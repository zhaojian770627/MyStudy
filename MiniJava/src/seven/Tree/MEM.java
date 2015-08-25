package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class MEM extends EXP {
	public EXP EXP;

	public MEM(EXP e) {
		EXP = e;
	}

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		kids.addFirst(EXP);
		return kids;
	}

	public EXP build(LinkedList<EXP> kids) {
		return new MEM(kids.getFirst());
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