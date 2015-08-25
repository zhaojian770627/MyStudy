package seven.Mips;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import seven.Assem.Instr;
import seven.Temp.Label;
import seven.Temp.Temp;
import seven.Tree.BINOP;
import seven.Tree.CALL;
import seven.Tree.CJUMP;
import seven.Tree.CONST;
import seven.Tree.CodeVisitor;
import seven.Tree.ESEQ;
import seven.Tree.EXP;
import seven.Tree.JUMP;
import seven.Tree.LABEL;
import seven.Tree.MEM;
import seven.Tree.NAME;
import seven.Tree.SEQ;
import seven.Tree.TEMP;

public class Codegen implements CodeVisitor {
	private MipsFrame frame;
	private ListIterator<Instr> insns;

	public Codegen(MipsFrame f, ListIterator<Instr> i) {
		frame = f;
		insns = i;
	}

	private void emit(Instr inst) {
		insns.add(inst);
	}

	static Instr OPER(String a, Temp[] d, Temp[] s, List<Label> j) {
		return new seven.Assem.OPER("\t" + a, d, s, j);
	}

	static Instr OPER(String a, Temp[] d, Temp[] s) {
		return OPER(a, d, s, null);
	}

	static Instr MOVE(String a, Temp d, Temp s) {
		return new seven.Assem.MOVE("\t" + a, d, s);
	}

	private static CONST CONST16(EXP e) {
		if (e instanceof CONST) {
			CONST c = (CONST) e;
			int value = c.value;
			if (value == (short) value)
				return c;
		}
		return null;
	}

	private static boolean immediate(seven.Tree.BINOP e) {
		CONST left = CONST16(e.left);
		CONST right = CONST16(e.right);
		if (left == null)
			return right != null;
		switch (e.binop) {
		case seven.Tree.BINOP.PLUS:
		case seven.Tree.BINOP.MUL:
		case seven.Tree.BINOP.AND:
		case seven.Tree.BINOP.OR:
		case seven.Tree.BINOP.XOR:
			if (right == null) {
				e.left = e.right;
				e.right = left;
			}
			return true;
		}
		return false;
	}

	public void visit(seven.Tree.MOVE s) {
		// MOVE(MEM, EXP)
		if (s.dst instanceof MEM) {
			MEM mem = (MEM) s.dst;

			// MOVE(MEM(+ EXP CONST), EXP)
			if (mem.EXP instanceof seven.Tree.BINOP) {
				seven.Tree.BINOP b = (seven.Tree.BINOP) mem.EXP;
				if (b.binop == seven.Tree.BINOP.PLUS && immediate(b)) {
					int right = ((CONST) b.right).value;
					Temp left = (b.left instanceof seven.Tree.TEMP) ? ((seven.Tree.TEMP) b.left).temp
							: b.left.acceptRT(this);
					String off = Integer.toString(right);
					if (left == frame.FP) {
						left = frame.SP;
						off += "+" + frame.name + "_framesize";
					}
					emit(OPER("sw `s0 " + off + "(`s1)", null, new Temp[] {
							s.src.acceptRT(this), left }));
					return;
				}
			}

			// MOVE(MEM(CONST), EXP)
			CONST EXP = CONST16(mem.EXP);
			if (EXP != null) {
				emit(OPER("sw `s0 " + EXP.value + "(`s1)", null, new Temp[] {
						s.src.acceptRT(this), frame.ZERO }));
				return;
			}

			// MOVE(MEM(TEMP), EXP)
			if (mem.EXP instanceof seven.Tree.TEMP) {
				Temp temp = ((seven.Tree.TEMP) mem.EXP).temp;
				if (temp == frame.FP) {
					emit(OPER("sw `s0 " + frame.name + "_framesize" + "(`s1)",
							null, new Temp[] { s.src.acceptRT(this), frame.SP }));
					return;
				}
			}

			// MOVE(MEM(EXP), EXP)
			emit(OPER("sw `s0 (`s1)", null, new Temp[] { s.src.acceptRT(this),
					mem.EXP.acceptRT(this) }));
			return;
		}

		// From here on dst must be a TEMP
		Temp dst = ((TEMP) s.dst).temp;

		// MOVE(TEMP, MEM)
		if (s.src instanceof MEM) {
			MEM mem = (MEM) s.src;

			// MOVE(TEMP, MEM(+ EXP CONST))
			if (mem.EXP instanceof BINOP) {
				BINOP b = (BINOP) mem.EXP;
				if (b.binop == seven.Tree.BINOP.PLUS && immediate(b)) {
					int right = ((CONST) b.right).value;
					Temp left = (b.left instanceof seven.Tree.TEMP) ? ((seven.Tree.TEMP) b.left).temp
							: b.left.acceptRT(this);
					String off = Integer.toString(right);
					if (left == frame.FP) {
						left = frame.SP;
						off += "+" + frame.name + "_framesize";
					}
					emit(OPER("lw `d0 " + off + "(`s0)", new Temp[] { dst },
							new Temp[] { left }));
					return;
				}
			}

			// MOVE(TEMP, MEM(CONST))
			CONST EXP = CONST16(mem.EXP);
			if (EXP != null) {
				emit(OPER("lw `d0 " + EXP.value + "(`s0)", new Temp[] { dst },
						new Temp[] { frame.ZERO }));
				return;
			}

			// MOVE(TEMP, MEM(TEMP))
			if (mem.EXP instanceof TEMP) {
				Temp temp = ((TEMP) mem.EXP).temp;
				if (temp == frame.FP) {
					emit(OPER("lw `d0 " + frame.name + "_framesize" + "(`s0)",
							new Temp[] { dst }, new Temp[] { frame.SP }));
					return;
				}
			}

			// MOVE(TEMP, MEM(EXP))
			emit(OPER("lw `d0 (`s0)", new Temp[] { dst },
					new Temp[] { mem.EXP.acceptRT(this) }));
			return;
		}

		// MOVE(TEMP, EXP)
		emit(MOVE("move `d0 `s0", dst, s.src.acceptRT(this)));
	}

