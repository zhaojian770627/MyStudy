package system.thread.book.java7.one.s12;

import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
