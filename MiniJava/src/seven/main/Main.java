package seven.main;

//package Main;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import seven.Assem.Instr;
import seven.Mips.Codegen;
import seven.syntaxtree.Node;
import seven.types.MJClasses;
import seven.types.MJType;
import seven.visitor.Frag;
import seven.visitor.GJFillTable;
import seven.visitor.ProcFrag;
import seven.visitor.Translate;

public class Main {
	static PrintWriter debug = new PrintWriter(System.out);

	public static void main(String[] args) throws java.io.IOException {
		Node root;
		try {
			// from standard input
			if (args.length == 0)
				root = new MiniJavaParser(System.in).Goal();
			// from file i.e arg[0]
			else
				try {
					root = new MiniJavaParser(new java.io.FileInputStream(
							args[0])).Goal();
				} catch (java.io.FileNotFoundException e) {
					System.err.println("Unable to read file " + args[0]);
					return;
				}
		} catch (ParseException e) {
			System.err.println(e.toString());
			return;
		}

		// build a symbol table
		MJType my_classes = new MJClasses();
		root.accept(new GJFillTable(), my_classes);
		// ** a type checking visitor could be inserted here**

		// this visitor is used to translate to IR
		Translate translate = new Translate(new seven.Mips.MipsFrame());
		root.accept(translate, (MJClasses) my_classes);
		
		// the translate visitor has a getResults method which returns
		// an iterator of fragments. The following code illustrates how to
		// iterate through the fragments. I am printing out the IR Tree to
		// stdout here, but one could as well implement a backend to compile
		// the IR to assembly at this point.
		for (Iterator<seven.visitor.Frag> frags = translate.getResults(); frags
				.hasNext();) {

			// get next fragment
			Frag f = frags.next();

			// if the fragment is a ProcFrag i.e one which contains a procedure
			// then I get the map of temps associated with it and print it out.

			if (f instanceof ProcFrag) {
				seven.Temp.TempMap tempmap = new seven.Temp.CombineMap(
						((ProcFrag) f).frame, new seven.Temp.DefaultMap());

				debug.println("PROCEDURE :" + ((ProcFrag) f).frame.name);

				if (((ProcFrag) f).body != null)
					new seven.Tree.Print(debug, tempmap, ((ProcFrag) f).body);
			}
		}
		debug.flush();
	}
}
