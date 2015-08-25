package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CityFactoryImpl extends UnicastRemoteObject implements
		ICityFactory {

	protected CityFactoryImpl() throws RemoteException {
		super();
	}

	@Override
	public City2Impl getCityServer(String cityName) throws RemoteException {
		City2Impl cityServer = new City2Impl(cityName);
		return cityServer;
	}

	public static void main(String argv[]) {
		try {
			CityFactoryImpl obj = new CityFactoryImpl();
			Naming.bind("//127.0.0.1/CityFactory", obj);
			System.out.println("CityFactory bound in registry");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
