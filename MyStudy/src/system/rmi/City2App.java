package system.rmi;

import java.rmi.Naming;
import java.rmi.Remote;

public class City2App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Remote obj = null;
		ICityFactory factory;
		ICity2 ottawa = null;
		ICity2 toronto = null;

		int ottawaPopulation = 0;
		int torontoPopulation = 0;

		try {
			obj = Naming.lookup("//127.0.0.1/CityFactory");
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(obj.getClass());
//		if (obj instanceof ICityFactory) {
			factory = (ICityFactory) obj;
//		} else
//			return;
		System.out.println(factory.getClass());
		try {
			ottawa = factory.getCityServer("Ottawa");
			toronto = factory.getCityServer("Toronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			ottawaPopulation = ottawa.getPopulation();
			torontoPopulation = toronto.getPopulation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The population of Ottawa is:"+ottawaPopulation);
		System.out.println("The population of Toronto is:"+torontoPopulation);
	}
}
