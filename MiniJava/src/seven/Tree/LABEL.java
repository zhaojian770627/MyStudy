package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Label;
import seven.Temp.Temp;

public class LABEL extends Stm {
	public Label label;

	public LABEL(Label l) {
		label = l;
	}

	public LinkedList<EXP> kids() {
		return new LinkedList<EXP>();
	}

	public Stm build(LinkedList<EXP> kids) {
		return this;
	}

	public void accept(IntVisitor v, int d) {
		v.visit(this, d);
	}

	public void accept(CodeVisitor v) {
		v.visit(this);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}

	public Temp acceptRT(CodeVisitor v)  {
		throw new Error("Not Unsupport");
	}
}