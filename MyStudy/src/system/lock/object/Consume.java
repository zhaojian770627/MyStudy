package system.lock.object;

import java.util.LinkedList;

public class Consume implements Runnable {
	LinkedList<String> queue;
	int i = 0;
	String curThreadId = "";

	public Consume(LinkedList<String> queue) {
		this.queue = queue;
		curThreadId = String.valueOf(Thread.currentThread().getId());
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.size() == 0) {
					try {
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.printf("Get: %d: %s\n", queue.size(), queue.poll());
				queue.notify();
			}
		}
	}
}
