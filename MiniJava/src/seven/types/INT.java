package seven.types;

public class INT extends MJType {
	private int value;

	public INT(int v) {
		value = v;
	}

	public INT() {
		value = 0;
	}

	public String GetType() {
		return "int";
	}

	public int GetVal() {
		return value;
	}

	public MJType SearchType(String name) {
		return this;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof INT))
			return false;
		else
			return true;
	}
}