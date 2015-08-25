package seven.types;

import java.util.Vector;

public class ParamList extends MJType {
	private Vector p_list;
	private MJType aux_argu;

	public ParamList(MJType aa) {
		p_list = new Vector();
		aux_argu = aa;
	}

	public MJType GetArgu() {
		return aux_argu;
	}

	public void AddType(MJType nt) {
		p_list.addElement(nt);
	}

	public Vector GetParamList() {
		return p_list;
	}

	public MJType SearchType(String name) {
		return null;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof ParamList))
			return false;
		else
			return true;
	}

	public String GetType() {
		return ("Param List");
	}
}