package memcached.test;

import java.util.concurrent.CountDownLatch;

public class TestHarness {
	public long timeTasks(int nThreads, final Runnable task)
			throws InterruptedException {
		final CountDownLatch startGate = new CountDownLatch(1);
		final CountDownLatch endGate = new CountDownLatch(nThreads);

		for (int i = 0; i < nThreads; i++) {
			Thread t = new RunThread(i) {
				public void run() {
					try {
						startGate.await();
						try {
							long a = System.currentTimeMillis();
							task.run();
							long b = System.currentTimeMillis();
							TestMain.ts[num] = (int) (b - a);
						} finally {
							endGate.countDown();
						}
					} catch (InterruptedException ignored) {

					}
				}
			};
			t.start();
		}
		long start = System.nanoTime();
		startGate.countDown();
		endGate.await();
		long end = System.nanoTime();
		return end - start;
	}
}
