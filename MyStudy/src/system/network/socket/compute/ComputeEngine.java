package system.network.socket.compute;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ComputeEngine extends Thread {
	public static final int EXEC_PORT = 5000;
	protected ServerSocket listen;

	public ComputeEngine() {
		try {
			listen = new ServerSocket(EXEC_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Exec server listening on port:" + EXEC_PORT);
		this.start();
	}

	@Override
	public void run() {
		try {
			while (true) {
				Socket cl = listen.accept();
				Connect cc = new Connect(cl);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String argv[]){
//		RunnerSecurityManager RSM;
//		try{
//			RSM=new RunnerSecurityManager();
//			System.setSecurityManager(RSM);
//		}catch(SecurityException se){
//			se.printStackTrace();
//		}
		new ComputeEngine();
	}
}
