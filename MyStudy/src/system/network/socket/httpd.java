package system.network.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class httpd {
	public static final int HTTP_PORT=8080;
	protected ServerSocket listen;
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		(new httpd()).start();
		
	}

	void start() throws IOException {
		System.out.println("Server started");
		listen = new ServerSocket(HTTP_PORT);
		while (true) {
			Socket client = listen.accept();
			HttpConnect cc=new HttpConnect(client);
		}
	}
}
