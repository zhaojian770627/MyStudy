package cancelTask;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LogService {
	private final BlockingQueue<String> queue;
	private final LoggerThread loggerThread;
	private final PrintWriter writer;
	private boolean isShutdown;
	private int reservations;
	
	public LogService(PrintWriter writer)
	{
		this.queue=new LinkedBlockingDeque<String>();
		this.writer=writer;
		this.loggerThread=new LoggerThread();
	}
	public void start(){
		loggerThread.start();
	}
	
	public void stop(){
		synchronized (this) {
			isShutdown=true;
		}
		loggerThread.interrupt();
	}
	
	public void log(String msg) throws InterruptedException{
		synchronized (this) {
			if(isShutdown)
				throw new IllegalStateException();
			++reservations;
		}
		queue.put(msg);
	}
	
	private class LoggerThread extends Thread{
		public void run(){
			try{
				while(true){
					try{
						synchronized (LogService.this) {
							if(isShutdown && reservations==0)
								break;
						}
						String msg=queue.take();
						synchronized (LogService.this) {
							--reservations;
						}
						writer.println(msg);
					}catch(InterruptedException e){
						/**
						 * жиЪд
						 */
					}
				}
			}finally{
				writer.close();
			}
		}
	}
}
