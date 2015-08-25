package seven.Tree;

import java.util.LinkedList;

import seven.Temp.Temp;

abstract public class Stm implements Hospitable {
	public abstract LinkedList<EXP> kids();

	public abstract Stm build(LinkedList<EXP> kids);

	public abstract void accept(IntVisitor v, int d);

	public abstract <R> R accept(ResultVisitor<R> v);

	public abstract void accept(CodeVisitor v);

	public abstract Temp acceptRT(CodeVisitor v);
}