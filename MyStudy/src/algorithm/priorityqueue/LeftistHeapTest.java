package algorithm.priorityqueue;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class LeftistHeapTest {

	LeftistHeap<Integer> heap = null;
	LeftistHeap<Integer> heap1 = null;
	LeftistHeap<Integer> heap2 = null;

	public LeftistHeapTest() {
		heap1 = new LeftistHeap<Integer>();
		heap2 = new LeftistHeap<Integer>();
		heap = heap1;
	}

	VTreePanel vp = null;

	private void Show() {
		JFrame f = new JFrame("LeftistHeap");
		f.setLayout(new BorderLayout());
		f.setSize(500, 500);
		JPanel optionPnl = new JPanel();
		ButtonGroup rg = new ButtonGroup();
		final JRadioButton r1 = new JRadioButton("Heap1");
		final JRadioButton r2 = new JRadioButton("Heap2");
		r1.setSelected(true);
		rg.add(r1);
		rg.add(r2);

		optionPnl.add(r1);
		optionPnl.add(r2);
		f.add(optionPnl, BorderLayout.NORTH);
		vp = new VTreePanel();

		JScrollPane scrollPane = new JScrollPane(vp);
		f.add(scrollPane, BorderLayout.CENTER);
		JPanel operPnl = new JPanel();
		f.add(operPnl, BorderLayout.SOUTH);
		final JTextField jtNum = new JTextField(20);
		JButton btnAdd = new JButton("Add");
		JButton btnDelMin = new JButton("DelMin");
		JButton btnMerge = new JButton("Merge");

		operPnl.add(jtNum);
		operPnl.add(btnAdd);
		operPnl.add(btnDelMin);
		operPnl.add(btnMerge);

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

		btnDelMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DelMin();
				jtNum.setText("");
			}

		});

		btnMerge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				heap1.merge(heap2);
				heap = heap1;
				setHeap(1);
			}

		});

		r1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r1.isSelected())
					setHeap(1);
			}
		});

		r2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (r2.isSelected())
					setHeap(2);
			}
		});

		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setHeap(int i) {
		if (i == 1)
			heap = heap1;
		else
			heap = heap2;
		VTreeNode vn = heap.createVTreeNode();
		VTreeModel vm = new VTreeModel(vn);
		vp.setModel(vm);
	}

	private void AddNum(int num) {
		heap.insert(num);
		VTreeNode vn = heap.createVTreeNode();
		VTreeModel vm = new VTreeModel(vn);
		vp.setModel(vm);
	}

	private void DelMin() {
		heap.deleteMin();
		VTreeNode vn = heap.createVTreeNode();
		VTreeModel vm = new VTreeModel(vn);
		vp.setModel(vm);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LeftistHeapTest test = new LeftistHeapTest();
		test.Show();
	}
}