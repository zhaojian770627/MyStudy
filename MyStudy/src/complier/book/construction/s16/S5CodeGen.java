package complier.book.construction.s16;

import java.io.PrintWriter;
import java.util.Map.Entry;

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

	public void endCode() {
		outFile.println();
		emitInstruction("halt");

		for (Entry<String, String> entry : st.getSymbol().entrySet()) {
			emitdw(entry.getKey(), entry.getValue());
		}
	}

	public String getLabel() {
		return "@L" + String.valueOf(lindex++);
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}
}
