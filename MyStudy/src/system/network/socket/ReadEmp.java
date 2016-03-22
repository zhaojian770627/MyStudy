package system.network.socket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadEmp {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		FileInputStream fis=new FileInputStream("db");
		ObjectInputStream ois=new ObjectInputStream(fis);
		Employee emily=(Employee) ois.readObject();
		Employee john=(Employee) ois.readObject();
		emily.print();
		john.print();
	}

}
