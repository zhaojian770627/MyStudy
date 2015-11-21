package networksocket;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPEchoClientTimeout {
	private static final int TIMEOUT=3000;	// Resend timeout(milliseconde)
	private static final int MAXTRIES= 5;	// Maximum retransmissions
	
	public static void main(String[] args) throws IOException, IllegalAccessException {
		if((args.length<2)|| (args.length>3)){	// Test for correct # of args
			throw new IllegalAccessException("Parameter(s):<Server><Word>[<Port>]");
		}
		
		InetAddress serverAddress=InetAddress.getByName(args[0]);	// Server address
		// Convert the argument String to bytes using the default encoding
		byte[] bytesToSend=args[1].getBytes();
		
		int servPort=(args.length==3)?Integer.parseInt(args[2]):7;
		DatagramSocket socket=new DatagramSocket();
		
		socket.setSoTimeout(TIMEOUT);	//Maxinum receive blocking time (milliseconds)
		
		DatagramPacket sendPacket=new DatagramPacket(bytesToSend,bytesToSend.length,serverAddress,servPort);	// Sending packet
		DatagramPacket receivePacket=new DatagramPacket(new byte[bytesToSend.length],bytesToSend.length);		// Receiving packet
		
		int tries=0;	// Packets may be lost, so we have to keep trying
		boolean receivedResponse=false;
		do{
			socket.send(sendPacket);	// Send the echo string
			try{
				socket.receive(receivePacket);	// Attempt echo reply reception
				
				if(!receivePacket.getAddress().equals(serverAddress)){	// Check source
					throw new IOException("Received packet from an unknown source");
				}
				
				receivedResponse=true;
			}catch(InterruptedIOException e){	// We did not get anything
				tries+=1;
				System.out.println("Timed ot,"+(MAXTRIES-tries)+" more tries...");
			}
		}while((!receivedResponse) && (tries<MAXTRIES));
		
		if(receivedResponse){
			System.out.println("Received:"+new String(receivePacket.getData()));
		}else{
			System.out.println("No response -- giving up.");
		}
		socket.close();
	}

}
