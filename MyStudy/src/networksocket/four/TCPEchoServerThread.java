package networksocket.four;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TCPEchoServerThread {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			throw new IllegalArgumentException("Parameter(s):<Port>");
		}

		int echoServPort = Integer.parseInt(args[0]); // Server port

		Logger logger = Logger.getLogger("practical");

		// Create a server socket to accept client connection requests
		ServerSocket servSock = new ServerSocket(echoServPort);

		// Run forever, accepting and spawning a thread for each connection
		while (true) {
			Socket clntSock = servSock.accept(); // Block waiting for connection

			Thread thread = new Thread(new EcoProtocol(clntSock, logger));
			thread.start();
			logger.info("Create and started Thread " + thread.getName());
		}
	}

}
