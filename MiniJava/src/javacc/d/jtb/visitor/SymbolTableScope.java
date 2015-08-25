package javacc.d.jtb.visitor;
import java.util.Hashtable;

class SymbolTableScope extends SymbolTableEntry {
	private Hashtable<String, Variable> vars;

	public SymbolTableScope(String id, String type) {
		super(id, type);
		vars = new Hashtable<String, Variable>();
	}

	public boolean addVar(String id, String type) {
		if(containsVar(id)) {
			return false;
		}
		else {
			vars.put(id, new Variable(id, type));
			return true;
		}
	}

	public Variable getVar(String id) {
		if(containsVar(id)) {
			return vars.get(id);
		}
		else {
			return null;
		}
	}

	private boolean containsVar(String id) {
		return vars.containsKey(id);
	}
}