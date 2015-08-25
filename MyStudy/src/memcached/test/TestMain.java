package memcached.test;

import java.util.concurrent.atomic.AtomicLong;

public class TestMain {
	public static int[] ts = new int[1000];
	public static int sum = 0;
	public static int avg = 0;

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		int n = 1000;
		TaskThread task = new TaskThread();
		TestHarness test = new TestHarness();
		long time = test.timeTasks(n, task);
		System.out.println(time / n);
		for (int i = 0; i < 1000; i++) {
			System.out.println("" + ts[i]);
			sum += ts[i];
		}
		System.out.println("Sum:" + sum);
		System.out.println("Avg:" + sum / 1000);
	}
}
