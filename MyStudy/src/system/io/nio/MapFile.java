package system.io.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Test behavior of Memory mapped buffer types.Create a file,write some data to
 * it,then create three different types of mappings to it.Observe the effects of
 * changes through the buffer APIs and updating the file directly.THe data spans
 * page boundaries to illustrate the page-oriented nature of Copy-On-Write
 * mappings.
 * 
 * @author zhaojianc
 *
 */
public class MapFile {

	public static void main(String[] args) throws IOException {
		File tempFile = File.createTempFile("mmaptest", null);
		RandomAccessFile file = new RandomAccessFile(tempFile, "rw");
		FileChannel channel = file.getChannel();
		ByteBuffer temp = ByteBuffer.allocate(100);
		// Put something in the file,starting at location 0
		temp.put("This is the file content".getBytes());
		temp.flip();
		channel.write(temp, 0);

		// Put something else in the file,starting at location 8192.
		// 8192 is 8KB,almost certainly a different memory/FS page.
		// This may cause a file hole,depending on the filesystem
		// page size.
		temp.clear();
		temp.put("This is more file content".getBytes());
		temp.flip();
		channel.write(temp, 8192);
		// Create three types of mappings to the same file
		MappedByteBuffer ro = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
		MappedByteBuffer rw = channel.map(FileChannel.MapMode.READ_WRITE, 0, channel.size());
		MappedByteBuffer cow = channel.map(FileChannel.MapMode.PRIVATE, 0, channel.size());
		// the buffer states before any modifications
		System.out.println("Begin");
		showBuffers(ro, rw, cow);
		// Modify the copy-on-write buffer
		cow.position(8);
		cow.put("COW".getBytes());
		System.out.println("Change to COW buffer");
		showBuffers(ro, rw, cow);
		// Modify the read/write buffer
		rw.position(9);
		rw.put(" R/W ".getBytes());
		rw.position(8194);
		rw.put(" R/W ".getBytes());
		rw.force();
		System.out.println("Change to R/W buffer");
		showBuffers(ro, rw, cow);
		// Write to the file through the channel;hit both pages
		temp.clear();
		temp.put("Channel write ".getBytes());
		temp.flip();
		channel.write(temp, 0);
		temp.rewind();
		channel.write(temp, 8202);
		System.out.println("Write on channel");
		showBuffers(ro, rw, cow);
		// Modify the copy-on-write buffer again
		cow.position(8207);
		cow.put(" COW2 ".getBytes());
		System.out.println("Second change to COW buffer");
		showBuffers(ro, rw, cow);
		// Modify the read/write buffer
		rw.position(0);
		rw.put(" R/W1 ".getBytes());
		rw.position(8210);
		rw.put(" R/W2 ".getBytes());
		rw.force();
		System.out.println("Second change to R/W buffer");
		showBuffers(ro, rw, cow);
		// cleanup
		channel.close();
		file.close();
		tempFile.delete();
	}

	// Show the current content of the three buffers
	private static void showBuffers(MappedByteBuffer ro, MappedByteBuffer rw, MappedByteBuffer cow) {
		dumpBuffer("R/O", ro);
		dumpBuffer("R/W", rw);
		dumpBuffer("COW", cow);
		System.out.println("");
	}

	public static void dumpBuffer(String prefix, ByteBuffer buffer) {
		System.out.println(prefix + ":'");
		int nulls = 0;
		int limit = buffer.limit();
		for (int i = 0; i < limit; i++) {
			char c = (char) buffer.get(i);
			if (c == '\u0000') {
				nulls++;
				continue;
			}

			// �ն�
			if (nulls != 0) {
				System.out.print("|[" + nulls + " nulls]|");
				nulls = 0;
			}
			System.out.print(c);
		}
		System.out.println("'");
	}
}
