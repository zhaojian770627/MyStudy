package cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier������CountDownLatchҲ�Ǹ���������
 * ��ͬ����CyclicBarrier�����ǵ���CyclicBarrier.await����ȴ����߳���
 * ���߳����ﵽ��CyclicBarrier��ʼʱ�涨����Ŀʱ�����н���ȴ�״̬���̱߳����Ѳ����� CyclicBarrier���������ֵ���˼һ�����Ǹ��ϰ���
 * ���е��̱߳��뵽������һ��ͨ������ϰ� CyclicBarrier��ʼʱ���ɴ�һ��Runnable�Ĳ���
 * ��Runnable��Runnable����Ŀ�ﵽ�����������̱߳�����ǰ��ִ��
 * 
 * @author zhaojianc
 * 
 */
public class CyclicBarrierTest {
	public static class ComponentThread implements Runnable {
		CyclicBarrier barrier; // ������
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
				// �������������еĵ�ǰֵ�ͺ���ֵ
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
