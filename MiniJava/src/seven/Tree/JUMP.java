package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Label;
import seven.Temp.Temp;

public class JUMP extends Stm {
	public EXP exp;
	public LinkedList<Label> targets;

	public JUMP(EXP e, LinkedList<Label> t) {
		exp = e;
		targets = t;
	}

	public JUMP(Label target) {
		exp = new NAME(target);
		targets = new LinkedList<Label>();
		targets.addFirst(target);
	}

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		kids.addFirst(exp);
		return kids;
	}

	public Stm build(LinkedList<EXP> kids) {
		return new JUMP(kids.getFirst(), targets);
	}

	public void accept(IntVisitor v, int d) {
		v.visit(this, d);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}

	public void accept(CodeVisitor v) {
		v.visit(this);
	}

	@Override
	public Temp acceptRT(CodeVisitor v) {
		throw new Error("Not Unsupport");
	}
}