package algorithm.tree;

/**
 * ≤È’“ ˜
 * @author zhaojian
 *
 */
public class SearchTree {
	class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int s) {
			value = s;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}
	}

	TreeNode insert(int x, TreeNode tree) {
		if (tree == null) {
			tree = new TreeNode(x);
			tree.setLeft(null);
			tree.setRight(null);
		} else if (x < tree.getValue())
			tree.setLeft(insert(x, tree.getLeft()));
		else if (x > tree.getValue())
			tree.setRight(insert(x, tree.getRight()));
		return tree;
	}

	TreeNode find(int x, TreeNode tree) {
		if (tree == null)
			return null;
		else if (x < tree.getValue())
			return find(x, tree.getLeft());
		else if (x > tree.getValue())
			return find(x, tree.getRight());
		else
			return tree;
	}

	TreeNode findMin(TreeNode tree) {
		if (tree != null)
			while (tree.getLeft() != null)
				tree = tree.getLeft();
		return tree;
	}

	TreeNode findMax(TreeNode tree) {
		if (tree != null)
			while (tree.getRight() != null)
				tree = tree.getRight();
		return tree;
	}

	TreeNode delete(int x, TreeNode tree) {
		if (tree == null)
			return null;
		else if (x < tree.getValue())
			tree.setLeft(delete(x, tree.getLeft()));
		else if (x > tree.getValue())
			tree.setRight(delete(x, tree.getRight()));
		else if (tree.getLeft() != null && tree.getRight() != null) {
			TreeNode t = findMin(tree.getRight());
			tree.setValue(t.getValue());
			tree.setRight(delete(tree.getValue(), tree.getRight()));
		} else {
			if (tree.getLeft() == null)
				tree = tree.getRight();
			else if (tree.getRight() == null)
				tree = tree.getLeft();
		}
		return tree;
	}

	void execute() {
		TreeNode root = insert(10, null);
		insert(11, root);
		insert(2, root);
		insert(22, root);

		TreeNode ft = find(5, root);
		TreeNode min = findMin(root);
		TreeNode max = findMax(root);
		TreeNode t = delete(10, root);
		System.out.println("OK");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SearchTree st = new SearchTree();
		st.execute();
		System.exit(0);
	}

}
