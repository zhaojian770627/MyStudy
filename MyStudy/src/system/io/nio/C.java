package system.io.nio;

public class C extends P implements Cloneable{
	private String c = "";

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	@Override
	protected C clone() throws CloneNotSupportedException {
		C c = (C) super.clone();
		c.c = this.c;
		return c;
	}

}
