package complier.book.construction.s16;

import java.util.HashMap;
import java.util.Iterator;

/*
 * ·ûºÅ±í
 */
public class S5SymTab {
	private HashMap<String, String> symbol;

	public S5SymTab() {
		symbol = new HashMap<>();
	}

	public void enter(String s) {
		if (!symbol.containsKey(s))
			symbol.put(s, "0");
	}

	public void enter(String s, String value) {
		symbol.put(s, value);
	}

	public HashMap<String, String> getSymbol() {
		return symbol;
	}

	public int getSize() {
		return symbol.size();
	}
}
