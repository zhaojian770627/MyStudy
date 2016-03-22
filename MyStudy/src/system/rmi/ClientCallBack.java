package system.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientCallBack extends UnicastRemoteObject implements ICallback {

	public ClientCallBack() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tempChanged(String cityName, int temp) throws RemoteException {
		System.out.println("Dynamic Update");
		System.out.println("CityName:" + cityName);
		System.out.println("Temp:" + temp);
	}

}
