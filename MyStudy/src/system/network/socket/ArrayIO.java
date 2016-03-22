package system.network.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;

public class ArrayIO {
	public ArrayIO(){
		
	}
	
	public void writeArray(DataOutputStream out,int arr[]) throws IOException
	{
		for(int i=0;i<arr.length;i++){
			out.write(arr[i]);
		}
	}
	
	public int[] readArray(BufferedReader br) throws IOException 
	{
		int c[] =new int[10];
		for(int h=0;h<10;h++){
			c[h]=br.read();
		}
		return c;
	}
}
