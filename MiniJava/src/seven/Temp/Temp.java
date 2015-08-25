package seven.Temp;

public class Temp extends SimpleExp {
	private static int count = 30;
	private int num;

	public String toString() {
		return "t" + num;
	}

	public Temp() {
		num = count++;
	}

	public Temp(int t) {
		num = t;
	}

	public int hashCode() {
		return num;
	}

	public boolean spillTemp = false;
	public int spillCost;
}