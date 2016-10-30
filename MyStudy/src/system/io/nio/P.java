package system.io.nio;

public class P implements Cloneable{
	public String getM() {
		return m;
	}

	public void setM(String m) {
		this.m = m;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		P p = new P();
		p.m = this.m;
		return p;
	}

	private String m = "";
}
