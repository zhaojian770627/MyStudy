package CodeDesigner;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;

import CodeDesigner.ui.sheet.PropertySheetPanel;

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
		PropertySheetPanel pp = new PropertySheetPanel();
		content.add(pp, BorderLayout.CENTER);
		this.setContentPane(content);

	}
}
