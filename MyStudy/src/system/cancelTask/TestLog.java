package system.cancelTask;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TestLog {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
//		PrintWriter pw=new PrintWriter("c:\\abc.txt");
//		LogWriter lw=new LogWriter(pw);
//		lw.start();
//		lw.log("abcd");
		PrintWriter pw=new PrintWriter("c:\\dddd.txt");
		LogService lg=new LogService(pw);
		lg.start();
		lg.log("eeeeee");
		lg.stop();
	}

}