package blockQueue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class BlockQueueMain {
	private final static int BOUND=1000;
	private static final int N_CONSUMERS = 3;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BlockingQueue<File> queue=new LinkedBlockingDeque<File>(BOUND);
		FileFilter filter=new FileFilter();
		List<File> roots=new ArrayList<File>();
		roots.add(new File("E:\\opengl"));
		
		for(File root:roots){
			new Thread(new FileCrawler(queue,filter,root)).start();			
		}
		
		for(int i=0;i<N_CONSUMERS;i++)
			new Thread(new Indexer(queue)).start();
	}

}
