package system.network.socket.sign;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;

public class SignServer implements Cloneable,Runnable {
	ServerSocket service=null;
	Socket clientSocket=null;
	ObjectInputStream ois=null;
	Thread worker=null;
	KeyPairGenerator kgen;
	KeyPair kpair;
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		SignServer serv=new SignServer();
		serv.startServer();
	}

	private synchronized void startServer() throws IOException {
		if(worker==null){
			service=new ServerSocket(4000);
			worker=new Thread(this);
			worker.start();
		}
		
	}

	@Override
	public void run() {
		Socket client=null;
		if(service!=null){
			while(true){
				try{
					client=service.accept();
					SignServer newServer=(SignServer) clone();
					newServer.service=null;
					newServer.clientSocket=client;
					newServer.worker=new Thread(newServer);
					newServer.worker.start();
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}else{
			perform(clientSocket);
		}
	}

	private void perform(Socket client) {
		try{
			ois=new ObjectInputStream(clientSocket.getInputStream());
			//Read object from client.
			SignedObject obj=(SignedObject) ois.readObject();
			//Generate object's signature.
			Signature sig=Signature.getInstance("SHA/DSA");
			sig.initVerify(obj.pub);
			sig.update(obj.b);
			
			//Verify the signature.
			boolean valid=sig.verify(obj.sig);
			if(valid){
				System.out.println("Signature is valid");
			}else{
				System.out.println("Signature is not valid...spy!");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// Close streams and connection
		try{
			ois.close();
			clientSocket.close();
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}

}
