package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class CalcServer {
	public static final int MATH_PORT = 3333;
	ServerSocket listen;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		(new CalcServer()).start();
	}

	void start() throws IOException {
		System.out.println("Server started");
		listen = new ServerSocket(MATH_PORT);
		while (true) {
			Socket client = listen.accept();
			new Connects(client);
		}
	}

	class Connects extends Thread {
		Socket client;
		BufferedReader is;
		DataOutputStream os;
		ArrayIO aio = new ArrayIO();
		ArrayMath am = new ArrayMath();

		public Connects(Socket s) {
			client = s;
			try {
				is = new BufferedReader(new InputStreamReader(
						client.getInputStream()));
				os = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				try {
					client.close();
					System.out.println("Error getting socket streams:" + e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;

			}
			this.start();
		}
		
		@Override
		public void run() {
			int a1[]= new int[10];
			int a2[]=new int[10];
			try{
				a1=aio.readArray(is);
				a2=aio.readArray(is);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			int r[]=new int[10];
			r=am.addArray(a1, a2);
			try {
				aio.writeArray(os, r);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
