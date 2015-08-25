package seven.Tree;

public interface Hospitable {
	public void accept(IntVisitor v, int d);

	public <R> R accept(ResultVisitor<R> v);
}