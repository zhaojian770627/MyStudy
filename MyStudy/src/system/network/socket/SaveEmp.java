package system.network.socket;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SaveEmp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Employee emily=new Employee("E.Jordan", 27, 35000);
		Employee john=new Employee("J.McDonald", 29, 39000);
		FileOutputStream fos=new FileOutputStream("db");
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		oos.writeObject(emily);
		oos.writeObject(john);
		oos.flush();
	}

}
