package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

public class MOVE extends Stm {
	public EXP dst, src;

	public MOVE(EXP d, EXP s) {
		dst = d;
		src = s;
	}

	public LinkedList<EXP> kids() {
		LinkedList<EXP> kids = new LinkedList<EXP>();
		if (dst instanceof MEM) {
			kids.addFirst(((MEM) dst).EXP);
			kids.addLast(src);
		} else
			kids.addFirst(src);
		return kids;
	}

	public Stm build(LinkedList<EXP> kids) {
		if (dst instanceof MEM)
			return new MOVE(new MEM(kids.getFirst()), kids.getLast());
		else
			return new MOVE(dst, kids.getFirst());
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

	@Override
	public Temp acceptRT(CodeVisitor v) {
		throw new Error("Not Unsupport");
	}
}