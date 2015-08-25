package threadTest;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import junit.framework.TestCase;

/**
 * BoundedBuffer的生产者-消费者测试程序
 * @author zhaojianc
 *
 */
public class PutTakeTest extends TestCase {
	private static final ExecutorService pool = Executors.newCachedThreadPool();
	private final AtomicInteger putSum = new AtomicInteger(0);
	private final AtomicInteger takeSum = new AtomicInteger(0);
	private final CyclicBarrier barrier;
	private final BoundedBuffer<Integer> bb;
	private final int nTrials, nPairs;
	private final BarrierTimer timer;

	public static void main(String[] args) {
		new PutTakeTest(10, 10, 100000).test();
		pool.shutdown();
	}

	PutTakeTest(int capacity, int npairs, int ntrials) {
		this.bb = new BoundedBuffer<Integer>(capacity);
		this.nTrials = ntrials;
		this.nPairs = npairs;
		this.timer=new BarrierTimer();
		this.barrier = new CyclicBarrier(npairs * 2 + 1,timer);
	}

	void test() {
		try {
			for (int i = 0; i < nPairs; i++) {
				pool.execute(new Producer());
				pool.execute(new Consumer());
			}
			barrier.await();
			barrier.await();			
			long nsPerItem=timer.getTime()/(nPairs*(long)nTrials);
			System.out.println("Throughput:"+nsPerItem+" ns/item");
			assertEquals(putSum.get(), takeSum.get());
			System.out.println("放数:"+putSum.get()+" 取数:"+takeSum.get());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	class Producer implements Runnable {

		@Override
		public void run() {
			try {
				int seed = (this.hashCode() ^ (int) System.nanoTime());
				int sum = 0;
				barrier.await();
				for (int i = nTrials; i > 0; --i) {
					bb.put(seed);
					sum += seed;
					seed = xorShift(seed);
				}
				putSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	class Consumer implements Runnable {

		@Override
		public void run() {
			try {
				barrier.await();

				int sum = 0;
				for (int i = nTrials; i > 0; --i) {
					sum+=bb.take();
				}
				takeSum.getAndAdd(sum);
				barrier.await();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	static int xorShift(int y) {
		y ^= (y << 6);
		y ^= (y >>> 21);
		y ^= (y << 7);
		return y;
	}
}
