package complier.book.construction.s19;

import java.io.PrintWriter;

public class R1CodeGen {
	private PrintWriter outFile;
	private R1SymTab st;
	int tempIndex = 0;

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
			if (st.getNeedsdw(i)) {
				key = st.getSymbol(i);
				value = st.getSymbolValue(i);
				emitdw(key, value);
			}
		}
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}

	public void assign(int left, int expVal) {
		String leftSym = st.getSymbol(left);
		String rightValue = st.getSymbol(expVal);
		emitInstruction("ld", leftSym);
		emitInstruction("st", rightValue);
	}

	public void println(int expVal) {
		String sym = st.getSymbol(expVal);
		emitInstruction("ld", sym);
		emitInstruction("dout");
		emitInstruction("ldc", "'\\n'");
		emitInstruction("aout");
	}

	public int add(int left, int right) {
		emitInstruction("ld", st.getSymbol(left));
		emitInstruction("add", st.getSymbol(right));
		int temp = getTemp();
		emitInstruction("st", st.getSymbol(temp));
		return temp;
	}

	private int getTemp() {
		String temp = "@t" + tempIndex++;
		return st.enter(temp, "0", true);
	}

	public int mult(int left, int right) {
		emitInstruction("ld", left);
		emitInstruction("mult", right);
		int temp = getTemp();
		emitInstruction("st", st.getSymbol(temp));
		return temp;
	}

	private void emitInstruction(String op, int opndIndex) {
		emitInstruction("ld", st.getSymbol(opndIndex));
	}
}
