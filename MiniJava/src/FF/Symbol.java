package FF;

public class Symbol {
	/*
	 * 0 �ս�� 1 ���ս��
	 */
	int type;

	/*
	 * ����ʱ���� 0 1
	 */
	String typeID;

	/*
	 * 
	 * ���ս�� ��ʾΪ��ĸ
	 */
	String id;

	/*
	 * �ս�� �����ֵ
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
