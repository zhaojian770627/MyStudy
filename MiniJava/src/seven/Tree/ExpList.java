package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class ExpList extends EXP {

	private LinkedList<EXP> list;

	public ExpList(LinkedList<EXP> l) {
		list = l;
	}

	public LinkedList<EXP> getList() {
		return list;
	}

	public LinkedList<EXP> kids() {
		return null;
	}

	public EXP build(LinkedList<EXP> kids) {
		return null;
	}

	public void accept(IntVisitor v, int d) {
	}

	public <R> R accept(ResultVisitor<R> v) {
		return null;
	}

	public Temp acceptRT(CodeVisitor v) {
		return null;
	}

}
