package privateLock;

public class SomeThread extends Thread {	
	private PrivateLock pl;
	public SomeThread(PrivateLock pl){
		this.pl=pl;
	}
	
	@Override
	public void run() {
		for(int i=0;i<1000;i++)
			pl.doSome();
	}
}