	public void visit(EXP s) {
		s.exp.accept(this);
	}

	public void visit(JUMP s) {
		if (s.exp instanceof NAME) {
			NAME name = (NAME) s.exp;
			// JUMP(NAME, List<Label>)
			emit(OPER("b " + name.label.toString(), null, null, s.targets));
			return;
		}
		// JUMP(EXP, List<Label>)
		emit(OPER("jr `s0", null, new Temp[] { s.exp.acceptRT(this) },
				s.targets));
		return;
	}

	private static boolean immediate(CJUMP s) {
		CONST left = CONST16(s.left);
		CONST right = CONST16(s.right);
		if (left == null)
			return right != null;
		if (right == null) {
			s.left = s.right;
			s.right = left;
			switch (s.relop) {
			case seven.Tree.CJUMP.EQ:
			case seven.Tree.CJUMP.NE:
				break;
			case seven.Tree.CJUMP.LT:
				s.relop = seven.Tree.CJUMP.GT;
				break;
			case seven.Tree.CJUMP.GE:
				s.relop = seven.Tree.CJUMP.LE;
				break;
			case seven.Tree.CJUMP.GT:
				s.relop = seven.Tree.CJUMP.LT;
				break;
			case seven.Tree.CJUMP.LE:
				s.relop = seven.Tree.CJUMP.GE;
				break;
			case seven.Tree.CJUMP.ULT:
				s.relop = seven.Tree.CJUMP.UGT;
				break;
			case seven.Tree.CJUMP.UGE:
				s.relop = seven.Tree.CJUMP.ULE;
				break;
			case seven.Tree.CJUMP.UGT:
				s.relop = seven.Tree.CJUMP.ULT;
				break;
			case seven.Tree.CJUMP.ULE:
				s.relop = seven.Tree.CJUMP.UGE;
				break;
			default:
				throw new Error("bad relop in Codegen.immediate");
			}
		}
		return true;
	}

	private static String[] CJUMP = new String[10];
	static {
		CJUMP[seven.Tree.CJUMP.EQ] = "beq";
		CJUMP[seven.Tree.CJUMP.NE] = "bne";
		CJUMP[seven.Tree.CJUMP.LT] = "blt";
		CJUMP[seven.Tree.CJUMP.GT] = "bgt";
		CJUMP[seven.Tree.CJUMP.LE] = "ble";
		CJUMP[seven.Tree.CJUMP.GE] = "bge";
		CJUMP[seven.Tree.CJUMP.ULT] = "bltu";
		CJUMP[seven.Tree.CJUMP.ULE] = "bleu";
		CJUMP[seven.Tree.CJUMP.UGT] = "bgtu";
		CJUMP[seven.Tree.CJUMP.UGE] = "bgeu";
	}

