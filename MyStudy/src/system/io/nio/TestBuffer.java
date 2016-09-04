package system.io.nio;

import java.nio.CharBuffer;

public class TestBuffer {

	public static void main(String[] args) {
		CharBuffer buffer = CharBuffer.allocate(100);
		buffer.put("abcdefghijklmn");
		buffer.flip();
		System.out.println(buffer.get());
		System.out.println(buffer.get());
		buffer.compact();
		System.out.println(buffer.array());
		System.out.println(buffer.position());
		System.out.println(buffer.limit());
	}

}
