package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class CalcClient {
	public final static int REMOTE_PORT=3333;
	static int a[]={1,2,3,4,5,6,7,8,9,10};
	static int b[]={5,5,5,5,5,5,5,5,5,5};
	
	public static void main(String argv[]) throws UnknownHostException, IOException
	{
		Socket cl=null,cl2=null;
		BufferedReader is=null;
		DataOutputStream os=null;
		ArrayIO aio=new ArrayIO();
		cl=new Socket("127.0.0.1",REMOTE_PORT);
		is=new BufferedReader(new InputStreamReader(cl.getInputStream()));
		os=new DataOutputStream(cl.getOutputStream());
		
		aio.writeArray(os, a);
		aio.writeArray(os, b);
		
		int result[]=new int[10];
		result=aio.readArray(is);
		System.out.println("The surn of the two arrays:");
		for(int j=0;j<result.length;j++){
			System.out.print(result[j]+" ");
		}
		System.out.println(" ");
		
		is.close();
		os.close();
		cl.close();
	}
}
