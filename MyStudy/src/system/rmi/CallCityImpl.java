package system.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class CallCityImpl extends UnicastRemoteObject implements ICallCity {
	Vector list = new Vector();

	public CallCityImpl() throws RemoteException {
		super();
	}

	@Override
	public int getPopulation(String cityName) throws RemoteException {
		setTemp("ab", 0);
		return 0;
	}

	@Override
	public int getTemperature(String cityName) throws RemoteException {
		return 0;
	}

	@Override
	public void register(ICallback cb) throws RemoteException {
		list.addElement(cb);
	}

	public void setTemp(String cityName, int tmp) throws RemoteException {
		for (int i = 0; i < list.size(); i++) {
			ICallback cb = (ICallback) list.elementAt(i);
			cb.tempChanged(cityName, tmp);
		}
	}

	public static void main(String argv[]) {
		try {
			CallCityImpl obj = new CallCityImpl();
			Naming.rebind("//127.0.0.1/CallCityServer", obj);
			System.out.println("CallCityServer bound in registry");
		} catch (Exception e) {
			System.out.println("CallCityServer error:" + e.getMessage());
			e.printStackTrace();
		}
	}

}
