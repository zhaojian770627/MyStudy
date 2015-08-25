package ai.Eight;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DrawDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DrawArea drawArea;
	JPanel operpnl = new JPanel();
	int i = 0;
	Data gloal = new Data();
	Data confusion = new Data();
	EightResolve resolve = new EightResolve();
	List<Data> lstResult = null;

	public DrawDialog() {
		setTitle("八数码问题");
		int[][] gary = new int[][] { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
		gloal = new Data();
		gloal.setValue(gary);
		drawArea = new DrawArea(gary);
		initUI();
	}

	private void initUI() {
		JButton btnConfusion = new JButton("Confusion");
		JButton btnResovle = new JButton("Resovle");
		final JButton btnPath = new JButton("Path");
		JButton btnAuto = new JButton("Auto");

		operpnl.add(btnConfusion);
		operpnl.add(btnResovle);
		operpnl.add(btnPath);
		operpnl.add(btnAuto);

		JPanel mainPnl = new JPanel();
		mainPnl.setLayout(new BorderLayout());
		mainPnl.add(operpnl, BorderLayout.NORTH);
		mainPnl.add(drawArea, BorderLayout.CENTER);
		drawArea.setBackground(Color.WHITE);
		this.setContentPane(mainPnl);
		drawArea.setModel(gloal);

		btnConfusion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				confusion = new Data();
				// int[][] iary = new int[][] { { 1, 2, 0 }, { 8, 4, 3 },
				// { 7, 6, 5 } };
				// confusion.setValue(iary);
				confusion.random();
				drawArea.setModel(confusion);
				btnPath.setEnabled(false);
			}
		});

		btnResovle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setTitle("八数码问题");
				// 深度搜索算法
				// if (resolve.deepResolve(confusion, gloal)) {
				// 宽度
				// if (resolve.widthResolve(confusion, gloal)) {

				// A
				if (resolve.AResolve(confusion, gloal)) {
					lstResult = resolve.getPath();
					i = lstResult.size() - 1;
					setTitle("节点总数:" + resolve.G.size() + " 深度:" + i);
					btnPath.setEnabled(true);
				} else
					btnPath.setEnabled(false);
			}
		});

		btnAuto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				do {
					confusion = new Data();
					confusion.random();
					drawArea.setModel(confusion);
					if (resolve.widthResolve(confusion, gloal)) {
						lstResult = resolve.getPath();
						i = lstResult.size() - 1;
						setTitle("节点总数:" + resolve.G.size() + " 深度:" + i);
						btnPath.setEnabled(true);
						break;
					} else
						btnPath.setEnabled(false);

				} while (true);
			}
		});

		btnPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				i--;
				if (i >= 0) {
					Data d = lstResult.get(i);
					drawArea.setModel(d);
				}
			}
		});
	}
}
