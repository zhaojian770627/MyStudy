package system.rmi;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ArithImp1 extends UnicastRemoteObject implements IArith {
	private String objectName;
	
	protected ArithImp1(String s) throws RemoteException {
		super();
		objectName=s;
	}

	@Override
	public int[] add(int[] a, int[] b) throws RemoteException {
		int c[]=new int[10];
		for (int i=0;i<10;i++)
			c[i]=a[i]+b[i];
		return c;
	}
	
	public static void main(String argv[]){
//		RMISecurityManager sm=new RMISecurityManager();
//		System.setSecurityManager(sm);
		try{
			ArithImp1 obj=new ArithImp1("ArithServer");
			Naming.rebind("//127.0.0.1/ArithServer", obj);	
			System.out.println("ArithServer bound in registry");
		}catch(Exception e){
			System.out.println("ArithImpl error:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
