package complier.book.construction.s19.R2;

import java.util.ArrayList;
import java.util.List;

/*
 * 符号表
 */
public class R2SymTab {
	// 全局变量
	private List<String> symbolList;
	private List<String> dwValueList;
	private List<Boolean> needsdwList;

	public R2SymTab() {
		symbolList = new ArrayList<String>();
		dwValueList = new ArrayList<String>();
		needsdwList = new ArrayList<Boolean>();
	}

	public void enter(String s) {
		if (!symbolList.contains(s)) {
			symbolList.add(s);
			dwValueList.add("0");
			needsdwList.add(true);
		}
	}

	public void enter(String s, String value) {
		symbolList.add(s);
		dwValueList.add(value);
		needsdwList.add(true);
	}

	public int enter(String s, String v, boolean b) {
		int index = find(s);
		if (index >= 0) // s已在符号表中
			return index; // 是，然后返回其索引

		index = symbolList.size();
		symbolList.add(s);
		dwValueList.add(v);
		needsdwList.add(b);
		return index;
	}

	public int getSize() {
		return symbolList.size();
	}

	public int find(String sym) {
		int i;
		for (i = symbolList.size() - 1; i >= 0; i--) {
			if (symbolList.get(i).equals(sym))
				return i;
		}
		return -1;
	}

	public String getSymbol(int i) {
		return symbolList.get(i);
	}

	public String getdwValue(int i) {
		return dwValueList.get(i);
	}

	public boolean getNeedsdw(int i) {
		return needsdwList.get(i);
	}

	public void setNeedsdw(int i) {
		needsdwList.set(i, true);
	}

	public boolean isldcConstant(int opndIndex) {
		if (isConstant(opndIndex)) {
			String svalue = dwValueList.get(opndIndex);
			int ivalue = Integer.parseInt(svalue);
			if (ivalue >= 0 && ivalue <= 4095)
				return true;
		}
		return false;
	}

	public boolean isConstant(int opndIndex) {
		String symbol = symbolList.get(opndIndex);
		if (symbol.substring(0, 1).equals("@")) {
			String second = symbol.substring(1, 2);
			if (second.equals("_")) {
				String third = symbol.substring(2, 3);
				if (Character.isDigit(third.charAt(0)))
					return true;
				else
					return false;
			} else if (Character.isDigit(second.charAt(0)))
				return true;

		}
		return false;
	}
}
