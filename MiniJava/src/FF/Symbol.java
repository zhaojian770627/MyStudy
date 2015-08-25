package FF;

public class Symbol {
	/*
	 * 0 终结符 1 非终结符
	 */
	int type;

	/*
	 * 先暂时不用 0 1
	 */
	String typeID;

	/*
	 * 
	 * 非终结符 标示为字母
	 */
	String id;

	/*
	 * 终结符 具体的值
	 */
	String value;

	public Symbol(int i, String s) {
		type = i;
		id = s;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeID() {
		return typeID;
	}

	public void setTypeID(String typeID) {
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
}
