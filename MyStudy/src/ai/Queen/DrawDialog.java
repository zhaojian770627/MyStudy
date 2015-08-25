package ai.Queen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

public class DrawDialog extends JDialog {
	DrawArea drawArea = new DrawArea();
	JPanel operpnl = new JPanel();
	Map<String, Color> cm = new HashMap<String, Color>();
	QeenResolve r = new QeenResolve();
	int i = 0;

	public DrawDialog() {
		initUI();
	}

	private void initUI() {
		JButton btnLine = new JButton("Resolve");
		JButton btnCircle = new JButton("Next");

		operpnl.add(btnLine);
		operpnl.add(btnCircle);

		JPanel mainPnl = new JPanel();
		mainPnl.setLayout(new BorderLayout());
		mainPnl.add(operpnl, BorderLayout.NORTH);
		mainPnl.add(drawArea, BorderLayout.CENTER);
		drawArea.setBackground(Color.WHITE);
		this.setContentPane(mainPnl);

		btnLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				r.resolve();
				setTitle("回溯次数:" + r.getBackCount() + " 解："
						+ r.getResultCount());
				showNext();
			}
		});

		btnCircle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showNext();
			}
		});
	}

	void showNext() {
		if (i >= r.getResult().size())
			i = 0;
		drawArea.setModel(r.getResult().get(i++));
	}
}
