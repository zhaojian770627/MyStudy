package threadTest;

import junit.framework.TestCase;
import junit.framework.TestResult;

public class BoundedBufferTest extends TestCase {
	private static final long LOCKUP_DEFECT_TIMEOUT = 10000;

	public void testIsEmptyWhenConstructed(){
		BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);
		assertTrue(bb.isEmpty());
		assertFalse(bb.isFull());
	}
	
	public void testIsFullAfterPuts() throws InterruptedException{
		BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);
		for(int i=0;i<10;i++)
			bb.put(i);
		assertTrue(bb.isFull());
		assertFalse(bb.isEmpty());
	}
	
	public void testTakeBlocksWhenEmpty(){
		final BoundedBuffer<Integer> bb=new BoundedBuffer<Integer>(10);
		Thread taker=new Thread(){

			@Override
			public void run() {
				try{
					int unused=bb.take();
					fail();	//如果运行到这里，说明有错误
				}catch(InterruptedException success){
					
				}
			}			
		};
		
		try{
			taker.start();
			Thread.sleep(LOCKUP_DEFECT_TIMEOUT);
			taker.interrupt();
			taker.join(LOCKUP_DEFECT_TIMEOUT);
			assertFalse(taker.isAlive());
		}catch(Exception unexpected){
			
		}
	}
}
