package system.lock.object;

import java.util.LinkedList;

public class LockMain {

	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		Product product1 = new Product(queue, "a");
		Product product2 = new Product(queue, "b");
		Consume consume = new Consume(queue);
		Thread tp1 = new Thread(product1);
		Thread tp2 = new Thread(product2);
		
		Thread tp3 = new Thread(consume);

		tp1.start();
		tp2.start();
		
		tp3.start();

	}

}
