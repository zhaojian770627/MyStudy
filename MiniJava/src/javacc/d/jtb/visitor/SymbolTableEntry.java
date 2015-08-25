package javacc.d.jtb.visitor;

abstract class SymbolTableEntry {
	private String id;
	private String type;

	public SymbolTableEntry(String id, String type) {
		this.id = id;
		this.type = type;
	}

	public String id() {
		return id;
	}

	public String type() {
		return type;
	}
}