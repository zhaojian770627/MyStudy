package seven.Temp;

public class Offset extends SimpleExp {
	int offset;

	public Offset(int os) {
		offset = os;
	}

	public int value() {
		return offset;
	}

	public void print() {
		System.out.print(offset + " ");
	}
}