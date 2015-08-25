package MiniJava.javacc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			File f = new File("D:\\WorkSpace\\javacc\\Factorial.java");
			FileInputStream fs = new FileInputStream(f);
			new MiniJavaParser(fs).Goal();
			System.out.println("Lexical analysis successfull");
		} catch (ParseException e) {
			System.out.println("Lexer Error : \n" + e.toString());
		}
	}
}
