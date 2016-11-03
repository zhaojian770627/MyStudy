package complier.book.construction.s12;

import java.io.PrintWriter;

public class S1CodeGen {
	private PrintWriter outFile;
	private S1SymTab st;
	int lindex = 0;

	public S1CodeGen(PrintWriter outFile, S1SymTab st) {
		this.outFile = outFile;
		this.st = st;
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

		int size = st.getSize();
		// 发布dw为符号表中的每个符号
		for (int i = 0; i < size; i++)
			emitdw(st.getSymbol(i), "0");
	}

	public String getLabel() {
		return "@L" + String.valueOf(lindex++);
	}
}
