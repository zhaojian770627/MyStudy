package rmi;

import java.rmi.Naming;

public class ArithApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int a[]={1,2,3,4,5,6,7,8,9,10};
		int b[]={1,2,3,4,5,6,7,8,9,15};
		int result[]=new int[10];
		try{
			IArith obj=(IArith)Naming.lookup("//127.0.0.1/ArithServer");
			result=obj.add(a,b);
		}catch(Exception e)
		{
			System.out.println("ArithApp:"+e.getMessage());
			e.printStackTrace();
		}
		System.out.print("The sum=");
		for(int i=0;i<result.length;i++){
			System.out.println(result[i]+" ");
		}
		System.out.println();
	}

}
