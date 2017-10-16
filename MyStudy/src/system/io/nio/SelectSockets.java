package system.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SelectSockets {

	public static int PORT_NUMBER = 13;

	public static void main(String[] argv) throws IOException {
		new SelectSockets().go(argv);
	}

	public void go(String[] argv) throws IOException {
		int port = PORT_NUMBER;

		if (argv.length > 0)
			port = Integer.parseInt(argv[0]);

		System.out.println("Listening on port " + port);
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverChannel.socket();
		Selector selector = Selector.open();
		serverSocket.bind(new InetSocketAddress(port));
		serverChannel.configureBlocking(false);
		serverChannel.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			int n = selector.select();
			if (n == 0) {
				continue;
			}

			Iterator it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				SelectionKey key = (SelectionKey) it.next();

				// Is a new connection coming in?
				if (key.isAcceptable()) {
					ServerSocketChannel server = (ServerSocketChannel) key.channel();
					SocketChannel channel = server.accept();

					registerChannel(selector, channel, SelectionKey.OP_READ);

					sayTime(channel);
				}

				if (key.isReadable()) {
					readDataFromSocket(key);
				}

				it.remove();
			}
		}

	}

	private ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

	private void readDataFromSocket(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		int count;

		buffer.clear();

		while ((count = socketChannel.read(buffer)) > 0) {
			buffer.flip();

			while (buffer.hasRemaining()) {
				socketChannel.write(buffer);
			}

			buffer.clear();
		}

		if (count < 0) {
			socketChannel.close();
		}
	}

	private void sayTime(SocketChannel channel) throws IOException {
		buffer.clear();
		buffer.put("Hi there!\r\n".getBytes());
		buffer.flip();

		channel.write(buffer);
	}

	private void registerChannel(Selector selector, SocketChannel channel, int ops) throws IOException {
		if (channel == null) {
			return;
		}

		channel.configureBlocking(false);
		channel.register(selector, ops);
	}

}
