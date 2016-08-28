package classloader.threadclassload;

public class ThreadContextRunner implements Runnable {

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getContextClassLoader());
		Test t=new Test();
		System.out.println(t.getClass().getClassLoader());
		t.print();
	}

}
