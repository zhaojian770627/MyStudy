package complier.book.construction.s16;

import java.io.PrintWriter;

public class S5CodeGen {
	private PrintWriter outFile;
	private S5SymTab st;
	int lindex = 0;

	public S5CodeGen(PrintWriter outFile, S5SymTab st) {
		this.outFile = outFile;
		this.st = st;
	}

	public void emitLabel(String label) {
		outFile.printf("%-9s:%n", label);
	}

	public void emitInstruction(String op) {
		outFile.printf("\t%-4s%n", op);
	}

	public void emitInstruction(String op, String opnd) {
		outFile.printf("\t%-4s\t%s\n", op, opnd);
	}

	public void emitdw(String label, String value) {
		outFile.printf("%-9s dw\t%s%n", label + ":", value);
	}

	public void push(int p) {
		if (st.getCategory(p) != st.GLOBALVARIABLE)
			outFile.printf("\tp\t%s\n", st.getSymbol(p));// global
		else
			outFile.printf("\tpr\t%s\n", st.getRelAdd(p));// local
	}
	
	public void pushAddress(int p) {
		if (st.getCategory(p) != st.GLOBALVARIABLE)
			outFile.printf("\tpc\t%s\n", st.getSymbol(p));// global
		else
			outFile.printf("\tcora\t%s\n", st.getRelAdd(p));// local
	}

	public void endCode() {
		// outFile.println();
		// emitInstruction("halt");

		for (int i = 0; i < st.getSize(); i++) {
			if (st.getCategory(i) == st.FUNCTIONCALL) {
				outFile.printf("\textern \t%s\n", st.getSymbol(i));
			}
		}
	}

	public String getLabel() {
		return "@L" + String.valueOf(lindex++);
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}
}
