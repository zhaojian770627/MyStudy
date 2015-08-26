package CodeDesigner;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import CodeDesigner.ui.MainDesigner;

public class TestDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestDialog() {
		initUI();
	}

	private void initUI() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		MainDesigner md = new MainDesigner();
		content.add(md, BorderLayout.CENTER);
		this.setContentPane(content);

	}
}
