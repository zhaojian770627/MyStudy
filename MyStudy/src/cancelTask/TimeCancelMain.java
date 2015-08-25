package cancelTask;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import concurrent.tools;

public class TimeCancelMain {
	// 在指定时间内取消任务
	public static void timedRun(final Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		ScheduledExecutorService cancelExec = Executors
				.newScheduledThreadPool(1);
		class RethrowableTask implements Runnable {
			private volatile Throwable t;

			@Override
			public void run() {
				try {
					r.run();
				} catch (Throwable t) {
					this.t = t;
				}
			}

			void rethrow() {
				if (t != null)
					throw tools.launderThrowable(t);
			}
		}

		RethrowableTask task = new RethrowableTask();
		final Thread taskThread = new Thread(task);
		taskThread.start();
		System.out.println("a");
		cancelExec.schedule(new Runnable() {
			public void run() {
				taskThread.interrupt();
				System.out.println("中断");
			}
		}, timeout, unit);
		System.out.println("b");
		taskThread.join(timeout);
		task.rethrow();
		cancelExec.shutdown();
	}

	/*
	 * 用Future来实现
	 */
	public static void timedRun2(Runnable r, long timeout, TimeUnit unit)
			throws InterruptedException {
		ExecutorService taskExec = Executors.newSingleThreadExecutor();
		Future<?> task = taskExec.submit(r);
		try {
			task.get(timeout, unit);
		} catch (TimeoutException e) {

		} catch (ExecutionException e) {
			throw tools.launderThrowable(e.getCause());
		} finally {
			task.cancel(true);
		}
		taskExec.shutdown();
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingDeque<BigInteger>(
				10);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		/* 使用timedRun
		timedRun(producer, 1, TimeUnit.SECONDS);
		while (!primes.isEmpty()) {
			System.out.println(primes.take());
		}
		*/
		// 使用timedRun2
		timedRun2(producer,1,TimeUnit.SECONDS);
		while (!primes.isEmpty()) {
			System.out.println(primes.take());
		}		
	}

}
