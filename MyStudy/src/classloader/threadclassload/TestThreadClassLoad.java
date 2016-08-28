package classloader.threadclassload;

import classloader.FileSystemClassLoader;

public class TestThreadClassLoad {

	public static void main(String[] args) {
		ThreadContextRunner runner=new ThreadContextRunner();
		Thread thread=new Thread(runner);
		thread.setContextClassLoader(new FileSystemClassLoader("d:\\"));
		thread.start();
	}

}
