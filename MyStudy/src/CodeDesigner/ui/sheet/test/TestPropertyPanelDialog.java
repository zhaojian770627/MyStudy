package CodeDesigner.ui.sheet.test;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class TestPropertyPanelDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestPropertyPanelDialog() {
		initUI();
	}

	private void initUI() {
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		PropertyEditor pp = new PropertyEditor();
		pp.addProperty("abcd", (long) 1);
		content.add(pp, BorderLayout.CENTER);
		this.setContentPane(content);

	}
}
