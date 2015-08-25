package seven.types;

public class Name extends MJType {
	private String name;

	public Name(String v_name) {
		name = v_name;
	}

	public String GetName() {
		return name;
	}

	public String GetType() {
		return ("Object " + name);
	}

	public MJType SearchType(String name) {
		return this;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof Name))
			return false;
		else
			return true;
	}

}