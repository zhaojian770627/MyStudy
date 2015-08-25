package javacc.a;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			File f = new File("d:\\Factorial.java");
			FileInputStream fis = new FileInputStream(f);
			new MiniJavaParser(fis).Goal();
			System.out.println("Lexical analysis successfull");
		} catch (ParseException e) {
			System.out.println("Lexer Error : \n" + e.toString());
		}
	}
}
