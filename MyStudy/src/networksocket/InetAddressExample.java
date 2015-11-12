package networksocket;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressExample {

	public static void main(String[] args) throws SocketException, UnknownHostException {
		Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
		if (interfaceList == null) {
			System.out.println("--No interfaces found--");
		} else {
			while (interfaceList.hasMoreElements()) {
				NetworkInterface iface = interfaceList.nextElement();
				System.out.println("Interface " + iface.getName() + ":");
				Enumeration<InetAddress> addrList = iface.getInetAddresses();
				if (!addrList.hasMoreElements()) {
					System.out.println("\t(No addresses for this interface)");
				}
				while (addrList.hasMoreElements()) {
					InetAddress address = addrList.nextElement();
					System.out.print("\tAddress " + ((address instanceof Inet4Address ? "(v4)"
							: (address instanceof Inet6Address ? "(v6)" : "(?)"))));
					System.out.println(": "+address.getHostAddress());
				}
			}
		}
		
		// Get name(s)/address(es) of hosts given on command line
		for(String host:args)
		{
			System.out.println(host+":");
			InetAddress[] addressList=InetAddress.getAllByName(host);
			for(InetAddress address:addressList);
			for(InetAddress address:addressList){
				System.out.println("\t"+address.getHostName()+"/"+address.getHostAddress());
			}
		}
	}
}
