package algorithm.priorityqueue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

/**
 * 二项优先队列测试程序
 * @author zhaojian
 *
 */
public class BinomialQueueTest {

	BinomialQueue<Integer> queue = null;
	BinomialQueue<Integer> queue1 = null;
	BinomialQueue<Integer> queue2 = null;

	JTree tree;
	DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Empty");
	JLabel l2;
	
	BinomialQueueTest() {
		queue1 = new BinomialQueue<Integer>();
		queue2 = new BinomialQueue<Integer>();
		queue = queue1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinomialQueueTest BinomialTree = new BinomialQueueTest();
		BinomialTree.Show();
	}

	private void Show() {
		JFrame f = new JFrame("BinomialQueue");
		f.setLayout(new BorderLayout());
		f.setSize(500, 500);

		JPanel optionPnl = new JPanel();
		ButtonGroup rg = new ButtonGroup();
		final JRadioButton r1 = new JRadioButton("Queue1");
		final JRadioButton r2 = new JRadioButton("Queue2");
		r1.setSelected(true);
		rg.add(r1);
		rg.add(r2);

		optionPnl.add(r1);
		optionPnl.add(r2);
		f.add(optionPnl, BorderLayout.NORTH);

		tree = new JTree(rootNode);
		f.add(new JScrollPane(tree), BorderLayout.CENTER);
		JPanel operPnl = new JPanel();
		f.add(operPnl, BorderLayout.SOUTH);
		final JTextField jtNum = new JTextField(20);
		JButton btnAdd = new JButton("Add");
		JButton btnDel = new JButton("DelMin");
		JButton btnMerge = new JButton("Merge");
		JButton btnClear = new JButton("Clear");
		JLabel l1=new JLabel("项目数");
		l2=new JLabel("0");
		operPnl.add(jtNum);
		operPnl.add(btnAdd);
		operPnl.add(btnDel);
		operPnl.add(btnMerge);
		operPnl.add(btnClear);
		operPnl.add(l1);
		operPnl.add(l2);

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String snum = jtNum.getText();
				if (snum.equals(""))
					return;
				int num = Integer.parseInt(snum);
				AddNum(num);
				jtNum.setText("");
				jtNum.requestFocus();
			}

		});

		btnDel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DelMin();
			}

		});
		
		btnMerge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				queue1.merge(queue2);
				queue = queue1;
				setQueue(1);
			}

		});


		btnClear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(
						"Empty");
				DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
				tree.setModel(treeModel);
				queue1.makeEmpty();
				queue2.makeEmpty();
			}
		});

		r1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r1.isSelected())
					setQueue(1);
			}
		});

		r2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r2.isSelected())
					setQueue(2);
			}
		});

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void AddNum(int num) {
		queue.insert(num);
		DefaultMutableTreeNode rootNode = queue.createTreeNode();
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		expandTree(tree, new TreePath(rootNode));
		l2.setText(" "+queue.getCurrentSize());
	}

	private void DelMin() {
		queue.deleteMin();
		DefaultMutableTreeNode rootNode = queue.createTreeNode();
		DefaultTreeModel treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		expandTree(tree, new TreePath(rootNode));
		l2.setText(" "+queue.getCurrentSize());
	}

	private void setQueue(int i) {
		if (i == 1)
			queue = queue1;
		else
			queue = queue2;

		DefaultMutableTreeNode rootNode = queue.createTreeNode();
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
}
