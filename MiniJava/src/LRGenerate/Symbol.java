package LRGenerate;

public class Symbol {
	/*
	 * 0 终结符 1 非终结符
	 */
	int isnullable;

	int typeID;

	/*
	 * 
	 * 非终结符 标示为字母
	 */
	String id;

	/*
	 * 终结符 具体的值
	 */
	String value;

	public Symbol(int i, String s, int typeid) {
		isnullable = i;
		id = s;
		typeID = typeid;
	}

	public int getType() {
		return isnullable;
	}

	public void setType(int type) {
		this.isnullable = type;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	protected Symbol clone() throws CloneNotSupportedException {
		Symbol c = new Symbol(isnullable, id, typeID);
		c.setValue(value);
		return c;
	}
}
