package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class CONST extends EXP {
	public int value;

	public CONST(int v) {
		value = v;
	}

	public LinkedList<EXP> kids() {
		return new LinkedList<EXP>();
	}

	public EXP build(LinkedList<EXP> kids) {
		return this;
	}

	public void accept(IntVisitor v, int d) {
		v.visit(this, d);
	}

	public void accept(CodeVisitor v) {
		v.visit(this);
	}

	public Temp acceptRT(CodeVisitor v) {
		return v.visitRT(this);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}
}