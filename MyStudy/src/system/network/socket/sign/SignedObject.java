package system.network.socket.sign;

import java.io.Serializable;
import java.security.PublicKey;

public class SignedObject implements Serializable {
	byte b[];
	byte sig[];
	PublicKey pub;
	
	public SignedObject(byte b[],byte sig[],PublicKey pub){
		this.b=b;
		this.sig=sig;
		this.pub=pub;
	}
}
