package cancelTask;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PrimeGenerator implements Runnable {
	private final List<BigInteger> primes=new ArrayList<BigInteger>();
	private volatile boolean cancelled;
	@Override
	public void run() {
		BigInteger p=BigInteger.ONE;
		while(!cancelled){
			p=p.nextProbablePrime();
			synchronized(this){
				primes.add(p);
			}
		}
	}
	public void cancel(){
		cancelled=true;
	}
	
	public synchronized List<BigInteger> get(){
		return new ArrayList<BigInteger>(primes);
	}
	
	public static void main(String[] args) {
		PrimeGenerator generator=new PrimeGenerator();
		new Thread(generator).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}finally
		{
			generator.cancel();
		}
		for(BigInteger i:generator.get()){
			System.out.println(i.toString());
		}
	}
}
