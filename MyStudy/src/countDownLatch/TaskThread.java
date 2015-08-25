package countDownLatch;

public class TaskThread implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());		
	}		
}
