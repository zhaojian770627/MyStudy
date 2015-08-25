package javacc.c;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javacc.c.jtb.syntaxtree.Program;
import javacc.c.jtb.visitor.BuildSymbolTableVisitor;
import javacc.c.jtb.visitor.TypeCheckVisitor;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		try {
			File f = new File("d:\\Factorial.java");
			FileInputStream fis = new FileInputStream(f);
			Program root = new MiniJavaParser(fis).Goal();
			//root.accept(new PrettyPrintVisitor());
			//root.accept(new TypeCheckVisitor());
			
			BuildSymbolTableVisitor v1 = new BuildSymbolTableVisitor();
			root.accept(new TypeCheckVisitor(v1.getSymTab()));
		} catch (ParseException e) {
			System.out.println(e.toString());
		}
	}
}