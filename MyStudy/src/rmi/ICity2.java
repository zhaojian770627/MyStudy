package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICity2 extends Remote {
	int getPopulation() throws RemoteException;
	int getTemperature() throws RemoteException;
	
}
