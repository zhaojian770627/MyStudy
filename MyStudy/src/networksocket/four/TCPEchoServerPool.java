package networksocket.four;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPEchoServerPool {

	public static void main(String[] args) throws IOException {
		if(args.length!=2){	// Test for correct # of args
			throw new IllegalAccessError("Parameter(s):<Port> <Threads>");
		}
		
		int echoServerPort=Integer.parseInt(args[0]);
		int threadPoolSize=Integer.parseInt(args[1]);
		
		// Create a server socket to accept client connection requests
		final ServerSocket servSock=new ServerSocket(echoServerPort);
		
		final Logger logger=Logger.getLogger("practical");
		
		// Spawn a fixed number of threads to service clients
		for(int i=0;i<threadPoolSize;i++){
			Thread thread=new Thread(){

				@Override
				public void run() {
					while(true){
						try{
							Socket clntSock=servSock.accept();	// Wait for a connection
							EchoProtocol.handleEchoClient(clntSock, logger);	// Handle it
						}catch(IOException ex){
							logger.log(Level.WARNING, "Client accept failed", ex);
						}
					}
				}
			};
			thread.start();
			logger.info("Create and started Thread="+thread.getName());
		}
	}

}
