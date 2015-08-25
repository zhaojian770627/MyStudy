package seven.Tree;

import java.util.LinkedList;
import java.util.List;

import seven.Temp.Temp;

public class CALL extends EXP {
	public EXP func;
	public List<EXP> args;

	public CALL(EXP f, List<EXP> a) {
		func = f;
		args = a;
	}

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		kids.addFirst(func);
		kids.addAll(args);
		return kids;
	}

	public EXP build(LinkedList<EXP> kids) {
		return new CALL(kids.removeFirst(), kids);
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