	public void visit(CJUMP s) {
		List<Label> targets = new LinkedList<Label>();
		targets.add(s.iftrue);
		targets.add(s.iffalse);
		if (immediate(s)) {
			int right = ((CONST) s.right).value;
			// CJUMP(op, EXP, CONST, Label, Label)
			emit(OPER(
					CJUMP[s.relop] + " `s0 " + right + " "
							+ s.iftrue.toString(), null,
					new Temp[] { s.left.acceptRT(this) }, targets));
			return;
		}

		// CJUMP(op, EXP, EXP, Label, Label)
		emit(OPER(CJUMP[s.relop] + " `s0 `s1 " + s.iftrue.toString(), null,
				new Temp[] { s.left.acceptRT(this), s.right.acceptRT(this) },
				targets));
		return;
	}

	public void visit(LABEL l) {
		emit(new seven.Assem.LABEL(l.label.toString() + ":", l.label));
		return;
	}

	public Temp visitRT(CONST e) {
		if (e.value == 0)
			return frame.ZERO;
		Temp t = new Temp();
		emit(OPER("li `d0 " + e.value, new Temp[] { t }, null));
		return t;
	}

	public Temp visitRT(NAME e) {
		Temp t = new Temp();
		emit(OPER("la `d0 " + e.label.toString(), new Temp[] { t }, null));
		return t;
	}

	public Temp visitRT(TEMP e) {
		if (e.temp == frame.FP) {
			Temp t = new Temp();
			emit(OPER("addu `d0 `s0 " + frame.name + "_framesize",
					new Temp[] { t }, new Temp[] { frame.SP }));
			return t;
		}
		return e.temp;
	}

	private static String[] BINOP = new String[10];
	static {
		BINOP[seven.Tree.BINOP.PLUS] = "add";
		BINOP[seven.Tree.BINOP.MINUS] = "sub";
		BINOP[seven.Tree.BINOP.MUL] = "mulo";
		BINOP[seven.Tree.BINOP.DIV] = "div";
		BINOP[seven.Tree.BINOP.AND] = "and";
		BINOP[seven.Tree.BINOP.OR] = "or";
		BINOP[seven.Tree.BINOP.LSHIFT] = "sll";
		BINOP[seven.Tree.BINOP.RSHIFT] = "srl";
		BINOP[seven.Tree.BINOP.ARSHIFT] = "sra";
		BINOP[seven.Tree.BINOP.XOR] = "xor";
	}

	private static int shift(int i) {
		int shift = 0;
		if ((i >= 2) && ((i & (i - 1)) == 0)) {
			while (i > 1) {
				shift += 1;
				i >>= 1;
			}
		}
		return shift;
	}

	public Temp visitRT(BINOP e) {
		Temp t = new Temp();
		if (immediate(e)) {
			int right = ((CONST) e.right).value;
			switch (e.binop) {
			case seven.Tree.BINOP.PLUS: {
				Temp left = (e.left instanceof TEMP) ? ((TEMP) e.left).temp
						: e.left.acceptRT(this);
				String off = Integer.toString(right);
				if (left == frame.FP) {
					left = frame.SP;
					off += "+" + frame.name + "_framesize";
				}
				emit(OPER("add `d0 `s0 " + off, new Temp[] { t },
						new Temp[] { left }));
				return t;
			}
			case seven.Tree.BINOP.MUL: {
				int shift = shift(right);
				if (shift != 0) {
					emit(OPER("sll `d0 `s0 " + shift, new Temp[] { t },
							new Temp[] { e.left.acceptRT(this) }));
					return t;
				}
				emit(OPER(BINOP[e.binop] + " `d0 `s0 " + right,
						new Temp[] { t }, new Temp[] { e.left.acceptRT(this) }));
				return t;
			}
			case seven.Tree.BINOP.DIV: {
				int shift = shift(right);
				if (shift != 0) {
					emit(OPER("sra `d0 `s0 " + shift, new Temp[] { t },
							new Temp[] { e.left.acceptRT(this) }));
					return t;
				}
				emit(OPER(BINOP[e.binop] + " `d0 `s0 " + right,
						new Temp[] { t }, new Temp[] { e.left.acceptRT(this) }));
				return t;
			}
			default:
				emit(OPER(BINOP[e.binop] + " `d0 `s0 " + right,
						new Temp[] { t }, new Temp[] { e.left.acceptRT(this) }));
				return t;
			}
		}
		emit(OPER(BINOP[e.binop] + " `d0 `s0 `s1", new Temp[] { t },
				new Temp[] { e.left.acceptRT(this), e.right.acceptRT(this) }));
		return t;
	}

