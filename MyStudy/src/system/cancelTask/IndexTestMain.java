package system.cancelTask;

public class IndexTestMain {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		IndexingService index = new IndexingService();
		index.start();	
		index.stop();
	}

}
