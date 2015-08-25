package countDownLatch;

public class TestMain {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		TaskThread task = new TaskThread();
		TestHarness test = new TestHarness();
		long time = test.timeTasks(4, task);
		System.out.println(time/1000);
	}
}
