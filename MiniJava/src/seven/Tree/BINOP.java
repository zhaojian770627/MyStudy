package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class BINOP extends EXP {
	public int binop;
	public EXP left, right;

	public BINOP(int b, EXP l, EXP r) {
		binop = b;
		left = l;
		right = r;
	}

	public final static int PLUS = 0, MINUS = 1, MUL = 2, DIV = 3, AND = 4,
			OR = 5, LSHIFT = 6, RSHIFT = 7, ARSHIFT = 8, XOR = 9;

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		kids.addFirst(left);
		kids.addLast(right);
		return kids;
	}

	public EXP build(LinkedList<EXP> kids) {
		return new BINOP(binop, kids.getFirst(), kids.getLast());
	}

	public void accept(IntVisitor v, int d) {
		v.visit(this, d);
	}

	public Temp acceptRT(CodeVisitor v) {
		return v.visitRT(this);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}
}