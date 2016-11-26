package system.io.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Test channel transfer.This is a very simplistic concatenation program.It
 * takes a list of file names as arguments,opens each in turn and transfers
 * (copies) theirs cotent to the given WritableByteChannel (in this
 * case,stdout).
 * 
 * @author zhaojianc
 *
 */
public class ChannelTransfer {

	public static void main(String[] args) throws IOException {
		if (args.length == 0) {
			System.err.println("Usage:filename");
			return;
		}
		catFiles(Channels.newChannel(System.out), args);
	}

	private static void catFiles(WritableByteChannel target, String[] files) throws IOException {
		for (int i = 0; i < files.length; i++) {
			FileInputStream fis = new FileInputStream(files[i]);
			FileChannel channel = fis.getChannel();
			channel.transferTo(0, channel.size(), target);
			channel.close();
			fis.close();
		}
	}

}
