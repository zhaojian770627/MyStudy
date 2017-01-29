package complier.book.construction.s19;

import java.io.PrintWriter;

public class R1CodeGen {
	private PrintWriter outFile;
	private R1SymTab st;
	int lindex = 0;

	public R1CodeGen(PrintWriter outFile, R1SymTab st) {
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

		String key, value;
		for (int i = 0; i < st.getSize(); i++) {
			key = st.getSymbol(i);
			value = st.getSymbolValue(i);
			emitdw(key, value);
		}
	}

	public String getLabel() {
		return "@L" + String.valueOf(lindex++);
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}
}
