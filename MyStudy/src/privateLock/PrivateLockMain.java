package privateLock;

import java.io.IOException;


public class PrivateLockMain {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		PrivateLock pl=new PrivateLock();
		for(int j=0;j<1000;j++)
		new SomeThread(pl).start();
		System.in.read();
		System.out.println(pl.getCount());
	}

}
