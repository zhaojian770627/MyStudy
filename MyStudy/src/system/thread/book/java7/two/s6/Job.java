package system.thread.book.java7.two.s6;

/**
 * This class simulates a job that send a document to print
 *
 */
public class Job implements Runnable {

	/**
	 * The queue to send the documents
	 */
	private PrintQueue printQueue;
	
	/**
	 * Constructor of the class. Initializes the print queue
	 * @param printQueue the print queue to send the documents
	 */
	public Job(PrintQueue printQueue){
		this.printQueue=printQueue;
	}
	
	/**
	 * Core method of the Job. Sends the document to the queue
	 */
	@Override
	public void run() {
		System.out.printf("%s: Going to print a job\n",Thread.currentThread().getName());
		printQueue.printJob(new Object());
		System.out.printf("%s: The document has been printed\n",Thread.currentThread().getName());		
	}

}
