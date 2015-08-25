package timer;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class OutOfTime {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(1000);
		timer.schedule(new ThrowTask(), 1);
		Thread.sleep(1000);

		ScheduledExecutorService executor = Executors
				.newScheduledThreadPool(100);
	}

}
