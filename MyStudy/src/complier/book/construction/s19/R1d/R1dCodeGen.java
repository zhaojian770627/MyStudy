package complier.book.construction.s19.R1d;

import java.io.PrintWriter;

public class R1dCodeGen {
	private PrintWriter outFile;
	private R1dSymTab st;
	int ac = -1;
	int tempIndex = 0;

	public R1dCodeGen(PrintWriter outFile, R1dSymTab st) {
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

	private void emitInstruction(String op, int opndIndex) {
		if (st.isConstant(opndIndex))
			st.setNeedsdw(opndIndex);
		emitInstruction(op, st.getSymbol(opndIndex));
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
				value = st.getdwValue(i);
				emitdw(key, value);
			}
		}
	}

	public void emitString(String msg) {
		outFile.printf(msg);
	}

	public void assign(int left, int expVal) {
		if (ac != expVal)
			emitLoad(expVal);
		freeTemp(expVal);
		emitInstruction("st", left);
		ac = left;
	}

	private void emitLoad(int opndIndex) {
		if (st.isldcConstant(opndIndex))
			emitInstruction("ldc", st.getdwValue(opndIndex));
		else
			emitInstruction("ld", opndIndex);
	}

	public void println(int expVal) {
		emitLoad(expVal);
		emitInstruction("dout");
		emitInstruction("ldc", "'\\n'");
		emitInstruction("aout");
	}

	public int add(int left, int right) {
		if (ac == left)
			emitInstruction("add", right);
		else if (ac == right)
			emitInstruction("add", left);
		else {
			if (st.isTemp(ac)) {
				emitInstruction("st", ac);
				st.setNeedsdw(ac);
			}
			emitLoad(left);
			emitInstruction("add", right);
		}
		freeTemp(left);
		freeTemp(right);
		int temp = getTemp();
		// emitInstruction("st", temp); // ?
		ac = temp;
		return temp;
	}

	private int getTemp() {
		String temp = "@t" + tempIndex++;
		return st.enter(temp, "0", false);
	}

	public int mult(int left, int right) {
		if (ac == left)
			emitInstruction("mult", right);
		else if (ac == right)
			emitInstruction("mult", left);
		else {
			if (st.isTemp(ac)) {
				emitInstruction("st", ac);
				st.setNeedsdw(ac);
			}
			emitLoad(left);
			emitInstruction("mult", right);
		}
		freeTemp(left);
		freeTemp(right);
		int temp = getTemp();
		// emitInstruction("st", temp); //?
		return temp;
	}

	public void freeTemp(int opndIndex) {
		if (st.isTemp(opndIndex))
			tempIndex--;
	}
}
