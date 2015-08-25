package LRGenerate;

import java.util.ArrayList;
import java.util.List;

public class Production {
	/*
	 * 产生式的左边
	 */
	Symbol left;
	/*
	 * 产生式的右边
	 */
	List<Symbol> right = new ArrayList<Symbol>();

	int key;

	public Production(Symbol z, Symbol[] symbols, int key) {
		this.left = z;
		for (Symbol s : symbols) {
			right.add(s);
		}
		this.key = key;
	}

	public Symbol getLeft() {
		return left;
	}

	public void setLeft(Symbol left) {
		this.left = left;
	}

	public List<Symbol> getRight() {
		return right;
	}

	public void setRight(List<Symbol> right) {
		this.right = right;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}
}
