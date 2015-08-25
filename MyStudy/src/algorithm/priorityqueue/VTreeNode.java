package algorithm.priorityqueue;

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

	public VTreeNode(Object value) {
		this.value = value;
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
		g.setColor(Color.blue);
		g.fillOval(x, y, r, r);
		g.setColor(Color.white);
		g.drawString(value.toString(), x + 5, y + 12);
	}
}