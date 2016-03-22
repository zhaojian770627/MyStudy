package system.rmi;

import java.rmi.Naming;

public class CityApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int pop = 0;
		try {
			ICity obj = (ICity) Naming.lookup("//127.0.0.1/CityServer");
			pop = obj.getPopulation("Toronto");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The population of Toronto is:" + pop);
	}

}
