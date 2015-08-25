package seven.types;

public class BOOLEAN extends MJType {
	private boolean value;

	public BOOLEAN(boolean v) {
		value = v;
	}

	public BOOLEAN() {
	}

	public String GetType() {
		return "boolean";
	}

	public MJType SearchType(String name) {
		return this;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof BOOLEAN))
			return false;
		else
			return true;
	}

}