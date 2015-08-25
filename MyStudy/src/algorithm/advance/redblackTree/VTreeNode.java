package algorithm.advance.redblackTree;

import java.awt.Color;
import java.awt.Graphics;

public class VTreeNode {
	/**
	 * ×ø±ê
	 **/
	int x, y;

	// ¸ß¶È
	int height;

	/**
	 * °ë¾¶
	 */
	int r = 30;

	VTreeNode left;
	VTreeNode right;

	Object value;
	RedBlackTree.RedBlackNode<Integer> innernode;

	public VTreeNode(RedBlackTree.RedBlackNode<Integer> innernode) {
		this.innernode = innernode;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public VTreeNode getLeft() {
		return left;
	}

	public void setLeft(VTreeNode left) {
		this.left = left;
	}

	public VTreeNode getRight() {
		return right;
	}

	public void setRight(VTreeNode right) {
		this.right = right;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	void paint(Graphics g) {
		if (getInnernode().color == 0)
			g.setColor(Color.RED);
		else
			g.setColor(Color.BLACK);
		g.fillOval(x, y, r, r);
		g.setColor(Color.white);
		g.drawString(this.innernode.element.toString(), x + 5, y + 12);
	}

	public RedBlackTree.RedBlackNode<Integer> getInnernode() {
		return innernode;
	}

	public void setInnernode(RedBlackTree.RedBlackNode<Integer> innernode) {
		this.innernode = innernode;
	}
}