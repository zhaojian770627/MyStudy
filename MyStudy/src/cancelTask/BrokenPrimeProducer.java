package cancelTask;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled)
				queue.put(p = p.nextProbablePrime());
			System.out.println("ËØÊýÏß³ÌÒÑÍ£Ö¹£¡");
		} catch (InterruptedException consumed) {
			cancelled=true;
			System.out.println("ÍÑÀë×èÈû×´Ì¬£¡");
			Thread.currentThread().interrupt();			
		}
	}

	public void cancel() {
		cancelled = true;
		//this.interrupt();
	}

	/**
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static void main(String[] args) throws InterruptedException,
			IOException {
		BlockingQueue<BigInteger> primes = new LinkedBlockingDeque<BigInteger>(
				10);
		BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();
		try {
			Thread.sleep(1000);
			// while(needMorePrimes())
			// System.out.println(primes.take());
		} catch (InterruptedException e) {

		} finally {
			// producer.cancel();
		}
		// while(!primes.isEmpty())
		// {
		// System.out.println(primes.take());
		// }
		producer.cancel();
		producer.interrupt();
		System.out.println(producer.isAlive());
//		System.in.read();
//		while (!primes.isEmpty()) {
//			System.out.println(primes.take());
//		}

	}

	private static boolean needMorePrimes() {
		return true;
	}

}
