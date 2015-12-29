package networksocket.four;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimelimitEchoProtool implements Runnable {

	private static final int BUFSIZE = 32; // Size (bytes) of buffer
	private static final String TIMELIMIT = "10000"; // Default limit(ms)
	private static final String TIMELIMITPROP = "Timelimit"; // Property

	private static int timelimit;
	private Socket clntSock;
	private Logger logger;

	public TimelimitEchoProtool(Socket clntSock, Logger logger) {
		this.clntSock = clntSock;
		this.logger = logger;

		// Get the time limit from the System properties or take the default
		timelimit = Integer.parseInt(System.getProperty(TIMELIMITPROP, TIMELIMIT));
	}

	public static void handleEchoClient(Socket clntSock, Logger logger) {
		try {
			// Get the input and output I/O streams from socket
			InputStream in = clntSock.getInputStream();
			OutputStream out = clntSock.getOutputStream();

			int recvMsgSize; // Size of received message
			int totalBytesEchoed = 0; // Bytes received from client
			byte[] echoBuffer = new byte[BUFSIZE]; // Receive buffer
			long endTime = System.currentTimeMillis() + timelimit;
			int timeBondMillis = timelimit;

			clntSock.setSoTimeout(timeBondMillis);
			// Receive until client closes connection,indicated by -1
			while ((timeBondMillis > 0) && // catch zero values
					((recvMsgSize = in.read(echoBuffer)) != -1)) {
				out.write(echoBuffer, 0, recvMsgSize);
				totalBytesEchoed += recvMsgSize;
				timeBondMillis = (int) (endTime - System.currentTimeMillis());
				clntSock.setSoTimeout(timeBondMillis);
			}
			logger.info("Client " + clntSock.getRemoteSocketAddress() + ",echoed " + totalBytesEchoed + " bytes.");
		} catch (IOException ex) {
			logger.log(Level.WARNING, "Exception in echo protocol", ex);
		}
	}

	@Override
	public void run() {
		handleEchoClient(this.clntSock, this.logger);
	}

}
