package system.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICallCity extends Remote {
	int getPopulation(String cityName) throws RemoteException;

	int getTemperature(String cityName) throws RemoteException;

	void register(ICallback cb) throws RemoteException;
}
