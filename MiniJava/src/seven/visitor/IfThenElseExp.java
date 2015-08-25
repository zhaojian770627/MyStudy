//package Translate;
package seven.visitor;

import seven.Temp.Temp;
import seven.Temp.Label;

public class IfThenElseExp extends Exp {
	Exp cond, a, b;
	Label t = new Label();
	Label f = new Label();
	Label join = new Label();

	IfThenElseExp(Exp cc, Exp aa, Exp bb) {
		cond = cc;
		a = aa;
		b = bb;
	}

	private static seven.Tree.Stm SEQ(seven.Tree.Stm left, seven.Tree.Stm right) {
		if (left == null)
			return right;
		if (right == null)
			return left;
		return new seven.Tree.SEQ(left, right);
	}

	private static seven.Tree.LABEL LABEL(Label l) {
		return new seven.Tree.LABEL(l);
	}

	private static seven.Tree.EXP ESEQ(seven.Tree.Stm stm, seven.Tree.EXP exp) {
		if (stm == null)
			return exp;
		return new seven.Tree.ESEQ(stm, exp);
	}

	private static seven.Tree.Stm MOVE(seven.Tree.EXP dst, seven.Tree.EXP src) {
		return new seven.Tree.MOVE(dst, src);
	}

	private static seven.Tree.Stm JUMP(Label l) {
		return new seven.Tree.JUMP(l);
	}

	private static seven.Tree.EXP TEMP(Temp t) {
		return new seven.Tree.TEMP(t);
	}

	seven.Tree.Stm unCx(Label tt, Label ff) {
		seven.Tree.Stm aStm = a.unCx(tt, ff);
		if (aStm instanceof seven.Tree.JUMP) {
			seven.Tree.JUMP aJump = (seven.Tree.JUMP) aStm;
			if (aJump.exp instanceof seven.Tree.NAME) {
				seven.Tree.NAME aName = (seven.Tree.NAME) aJump.exp;
				aStm = null;
				t = aName.label;
			}
		}
		seven.Tree.Stm bStm = b.unCx(tt, ff);
		if (bStm instanceof seven.Tree.JUMP) {
			seven.Tree.JUMP bJump = (seven.Tree.JUMP) bStm;
			if (bJump.exp instanceof seven.Tree.NAME) {
				seven.Tree.NAME bName = (seven.Tree.NAME) bJump.exp;
				bStm = null;
				f = bName.label;
			}
		}

		seven.Tree.Stm condStm = cond.unCx(t, f);

		if (aStm == null && bStm == null)
			return condStm;
		if (aStm == null)
			return SEQ(condStm, SEQ(LABEL(f), bStm));
		if (bStm == null)
			return SEQ(condStm, SEQ(LABEL(t), aStm));
		return SEQ(condStm, SEQ(SEQ(LABEL(t), aStm), SEQ(LABEL(f), bStm)));
	}

	seven.Tree.EXP unEx() {
		seven.Tree.EXP aExp = a.unEx();
		if (aExp == null)
			return null;
		seven.Tree.EXP bExp = b.unEx();
		if (bExp == null)
			return null;
		Temp r = new Temp();
		return ESEQ(
				SEQ(SEQ(cond.unCx(t, f),
						SEQ(SEQ(LABEL(t), SEQ(MOVE(TEMP(r), aExp), JUMP(join))),
								SEQ(LABEL(f),
										SEQ(MOVE(TEMP(r), bExp), JUMP(join))))),
						LABEL(join)), TEMP(r));
	}

	seven.Tree.Stm unNx() {
		seven.Tree.Stm aStm = a.unNx();
		if (aStm == null)
			t = join;
		else
			aStm = SEQ(SEQ(LABEL(t), aStm), JUMP(join));

		seven.Tree.Stm bStm = b.unNx();
		if (bStm == null)
			f = join;
		else
			bStm = SEQ(SEQ(LABEL(f), bStm), JUMP(join));

		if (aStm == null && bStm == null)
			return cond.unNx();

		seven.Tree.Stm condStm = cond.unCx(t, f);

		if (aStm == null)
			return SEQ(SEQ(condStm, bStm), LABEL(join));

		if (bStm == null)
			return SEQ(SEQ(condStm, aStm), LABEL(join));

		return SEQ(SEQ(condStm, SEQ(aStm, bStm)), LABEL(join));
	}
}
