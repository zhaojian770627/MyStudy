package networksocket;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DelimFramer implements Framer {

	private InputStream in;	// data source
	private static final byte DELIMITER='\n';	// message delimiter
	
	public DelimFramer(InputStream in){
		this.in=in;
	}
	@Override
	public void frameMsg(byte[] message, OutputStream out) throws IOException {
		// ensure that the message does not contain the delimiter
		for(byte b:message){
			if(b==DELIMITER){
				throw new IOException("Message contains delimiter");
			}
		}
		out.write(message);
		out.write(DELIMITER);
		out.flush();
	}

	@Override
	public byte[] nextMsg() throws IOException {
		ByteArrayOutputStream messageBuffer=new ByteArrayOutputStream();
		int nextByte;
		
		// fetch btes until find delimiter
		while((nextByte=in.read())!=DELIMITER){
			if(nextByte==-1){ // if no byte read
				if(messageBuffer.size()==0) 
					return null;	// if no byte read
				else		// if bytes followed by end of stream:framing error
					throw new EOFException("non-empty message without delimiter");
			}
			messageBuffer.write(nextByte);	// write byte to buffer
		}
		
		return messageBuffer.toByteArray();
	}
	
}
