package socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

public class HttpConnect extends Thread {
	Socket client;
	DataInputStream is;
	DataOutputStream os;
	
	public HttpConnect(Socket s){
		client=s;
		try {
			is = new DataInputStream(client.getInputStream());
			os = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			try {
				client.close();
				System.out.println("Error getting socket streams:" + e);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return;
		}
		this.start();
	}
	
	@Override
	public void run() {
		try{
			String request=is.readLine();
			System.out.println("Request:"+request);
			StringTokenizer st=new StringTokenizer(request);
			if((st.countTokens()>=2)&& st.nextToken().equals("GET")){
				if((request=st.nextToken()).startsWith("/"))
					request=request.substring(1);
				if(request.endsWith("/")||request.equals(" "))
					request=request+"index.html";
				
				File f=new File(request);
				shipDocuent(os,f);
			}else
			{
				os.writeBytes("400 Bad Request");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	private void shipDocuent(DataOutputStream os2, File f) {
		try{
			DataInputStream in=new DataInputStream(new FileInputStream(f));
			int len=(int)f.length();
			byte buf[]=new byte[len];
			in.readFully(buf);
			os.writeBytes("HTTP/1.0 200 OK \r\n");
			os.writeBytes("Content-Length:"+buf.length+"\r\n");
			os.writeBytes("Content-Type:text\\html \r\n\r\n");
			os.write(buf);
			os.flush();
			in.close();
		}catch(FileNotFoundException e){
			try {
				os.writeBytes("404 Not Found");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}catch(IOException ex){
			System.out.println("Error writing..."+ex);
		}
	}
}
