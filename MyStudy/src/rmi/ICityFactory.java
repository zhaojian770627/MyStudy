package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICityFactory extends Remote {
	ICity2 getCityServer(String cityName) throws RemoteException;
}
