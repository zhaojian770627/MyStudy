package complier.book.construction.s21.I1;

import java.util.ArrayList;
import java.util.List;

/*
 * ·ûºÅ±í
 */
public class I1SymTab {
	private List<String> symbol;
	private List<Integer> symbolValue;

	public I1SymTab() {
		symbol = new ArrayList<>();
		symbolValue = new ArrayList<>();
	}

	public int enter(String sym) {
		int i = find(sym);
		if (i == -1) {
			symbol.add(sym);
			symbolValue.add(0);
			i = symbol.size() - 1;
		}
		return i;

	}

	public int find(String sym) {
		int i;
		for (i = symbol.size() - 1; i >= 0; i--) {
			if (symbol.get(i).equals(sym))
				return i;
		}
		return -1;
	}

	public void enter(String sym, String value) {
		int i = find(sym);
		if (i == -1) {
			symbol.add(sym);
			symbolValue.add(0);
		}
	}

	public String getSymbol(int i) {
		return symbol.get(i);
	}

	public Integer getSymbolValue(int i) {
		return symbolValue.get(i);
	}

	public void setSymbolValue(int i, Integer v) {
		symbolValue.set(i, v);
	}

	public int getSize() {
		return symbol.size();
	}
}
