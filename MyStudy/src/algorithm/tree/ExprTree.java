package algorithm.tree;

import java.util.Stack;

/**
 * 
 * @author zhaojian
 *
 */
public class ExprTree {
	class TreeNode {
		String value;
		TreeNode left;
		TreeNode right;

		public TreeNode(String s) {
			value = s;
		}

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
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


	void execute() {
		Stack<TreeNode> stk = new Stack<TreeNode>();
		String inputStr = "156*+23*4+9+3*+";
		String[] input = inputStr.split("");
		for (String s : input) {
			if (s.equals(""))
				continue;

			char c = s.charAt(0);
			if (Character.isDigit(c)) {
				TreeNode t = new TreeNode(s);
				stk.push(t);
			} else {
				TreeNode p = new TreeNode(s);
				TreeNode c1 = stk.pop();
				TreeNode c2 = stk.pop();
				p.setLeft(c2);
				p.setRight(c1);
				stk.push(p);
			}
		}
		TreeNode root = stk.pop();
		System.out.println("OK");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ExprTree et = new ExprTree();
		et.execute();
	}

}
