package system.network.socket.compute;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Connect extends Thread {
	Socket client;
	DataInputStream is;
	PrintStream os;

	public Connect(Socket s) {
		System.out.println("接收了一个连接");
		client = s;
		try {
			is = new DataInputStream(client.getInputStream());
			os = new PrintStream(client.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.start();
	}

	@Override
	public void run() {
		String url = null;
		try {
			url = is.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String className = url;
		System.setOut(os);
		NetClassLoader sc = new NetClassLoader();
		try {
			Object o;
			o = (sc.loadClass(className, true)).newInstance();
			((ICompute) o).run();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			is.close();
			os.close();
			client.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
}