	public Temp visitRT(MEM e) {
		Temp t = new Temp();

		// MEM(+ EXP CONST)
		if (e.EXP instanceof BINOP) {
			BINOP b = (BINOP) e.EXP;
			if (b.binop == seven.Tree.BINOP.PLUS && immediate(b)) {
				int right = ((CONST) b.right).value;
				Temp left = (b.left instanceof TEMP) ? ((TEMP) b.left).temp
						: b.left.acceptRT(this);
				String off = Integer.toString(right);
				if (left == frame.FP) {
					left = frame.SP;
					off += "+" + frame.name + "_framesize";
				}
				emit(OPER("lw `d0 " + off + "(`s0)", new Temp[] { t },
						new Temp[] { left }));
				return t;
			}
		}

		// MEM(CONST)
		CONST EXP = CONST16(e.EXP);
		if (EXP != null) {
			emit(OPER("lw `d0 " + EXP.value + "(`s0)", new Temp[] { t },
					new Temp[] { frame.ZERO }));
			return t;
		}

		// MEM(TEMP)
		if (e.EXP instanceof TEMP) {
			Temp temp = ((TEMP) e.EXP).temp;
			if (temp == frame.FP) {
				emit(OPER("lw `d0 " + frame.name + "_framesize" + "(`s0)",
						new Temp[] { t }, new Temp[] { frame.SP }));
				return t;
			}
		}

		// MEM(EXP)
		emit(OPER("lw `d0 (`s0)", new Temp[] { t },
				new Temp[] { e.EXP.acceptRT(this) }));
		return t;
	}

	public Temp visitRT(CALL s) {
		Iterator<EXP> args = s.args.iterator();

		LinkedList<Temp> uses = new LinkedList<Temp>();

		Temp V0 = args.next().acceptRT(this);
		if (V0 != frame.ZERO) {
			emit(MOVE("move `d0 `s0", frame.V0, V0));
			uses.add(frame.V0);
		}

		int offset = 0;

		if (args.hasNext()) {
			offset += frame.wordSize();
			emit(MOVE("move `d0 `s0", frame.A0, args.next().acceptRT(this)));
			uses.add(frame.A0);
		}
		if (args.hasNext()) {
			offset += frame.wordSize();
			emit(MOVE("move `d0 `s0", frame.A1, args.next().acceptRT(this)));
			uses.add(frame.A1);
		}
		if (args.hasNext()) {
			offset += frame.wordSize();
			emit(MOVE("move `d0 `s0", frame.A2, args.next().acceptRT(this)));
			uses.add(frame.A2);
		}
		if (args.hasNext()) {
			offset += frame.wordSize();
			emit(MOVE("move `d0 `s0", frame.A3, args.next().acceptRT(this)));
			uses.add(frame.A3);
		}
		while (args.hasNext()) {
			offset += frame.wordSize();
			emit(OPER("sw `s0 " + offset + "(`s1)", null, new Temp[] {
					args.next().acceptRT(this), frame.SP }));
		}

		if (offset > frame.maxArgOffset)
			frame.maxArgOffset = offset;

		if (s.func instanceof NAME) {
			emit(OPER("jal " + ((NAME) s.func).label.toString(),
					frame.calldefs, uses.toArray(new Temp[] {})));
			return frame.V0;
		}
		uses.addFirst(s.func.acceptRT(this));
		emit(OPER("jal `s0", frame.calldefs, uses.toArray(new Temp[] {})));
		return frame.V0;
	}

	@Override
	public void visit(SEQ n) {
		// TODO Auto-generated method stub

	}

	@Override
	public Temp visitRT(ESEQ n) {
		// TODO Auto-generated method stub
		return null;
	}
}