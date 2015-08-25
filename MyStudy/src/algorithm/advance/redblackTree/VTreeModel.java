package algorithm.advance.redblackTree;

import java.util.HashMap;
import java.util.Map;

public class VTreeModel {
	/**
	 * 节点半径
	 */
	int r;

	RedBlackTree.RedBlackNode<Integer> root;
	RedBlackTree.RedBlackNode<Integer> nullNode;
	RedBlackTree<Integer> tree;

	Map<Integer, Integer> levelDistance = new HashMap<Integer, Integer>();

	/**
	 * 树高
	 */
	int treeHight;

	/**
	 * 节点之间水平间距
	 */
	int hDistance = 10;

	int vDistance = 50;

	/**
	 * 节点之间水平间距
	 */
	public int gethDistance() {
		return hDistance;
	}

	public void sethDistance(int hDistance) {
		this.hDistance = hDistance;
	}

	/**
	 * 节点之间垂直间距
	 */
	public int getvDistance() {
		return vDistance;
	}

	public void setvDistance(int vDistance) {
		this.vDistance = vDistance;
	}

	public RedBlackTree.RedBlackNode<Integer> getRoot() {
		return root;
	}

	public void setRoot(RedBlackTree.RedBlackNode<Integer> root) {
		this.root = root;
	}

	int maxLeft;
	int maxRight;

	public VTreeModel(RedBlackTree<Integer> tree) {
		this.tree = tree;
		this.root = tree.getHeader().right;
		maxLeft = 0;
		maxRight = 0;
		// 求树高及最左、最右节点
		treeHight = High(tree.getHeader().right);
	}

	public int getLevelDistance(int level) {
		return levelDistance.get(level);
	}

	public int getTreeHight() {
		return treeHight;
	}

	public void setTreeHight(int treeHight) {
		this.treeHight = treeHight;
	}

	public int getMaxLeft() {
		return maxLeft;
	}

	public void setMaxLeft(int maxLeft) {
		this.maxLeft = maxLeft;
	}

	public int getMaxRight() {
		return maxRight;
	}

	public void setMaxRight(int maxRight) {
		this.maxRight = maxRight;
	}

	int High(RedBlackTree.RedBlackNode<Integer> node) {
		if (node == tree.getNullNode())
			return 0;
		else {
			int left_h = High(node.left);
			int right_h = High(node.right);
			int h = 1 + Math.max(left_h, right_h);
			return h;
		}
	}
}