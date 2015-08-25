package javacc.d.jtb.visitor;
import java.util.Vector;

class Method extends SymbolTableScope {
	Vector parameters;

	public Method(String id, String type) {
		super(id, type);
		parameters = new Vector();
	}

	public boolean addParameter(String id, String type) {
		if(!addVar(id, type)) {
			return false;
		}
		else {
			parameters.add(getVar(id));
			return true;
		}
	}

	public Variable getParameter(int index) {
		if(index > numParameters()) {
			return null;
		}
		else {
			return (Variable)parameters.get(index);
		}
	}

	public int numParameters() {
		return parameters.size();
	}
}