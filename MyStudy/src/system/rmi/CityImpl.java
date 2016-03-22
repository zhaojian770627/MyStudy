package system.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CityImpl extends UnicastRemoteObject implements ICity {
	private String name;

	protected CityImpl(String name) throws RemoteException {
		super();
		this.name = name;
	}

	@Override
	public int getPopulation(String cityName) throws RemoteException {
		if (cityName.equals("Toronto"))
			return 10;
		else if (cityName.equals("Ottawa"))
			return 2;
		else
			return 0;
	}

	@Override
	public int getTemperature(String cityName) throws RemoteException {
		return 1;
	}

	public static void main(String argv[]) {
		try {
			CityImpl obj = new CityImpl("CityServer");
			Naming.bind("//127.0.0.1/CityServer", obj);
			System.out.println("CityServer bound in registry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
