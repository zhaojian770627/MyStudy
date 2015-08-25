package algorithm.priorityqueue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BinaryHeapTest {

	BinaryHeap<Integer> heap = null;

	public BinaryHeapTest() {
		heap = new BinaryHeap<Integer>();
	}

	JTextArea textArea = null;
	VTreePanel vp = null;

	private void Show() {
		JFrame f = new JFrame("BinaryHeap");
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
				// showHeap();
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
		heap.insert(num);

		VTreeNode vn = createVTreeNode(heap, 1);
		VTreeModel vm = new VTreeModel(vn);
		showHeap();
		vp.setModel(vm);
	}

	private void DelMin() {
		heap.deleteMin();
		VTreeNode vn = createVTreeNode(heap, 1);
		VTreeModel vm = new VTreeModel(vn);
		showHeap();
		vp.setModel(vm);
	}

	private VTreeNode createVTreeNode(BinaryHeap<Integer> heap, int i) {
		if (i > heap.getCurrentSize())
			return null;
		VTreeNode node = new VTreeNode(heap.get(i));
		VTreeNode leftChild = createVTreeNode(heap, 2 * i);
		if (leftChild != null)
			node.setLeft(leftChild);
		VTreeNode rightChild = createVTreeNode(heap, 2 * i + 1);
		if (rightChild != null)
			node.setRight(rightChild);
		return node;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BinaryHeapTest test = new BinaryHeapTest();
		test.Show();
	}

	private void showHeap() {
		String s = "";
		for (int i = 1; i <= heap.getCurrentSize(); i++) {
			s = s + " " + heap.get(i);
		}
		textArea.setText(s);
	}

	private void showVTreeInfo(VTreeModel vm) {
		String s = "Ê÷¸ß:" + vm.getTreeHight() + " ×î×ó:" + vm.getMaxLeft() + " ×îÓÒ"
				+ vm.getMaxRight();
		textArea.setText(s);
	}
}