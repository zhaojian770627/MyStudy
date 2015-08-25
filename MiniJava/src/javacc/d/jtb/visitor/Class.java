package javacc.d.jtb.visitor;
import java.util.Hashtable;

class Class extends SymbolTableScope {
	private Hashtable<String, Method> methods;
	private String parent;

	public Class(String id, String parent) {
		super(id, id);
		this.parent = parent;
		methods = new Hashtable<String, Method>();
	}

	public boolean addMethod(String id, String type) {
		if(containsMethod(id)) {
			return false;
		}
		else {
			methods.put(id, new Method(id, type));
			return true;
		}
	}

	public Method getMethod(String id) {
		if(containsMethod(id)) {
			return methods.get(id);
		}
		else {
			return null;
		}
	}

	private boolean containsMethod(String id) {
		return methods.containsKey(id);
	}

	public String parent() {
		return parent;
	}
}