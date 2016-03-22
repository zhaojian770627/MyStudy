package system.threadPool;

import java.util.concurrent.CountDownLatch;

/**
 * ConcurrentPuzzleSolverʹ�ÿ�Я������ı���
 * 
 * @author zhaojianc
 * 
 * @param <T>
 */
public class ValueLatch<T> {
	private T value = null;
	private final CountDownLatch done = new CountDownLatch(1);

	public boolean isSet() {
		return (done.getCount() == 0);
	}

	public synchronized void setValue(T newValue) {
		if (!isSet()) {
			value = newValue;
			done.countDown();
		}
	}

	public T getValue() throws InterruptedException {
		done.await();
		synchronized (this) {
			return value;
		}
	}
}
