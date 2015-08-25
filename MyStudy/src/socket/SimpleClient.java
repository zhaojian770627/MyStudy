package socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class SimpleClient {
	public final static int REMOTE_PORT = 5000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Socket cl = null, cl2 = null;
		BufferedReader is = null;
		DataOutputStream os = null;
		BufferedReader stdin = new BufferedReader(new InputStreamReader(
				System.in));
		String userInput = null;
		String output = null;
		try {
			cl = new Socket("127.0.0.1", REMOTE_PORT);
			is = new BufferedReader(new InputStreamReader(cl.getInputStream()));
			os = new DataOutputStream(cl.getOutputStream());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		try {
			System.out.print("Please input a keywork:");
			userInput = stdin.readLine();
			os.writeBytes(userInput + "\n");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		try {
			output = is.readLine();
			System.out.println("Got from server:" + output);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			is.close();
			os.close();
			cl.close();
		} catch (IOException x) {
			x.printStackTrace();
		}
	}

}
