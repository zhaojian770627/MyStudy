package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class ESEQ extends EXP {
	public Stm stm;
	public EXP EXP;

	public ESEQ(Stm s, EXP e) {
		stm = s;
		EXP = e;
	}

	public LinkedList<EXP> kids() {
		throw new Error("kids() not applicable to ESEQ");
	}

	public EXP build(LinkedList<EXP> kids) {
		throw new Error("build() not applicable to ESEQ");
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