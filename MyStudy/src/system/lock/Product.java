package system.lock;

import java.util.LinkedList;

public class Product implements Runnable {
	LinkedList<String> queue;
	String curThreadId = "";
	int i = 0;

	public Product(LinkedList<String> queue, String flag) {
		this.queue = queue;
		curThreadId = flag;
	}

	@Override
	public void run() {
		while (true) {
			synchronized (queue) {
				while (queue.size() >= 100) {
					try {
						System.out.println(curThreadId + " wait " + queue.size());
						queue.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				queue.add(curThreadId + "-" + (i++));

				queue.notifyAll();
			}
		}
	}
}
