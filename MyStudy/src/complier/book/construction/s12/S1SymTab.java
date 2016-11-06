package complier.book.construction.s12;

import java.util.HashMap;
import java.util.Iterator;

/*
 * ·ûºÅ±í
 */
public class S1SymTab {
	private HashMap<String, String> symbol;

	public S1SymTab() {
		symbol = new HashMap<>();
	}

	public void enter(String s) {
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
