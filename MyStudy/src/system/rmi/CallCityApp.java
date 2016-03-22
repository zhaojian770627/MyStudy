package system.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallCityApp    {
	public CallCityApp() throws RemoteException {
		super();
	}

	/**
	 * @param args
	 * @throws RemoteException 
	 * @throws NotBoundException 
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
		int pop = 0;
		ICallback c = new ClientCallBack();
		ICallCity obj;
//		try {
			obj = (ICallCity) Naming.lookup("//127.0.0.1/CallCityServer");
			pop = obj.getPopulation("Toronto");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		obj.register(c);
		obj.getPopulation("ddddd");
		System.out.println("The population of Toronto is:" + pop);
	}
}
