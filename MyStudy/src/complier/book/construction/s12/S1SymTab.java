package complier.book.construction.s12;

import java.util.ArrayList;

/*
 * ���ű�
 */
public class S1SymTab {
	private ArrayList<String> symbol;

	public S1SymTab() {
		symbol = new ArrayList<>();
	}

	public void enter(String s) {
		int index = symbol.indexOf(s);

		if (index < 0)
			symbol.add(s);
	}

	public String getSymbol(int index) {
		return symbol.get(index);
	}

	public int getSize() {
		return symbol.size();
	}
}
