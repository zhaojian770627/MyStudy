package system.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockMain {

	public static void main(String[] args) {
		LinkedList<String> queue = new LinkedList<>();
		Lock lock = new ReentrantLock();
		Condition notFull = lock.newCondition();
		Condition notEmpty = lock.newCondition();

		Product product1 = new Product(queue, "a", lock, notFull, notEmpty);
		Product product2 = new Product(queue, "b", lock, notFull, notEmpty);
		Consume consume = new Consume(queue, lock, notFull, notEmpty);
		Thread tp1 = new Thread(product1);
		Thread tp2 = new Thread(product2);

		Thread tp3 = new Thread(consume);

		tp1.start();
		tp2.start();

		tp3.start();

	}

}
