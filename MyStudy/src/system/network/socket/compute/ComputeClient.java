package system.network.socket.compute;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ComputeClient {
	public final static int REMOTE_PORT=5000;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String host=args[0];
		String url=args[1];
		Socket cl=null,cl2=null;
		BufferedReader is=null;
		DataOutputStream os=null;
		
		try{
			cl=new Socket(host,REMOTE_PORT);
			is=new BufferedReader(new InputStreamReader(cl.getInputStream()));
			os=new DataOutputStream(cl.getOutputStream());
			System.out.println("Connection is fine...");
		}catch(UnknownHostException el){
			el.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			os.writeUTF(url);			
		}catch(IOException ex){
			ex.printStackTrace();
		}
		
		String outline;
		try{
			while((outline=is.readLine())!=null){
				System.out.println("Remote:"+outline);
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		try{
			is.close();
			os.close();
			cl.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
