package networksocket.three;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class VoteServerTCP {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) { // Test for correct # of args
			throw new IllegalArgumentException("Parameter(s):<Port>");
		}

		int port = Integer.parseInt(args[0]);

		ServerSocket servSock = new ServerSocket(port);
		// Change Bin to Text on both client and server for different encoding
		VoteMsgCoder coder = new VoteMsgBinCoder();
		VoteService service = new VoteService();

		while (true) {
			Socket clntSock = servSock.accept();
			System.out.println("Handling clien at " + clntSock.getRemoteSocketAddress());
			// Change Length to Delim for a different framing strategy
			Framer framer = new LengthFramer(clntSock.getInputStream());
			try {
				byte[] req;
				while ((req = framer.nextMsg()) != null) {
					System.out.println("Received messgage(" + req.length + " bytes)");
					VoteMsg responseMsg = service.handleRequest(coder.fromWire(req));
					framer.frameMsg(coder.toWire(responseMsg), clntSock.getOutputStream());
				}
			} catch (IOException ioe) {
				System.out.println("Closeing connection");
			} finally {
				System.out.print("Closing connection");
				clntSock.close();
			}
		}
	}

}
