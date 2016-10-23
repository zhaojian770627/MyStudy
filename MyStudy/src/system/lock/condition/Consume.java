package system.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Consume implements Runnable {
	LinkedList<String> queue;
	int i = 0;
	String curThreadId = "";
	private Lock lock;
	private Condition notFull;
	private Condition notEmpty;

	public Consume(LinkedList<String> queue, Lock lock, Condition notFull, Condition notEmpty) {
		this.queue = queue;
		curThreadId = String.valueOf(Thread.currentThread().getId());
		this.lock = lock;
		this.notFull = notFull;
		this.notEmpty = notEmpty;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				while (queue.size() == 0) {
					try {
						notEmpty.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.printf("Get: %d: %s", queue.size(), queue.poll());
				notFull.signalAll();
			} finally {
				lock.unlock();
			}
		}
	}
}
