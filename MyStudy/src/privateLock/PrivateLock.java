package privateLock;

public class PrivateLock {
	private final Object myLock = new Object();

	private int count;

	void doSome() {
//		synchronized (myLock) {
			System.out.println(count);
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			count++;			
//		}
	}
	
	int getCount()
	{
		return count;
	}
}
