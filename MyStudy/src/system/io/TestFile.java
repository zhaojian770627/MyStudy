package system.io;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.CRC32;

import org.junit.Test;

public class TestFile {

	public static void main(String[] args) throws IOException {
		CRC32 checksum = new CRC32();

		File file = new File("d:\\db.lck");
		RandomAccessFile raFile = new RandomAccessFile(file, "rw");

		ByteArrayOutputStream baos = new ByteArrayOutputStream(64);
		DataOutputStream daos = new DataOutputStream(baos);

		daos.writeInt(20);

		checksum.reset();
		checksum.update(baos.toByteArray(), 0, baos.size());
		daos.writeLong(checksum.getValue());
		daos.flush();
		raFile.seek(0);
		raFile.write(baos.toByteArray());
		raFile.getFD().sync();
		raFile.close();
	}

	@Test
	public void testReadFile() throws IOException {
		File file = new File("d:\\db.lck");
		RandomAccessFile raFile = new RandomAccessFile(file, "rw");
		ByteArrayOutputStream baos = new ByteArrayOutputStream(64);
		DataOutputStream daos = new DataOutputStream(baos);
		daos.writeInt(12);
		FileChannel chnl = raFile.getChannel();
		ByteBuffer buf = ByteBuffer.wrap(baos.toByteArray());
		chnl.write(buf);
		chnl.close();
		raFile.close();
	}

}
