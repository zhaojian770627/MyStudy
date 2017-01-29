package complier.book.construction.s19;

import java.util.ArrayList;
import java.util.List;

/*
 * 符号表
 */
public class R1SymTab {
	// 全局变量
	private List<String> symbol;
	private List<String> dwValue;

	public R1SymTab() {
		symbol = new ArrayList<String>();
	}

	public void enter(String s) {
		if (!symbol.contains(s)) {
			symbol.add(s);
			dwValue.add("0");
		}
	}

	public void enter(String s, String value) {
		symbol.add(s);
		dwValue.add(value);
	}

	public int getSize() {
		return symbol.size();
	}
	
	public String getSymbol(int i) {
		return symbol.get(i);
	}

	public String getSymbolValue(int i) {
		return dwValue.get(i);
	}
}
