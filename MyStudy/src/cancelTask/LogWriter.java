package cancelTask;

import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class LogWriter {
	private final BlockingQueue<String> queue;
	private final LoggerThread logger;
	
	public LogWriter(Writer writer){
		this.queue=new LinkedBlockingDeque<String>();
		this.logger=new LoggerThread(writer);
	}
	
	public void start(){
		logger.start();
	}
	
	public void log(String msg) throws InterruptedException{
		queue.put(msg);
	}	
	
	private class LoggerThread extends Thread {
		private final PrintWriter writer;
		public LoggerThread(Writer writer) {
			this.writer=(PrintWriter) writer;
		}
		
		@Override
		public void run() {
			try{
				while(true)
					writer.println(queue.take());
			}catch(InterruptedException ignored){
				
			}
			finally{
				writer.close();
			}
		}
		
	}
}
