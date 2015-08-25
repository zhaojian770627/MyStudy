package cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier类似于CountDownLatch也是个计数器，
 * 不同的是CyclicBarrier的数是调用CyclicBarrier.await进入等待的线程数
 * 当线程数达到了CyclicBarrier初始时规定的数目时，所有进入等待状态的线程被唤醒并继续 CyclicBarrier就象它名字的意思一样，是个障碍，
 * 所有的线程必须到齐后才能一起通过这个障碍 CyclicBarrier初始时还可带一个Runnable的参数
 * 此Runnable在Runnable的数目达到后，所有其他线程被唤醒前被执行
 * 
 * @author zhaojianc
 * 
 */
public class CyclicBarrierTest {
	public static class ComponentThread implements Runnable {
		CyclicBarrier barrier; // 计数器
		int ID;
		int[] array;

		public ComponentThread(CyclicBarrier barrier, int[] array, int ID) {
			this.barrier = barrier;
			this.ID = ID;
			this.array = array;
		}

		@Override
		public void run() {
			try {
				array[ID] = new Random().nextInt();
				System.out.println("Component " + ID + " generates: "
						+ array[ID]);
				System.out.println("Component " + ID + " sleep...");

				barrier.await();

				System.out.println("Component " + ID + " awaked...");
				// 计算数据数组中的当前值和后续值
				int result = array[ID] + array[ID + 1];
				System.out.println("Component " + ID + " result:" + result);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void testCyclicBarrier() {
		final int[] array = new int[3];
		CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
			@Override
			public void run() {
				System.out.println("TestCycliBarrier run...");
				array[2] = array[0] + array[1];
			}
		});

		new Thread(new ComponentThread(barrier, array, 0)).start();
		new Thread(new ComponentThread(barrier, array, 1)).start();
	}

	public static void main(String[] args) {
		CyclicBarrierTest.testCyclicBarrier();
	}
}
