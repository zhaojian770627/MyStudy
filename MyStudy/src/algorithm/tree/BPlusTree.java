package algorithm.tree;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class BPlusTree implements IBPlusTree {
	/** 根节点 */
	protected BPlusTreeNode root;

	/** 阶数，M值 */
	protected int order;

	/** 叶子节点的链表头 */
	protected BPlusTreeNode head;

	public BPlusTreeNode getHead() {
		return head;
	}

	public void setHead(BPlusTreeNode head) {
		this.head = head;
	}

	public BPlusTreeNode getRoot() {
		return root;
	}

	public void setRoot(BPlusTreeNode root) {
		this.root = root;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Override
	public Object get(Comparable key) {
		return root.get(key);
	}

	@Override
	public void remove(Comparable key) {
		root.remove(key, this);

	}

	@Override
	public void insertOrUpdate(Comparable key, Object obj) {
		root.insertOrUpdate(key, obj, this);

	}

	public BPlusTree(int order) {
		if (order < 3) {
			System.out.print("order must be greater than 2");
			System.exit(0);
		}
		this.order = order;
		root = new BPlusTreeNode(true, true);
		head = root;
	}

	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Empty");
	JTree tree;

	private void Show() {
		JFrame f = new JFrame("BPlusTree");
		f.setLayout(new BorderLayout());
		f.setSize(500, 500);

		tree = new JTree(rootNode);
		JScrollPane scrollPane = new JScrollPane(tree);
		f.add(scrollPane, BorderLayout.CENTER);
		JPanel operPnl = new JPanel();
		f.add(operPnl, BorderLayout.SOUTH);
		final JTextField jtNum = new JTextField(20);
		JButton btnAdd = new JButton("Add");
		JButton btnDel = new JButton("Del");
		JButton btnGet = new JButton("Get");
		JButton btnClear = new JButton("Clear");

		operPnl.add(jtNum);
		operPnl.add(btnAdd);
		operPnl.add(btnDel);
		operPnl.add(btnGet);
		operPnl.add(btnClear);

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String snum = jtNum.getText();
				if (snum.equals(""))
					return;
				int num = Integer.parseInt(snum);
				try {
					AddNum(num);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				jtNum.setText("");
			}

		});

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String snum = jtNum.getText();
				if (snum.equals(""))
					return;
				int num = Integer.parseInt(snum);
				try {
					DelNum(num);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				jtNum.setText("");
			}

		});

		btnGet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String snum = jtNum.getText();
				if (snum.equals(""))
					return;
				int num = Integer.parseInt(snum);
				Object o = get(num);
				if (o != null)
					JOptionPane.showMessageDialog(null, o.toString());
				jtNum.setText("");
			}
		});

		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				root = new BPlusTreeNode(true, true);
				head = root;
				DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
						"Empty");
				DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
				tree.setModel(treeModel);
			}
		});
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	class UITreeNode {
		BPlusTreeNode node;
		String title;

		public UITreeNode(String title) {
			this.title = title;
		}

		public UITreeNode(BPlusTreeNode node, String title) {
			this.node = node;
			this.title = title;
		}

		@Override
		public String toString() {
			if (node != null)
				return title + node.toString();
			else
				return title;
		}
	}

	protected void AddNum(int num) throws Exception {
		insertOrUpdate(num, num);

		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				new UITreeNode(root, "Root "));
		addChildTree(rootNode, root);
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		expandTree(tree, new TreePath(rootNode));
	}

	private void DelNum(int num) throws Exception {
		remove(num);
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
				new UITreeNode(root, "Root "));
		addChildTree(rootNode, root);
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		expandTree(tree, new TreePath(rootNode));
	}

	private void expandTree(JTree tree, TreePath parent) {
		TreeNode node = (TreeNode) parent.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (Enumeration<?> e = node.children(); e.hasMoreElements();) {
				TreeNode n = (TreeNode) e.nextElement();
				TreePath path = parent.pathByAddingChild(n);
				expandTree(tree, path);
			}
		}
		tree.expandPath(parent);
	}

	private void addChildTree(DefaultMutableTreeNode rootNode,
			BPlusTreeNode bPlusNode) throws Exception {
		if (bPlusNode == null)
			return;
		int keys = 0;
		if (bPlusNode.getKeyEntries() != null)
			keys = bPlusNode.getKeyEntries().size();
		int childs = 0;
		if (bPlusNode.getChildren() != null)
			childs = bPlusNode.getChildren().size();

		int i = 0;
		while (i < keys) {
			if (i < keys) {
				Object key = bPlusNode.getKeyEntries().get(i);
				DefaultMutableTreeNode keyNode = new DefaultMutableTreeNode(
						new UITreeNode("键:" + key.toString()));
				rootNode.add(keyNode);
			}

			if (i < childs) {
				BPlusTreeNode child = bPlusNode.getChildren().get(i);
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
						new UITreeNode(child, "子节点:"));
				addChildTree(childNode, child);
				rootNode.add(childNode);
			}
			i++;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BPlusTree bPlusTree = new BPlusTree(6);
		bPlusTree.Show();
	}
}