package system.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class City2Impl extends UnicastRemoteObject implements ICity2 {
	private String cityName;
	public City2Impl() throws RemoteException {
		super();
	}

	public City2Impl(String cityName) throws RemoteException {
		super();
		this.cityName=cityName;		
	}
	@Override
	public int getPopulation() throws RemoteException {
		if(cityName.equals("Toronto"))
			return 10;
		else if(cityName.equals("Ottawa"))
			return 2;
		else
			return 0;
	}

	@Override
	public int getTemperature() throws RemoteException {
		return 1;
	}

}
