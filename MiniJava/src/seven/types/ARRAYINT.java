package seven.types;

public class ARRAYINT extends MJType {
	private int value;

	public ARRAYINT(int v) {
		value = v;
	}

	public ARRAYINT() {
	}

	public String GetType() {
		return "array-int";
	}

	public MJType SearchType(String name) {
		return this;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof ARRAYINT))
			return false;
		else
			return true;
	}
}