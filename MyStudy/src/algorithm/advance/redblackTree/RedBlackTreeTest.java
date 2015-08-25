package algorithm.advance.redblackTree;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ºìºÚÊ÷ÑÝÊ¾³ÌÐò
 * @author zhaojian
 *
 */
public class RedBlackTreeTest {

	RedBlackTree<Integer> tree = null;

	public RedBlackTreeTest() {
		tree = new RedBlackTree<Integer>();
	}

	JTextArea textArea = null;
	VTreePanel vp = null;
	VTreeModel vm;

	private void Show() {
		JFrame f = new JFrame("RedBlackTree");
		f.setLayout(new BorderLayout());
		f.setSize(500, 500);
		textArea = new JTextArea();
		f.add(textArea, BorderLayout.NORTH);
		vp = new VTreePanel();

		JScrollPane scrollPane = new JScrollPane(vp);
		f.add(scrollPane, BorderLayout.CENTER);
		JPanel operPnl = new JPanel();
		f.add(operPnl, BorderLayout.SOUTH);
		final JTextField jtNum = new JTextField(20);
		JButton btnAdd = new JButton("Add");
		JButton btnDelMin = new JButton("DelMin");

		operPnl.add(jtNum);
		operPnl.add(btnAdd);
		operPnl.add(btnDelMin);

		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String snum = jtNum.getText();
				if (snum.equals(""))
					return;
				int num = Integer.parseInt(snum);
				AddNum(num);
				jtNum.setText("");
				showVTreeInfo(vm);
			}

		});

		btnDelMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DelMin();
				jtNum.setText("");
			}

		});

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void AddNum(int num) {
		tree.insert(num);
		vm = new VTreeModel(tree);
		vp.setModel(vm);
	}

	private void DelMin() {

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RedBlackTreeTest test = new RedBlackTreeTest();
		test.Show();
	}

	private void showVTreeInfo(VTreeModel vm) {
		String s = "Ê÷¸ß:" + vm.getTreeHight();
		textArea.setText(s);
	}
}