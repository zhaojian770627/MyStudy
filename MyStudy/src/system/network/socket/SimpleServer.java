package system.network.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int TESTPORT = 5000;
		ServerSocket checkServer = null;
		String line;
		BufferedReader is = null;
		DataOutputStream os = null;
		Socket clientSocket = null;

		try {
			checkServer = new ServerSocket(TESTPORT);
			System.out.println("SimpleServer started...");
		} catch (IOException e) {
			System.out.println(e);
			return;
		}

		try {
			clientSocket = checkServer.accept();
			is = new BufferedReader(new InputStreamReader(
					clientSocket.getInputStream()));
			os = new DataOutputStream(clientSocket.getOutputStream());
		} catch (Exception ei) {
			ei.printStackTrace();
		}
		
		try{
			line=is.readLine();
			System.out.println("we received:"+line);
			if(line.compareTo("Greetings")==0){
				os.writeBytes("we received:"+line);
			}else{
				os.writeBytes("Sorry,you don't speak my protocol");
			}
		}catch(IOException e){
			System.out.println(e);
		}
		
		try{
			os.close();
			is.close();
			clientSocket.close();
		}catch(IOException ic)
		{
			ic.printStackTrace();
		}
	}

}
