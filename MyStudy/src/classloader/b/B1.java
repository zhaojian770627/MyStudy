package classloader.b;

public class B1 extends Thread {

	@Override
	public void run() {
		B2 b2=new B2();
		b2.add();
	}

}
