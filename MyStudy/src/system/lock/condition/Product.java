package system.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Product implements Runnable {
	LinkedList<String> queue;
	String curThreadId = "";
	int i = 0;
	private Lock lock;
	private Condition notFull;
	private Condition notEmpty;

	public Product(LinkedList<String> queue, String flag, Lock lock, Condition notFull, Condition notEmpty) {
		this.queue = queue;
		curThreadId = flag;
		this.lock = lock;
		this.notFull = notFull;
		this.notEmpty = notEmpty;
	}

	@Override
	public void run() {
		while (true) {
			lock.lock();
			try {
				while (queue.size() >= 100) {
					try {
						System.out.println(curThreadId + " wait " + queue.size());
						notFull.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				queue.add(curThreadId + "-" + (i++));
				notEmpty.signalAll();
			} finally {
				lock.unlock();
			}
		}
	}
}
