package javacc.d.jtb.visitor;
import java.util.Hashtable;

class SymbolTable {
	private Hashtable<String, Class> hashtable;

	public SymbolTable() {
		hashtable = new Hashtable<String, Class>();
	}

	public boolean addClass(String id, String parent) {
		if(containsClass(id)) {
			return false;
		}
		else {
			hashtable.put(id, new Class(id, parent));
			return true;
		}
	}

	public Class getClass(String id) {
		if(containsClass(id)) {
			return hashtable.get(id);
		}
		else {
			return null;
		}
	}

	public boolean containsClass(String id) {
		return hashtable.containsKey(id);
	}
}