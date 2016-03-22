package system.network.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NsLookup {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length==0){
			System.out.println("Usage:java NsLookup <hostname>");
			System.exit(0);
		}
		String host=args[0];
		InetAddress address=null;
		try{
			address=InetAddress.getByName(host);			
		}catch(UnknownHostException e){
			System.out.println("Unknown host");
			System.exit(0);
		}
		System.out.println(address.getHostAddress());
	}

}
