package complier.book.construction.s19.R2;

import java.util.ArrayList;
import java.util.List;

/*
 * 符号表
 */
public class R2SymTab {
	// 全局变量
	private List<String> symbol;
	private List<String> dwValue;
	private List<Boolean> needsdw;

	public R2SymTab() {
		symbol = new ArrayList<String>();
		dwValue = new ArrayList<String>();
		needsdw = new ArrayList<Boolean>();
	}

	public void enter(String s) {
		if (!symbol.contains(s)) {
			symbol.add(s);
			dwValue.add("0");
			needsdw.add(true);
		}
	}

	public void enter(String s, String value) {
		symbol.add(s);
		dwValue.add(value);
		needsdw.add(true);
	}

	public int enter(String s, String v, boolean b) {
		int index = find(s);
		if (index >= 0) // s已在符号表中
			return index; // 是，然后返回其索引

		index = symbol.size();
		symbol.add(s);
		dwValue.add(v);
		needsdw.add(b);
		return index;
	}

	public int getSize() {
		return symbol.size();
	}

	public int find(String sym) {
		int i;
		for (i = symbol.size() - 1; i >= 0; i--) {
			if (symbol.get(i).equals(sym))
				return i;
		}
		return -1;
	}

	public String getSymbol(int i) {
		return symbol.get(i);
	}

	public String getSymbolValue(int i) {
		return dwValue.get(i);
	}

	public boolean getNeedsdw(int i) {
		return needsdw.get(i);
	}

	public void setNeedsdw(int i) {
		needsdw.set(i, true);
	}
}
