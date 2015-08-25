package algorithm.priorityqueue;

import java.util.HashMap;
import java.util.Map;

public class VTreeModel {
	/**
	 * 节点半径
	 */
	int r;

	VTreeNode root;

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

	public VTreeNode getRoot() {
		return root;
	}

	public void setRoot(VTreeNode root) {
		this.root = root;
	}

	int maxLeft;
	int maxRight;

	public VTreeModel(VTreeNode vn) {
		if (vn == null)
			return;
		this.root = vn;
		maxLeft = 0;
		maxRight = 0;
		// 求树高及最左、最右节点
		treeHight = High(root);
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

	int High(VTreeNode node) {
		if (node == null)
			return 0;
		else {
			int left_h = High(node.getLeft());
			int right_h = High(node.getRight());
			int h = 1 + Math.max(left_h, right_h);
			node.setHeight(h);
			return h;
		}
	}
}