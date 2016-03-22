package system.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICallback extends Remote {
	void tempChanged(String cityName, int temp) throws RemoteException;
}
