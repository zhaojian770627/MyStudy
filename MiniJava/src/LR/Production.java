package LR;

import java.util.ArrayList;
import java.util.List;

public class Production {
	/*
	 * ����ʽ�����
	 */
	Symbol left;
	/*
	 * ����ʽ���ұ�
	 */
	List<Symbol> right = new ArrayList<Symbol>();

	public Production(Symbol z, Symbol[] symbols) {
		this.left = z;
		for (Symbol s : symbols) {
			right.add(s);
		}
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

	
}
