package complier.book.construction.s16;

import java.util.ArrayList;
import java.util.List;

/*
 * 符号表
 */
public class S5SymTab implements S5Constants {

	// 全局变量
	private List<String> symbol;
	private List<String> symbolValue;

	private List<Integer> relAdd;
	private List<Integer> category;

	public S5SymTab() {
		symbol = new ArrayList<String>();
		symbolValue = new ArrayList<String>();

		relAdd = new ArrayList<Integer>();
		category = new ArrayList<Integer>();
	}

	public void enter(String s) {
		if (!symbol.contains(s)) {
			symbol.add(s);
			symbolValue.add("0");
		}
	}

	public void enter(String sym, int ra, int cat) {
		int i = find(sym);
		if (i == -1) {
			symbol.add(sym);
			symbolValue.add("0");

			relAdd.add(ra);
			category.add(cat);
		} else {
			if (cat == FUNCTIONDEFINITION && category.get(i) == FUNCTIONCALL) {
				category.remove(i);
				category.add(i, FUNCTIONDEFINITION);
			} else if (cat == LOCAL && (category.get(i) == GLOBALVARIABLE || category.get(i) == EXTERNVARIABLE)) {
				symbol.add(sym);
				symbolValue.add("0");
				relAdd.add(ra);
				category.add(cat);
			} else
				throw new RuntimeException("enter error");
		}
	}

	public int find(String sym) {
		int i;
		for (i = symbol.size() - 1; i >= 0; i--) {
			if (symbol.get(i).equals(sym))
				return i;
		}
		return -1;
	}

	public void enter(String s, String value) {
		symbol.add(s);
		symbolValue.add(value);
	}

	public int getSize() {
		return symbol.size();
	}

	public String getSymbol(int i) {
		return symbol.get(i);
	}

	public String getSymbolValue(int i) {
		return symbolValue.get(i);
	}

	public int getRelAdd(int i) {
		return relAdd.get(i);
	}

	public int getCategory(int i) {
		return category.get(i);
	}

	/**
	 * 删除所有的局部变量
	 */
	public void localRemove() {
		for (int i = symbol.size(); i >= 0; i--) {
			if (getCategory(i) == LOCAL) {
				symbol.remove(i);
				symbolValue.remove(i);
				relAdd.remove(i);
				category.remove(i);
			}
		}
	}
}
