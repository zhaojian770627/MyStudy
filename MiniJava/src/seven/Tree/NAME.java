package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Label;
import seven.Temp.Temp;

public class NAME extends EXP {
	public Label label;

	public NAME(Label l) {
		label = l;
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

	public Temp acceptRT(CodeVisitor v) {
		return v.visitRT(this);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}
}