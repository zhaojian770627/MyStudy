package socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetName {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		InetAddress host=null;
		host=InetAddress.getLocalHost();
		System.out.println(host.getHostAddress());
		System.out.println(host.getHostName());
		
		byte ip[]=host.getAddress();
		for(int i=0;i<ip.length;i++){
			if(i>0)
				System.out.print(".");
			System.out.print(ip[i]&0xff);
		}
	}

}
