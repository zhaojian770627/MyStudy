package system.thread.book.java7.one;

public class Main {

	public static void main(String[] args) {
		for (int i = 1; i <= 10; i++) {
			Calculator calculator = new Calculator(i);
			Thread thread = new Thread(calculator);
			thread.start();
		}
	}

}
