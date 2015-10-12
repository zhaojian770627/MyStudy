package basicstudy;

import java.util.Random;

public class BreakpointDemo {
	private Random random = new Random();

	private int value = -1;

	private void setValue(int count) {
		System.out.println("entering setValue method ...");
		for (int i = 0; i < count; i++) {
			value = random.nextInt(10);
		}
		System.out.println("leaving setValue method ...");
	}

	private void printValue(int count) {
		setValue(count);

		if (value % 3 == 0) {
			throw new IllegalArgumentException("value is illegal");
		}
		System.out.print(value);
	}

	public static void main(String[] args) {
		BreakpointDemo demo = new BreakpointDemo();
		demo.printValue(21);
	}

}
