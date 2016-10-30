package system.io.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 * Test locking with FileChannel. Run one copy of this code with arguments
 * "-w /tmp/locktest.dat" and one or more copies with "-r /tmp/locktest.dat" to
 * see the interactions of exclusive and shared locks. Note how too many readers
 * can starve out the writer. Note: The filename you provide will be
 * overwritten.Substitute and appropriate temp filename for your favorite OS.
 * 
 * @author zhaojianc
 *
 */
public class LockTest {
	private static final int SIZEOF_INT = 4;
	private static final int INDEX_START = 0;
	private static final int INDEX_COUNT = 10;
	private static final int INDEX_SIZE = INDEX_COUNT * SIZEOF_INT;

	private ByteBuffer buffer = ByteBuffer.allocate(INDEX_SIZE);
	private IntBuffer indexBuffer = buffer.asIntBuffer();
	private Random rand = new Random();

	public static void main(String[] args) throws FileNotFoundException {
		boolean writer = false;
		String filename;
		if (args.length != 2) {
			System.out.println("Usage:[-r|-w] filename");
			return;
		}

		writer = args[0].equals("-w");
		filename = args[1];
		RandomAccessFile raf = new RandomAccessFile(filename, (writer) ? "rw" : "r");
		FileChannel fc = raf.getChannel();
		LockTest lockTest = new LockTest();
		if (writer)
			lockTest.doUpdates(fc);
		else
			lockTest.doQueries(fc);
	}

	/**
	 * Simulate a series of read-only queries while holding a shared lock on the
	 * index area
	 * 
	 * @param fc
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	private void doQueries(FileChannel fc) throws IOException, InterruptedException {
		while (true) {
			println("trying for shared lock...");
			FileLock lock = fc.lock(INDEX_START, INDEX_SIZE, true);
			int reps = rand.nextInt(60) + 20;
			for (int i = 0; i < reps; i++) {
				int n = rand.nextInt(INDEX_COUNT);
				int position = INDEX_START + (n * SIZEOF_INT);
				buffer.clear();
				fc.read(buffer, position);
				int value = indexBuffer.get(n);
				println("Index entry " + n + "=" + value);
				Thread.sleep(100);
			}
			lock.release();
			println("<sleeping>");
			Thread.sleep(rand.nextInt(3000) + 500);
		}
	}

	private int lastLineLen = 0;

	private void println(String msg) {
		System.out.print("\r");
		System.out.print(msg);
		for (int i = msg.length(); i < lastLineLen; i++) {
			System.out.print(" ");
		}
		System.out.print("\r");
		System.out.flush();
		lastLineLen = msg.length();
	}

}
