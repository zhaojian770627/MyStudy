package complier.book.construction.s21;

import java.io.PrintWriter;

public class I1CodeGen {
	private PrintWriter outFile;
	private I1SymTab st;
	int lindex = 0;

	public I1CodeGen(PrintWriter outFile, I1SymTab st) {
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

	public void endCode() {
		outFile.println();
		emitInstruction("halt");

		for (int i = 0; i < st.getSize(); i++) {
			emitdw(st.getSymbol(i), st.getSymbolValue(i).toString());
		}
	}

	public String getLabel() {
		return "@L" + String.valueOf(lindex++);
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}
}
