package system.cancelTask;

import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import system.blockQueue.FileFilter;

/**
 * 使用致命药丸来关闭
 * 
 * @author zhaojianc
 * 
 */
public class IndexingService {
	private static final File POISON = new File("");
	private final IndexerThread consumer = new IndexerThread();
	private final CrawlerThread producer = new CrawlerThread();
	private final BlockingQueue<File> queue = new LinkedBlockingDeque<File>();
	private final FileFilter fileFilter = new FileFilter();
	private final File root = new File("F:\\");;

	class CrawlerThread extends Thread {
		int count = 0;

		@Override
		public void run() {
			try {
				crawl(root);
			} catch (InterruptedException e) {
				/* 失败 */
				System.out.println("生产者线程中断");
			} finally {
				while (true) {
					try {
						System.out.println("生产者线程停止:" + count);
						queue.put(POISON);
						break;
					} catch (InterruptedException e1) {
						/* 重试 */
					}
				}
			}
		}

		private void crawl(File root) throws InterruptedException {
			File[] entries = root.listFiles(fileFilter);
			if (entries != null) {
				for (File entry : entries)
					if (entry.isDirectory())
						crawl(entry);
					else {
						queue.put(entry);
						count++;
					}

			}
		}

	}

	class IndexerThread extends Thread {

		@Override
		public void run() {
			try {
				while (true) {
					File file = queue.take();
					if (file == POISON)
						break;
					else
						indexFile(file);
				}
			} catch (InterruptedException consumed) {

			}
		}

		private void indexFile(File file) {
			System.out.println(file.getName());
		}
	}

	public void start() {
		producer.start();
		consumer.start();
	}

	public void stop() {
		producer.interrupt();
	}

	public void awaitTermination() throws InterruptedException {
		consumer.join();
	}
}
