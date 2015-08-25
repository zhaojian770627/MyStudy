package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class EXP extends Stm {
	public EXP exp;

	// zhaojian Add
	public EXP() {

	}

	// zhaojian End

	public EXP(EXP e) {
		exp = e;
	}

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		kids.addFirst(exp);
		return kids;
	}

	public Stm build(LinkedList<EXP> kids) {
		return new EXP(kids.getFirst());
	}

	public void accept(IntVisitor v, int d) {
		v.visit(this, d);
	}

	public Temp acceptRT(CodeVisitor v){
		throw new Error("Not Unsupport");
	}

	public void accept(CodeVisitor v) {
		v.visit(this);
	}

	public <R> R accept(ResultVisitor<R> v) {
		return v.visit(this);
	}

}