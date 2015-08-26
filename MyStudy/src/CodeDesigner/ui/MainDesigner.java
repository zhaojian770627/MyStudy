package CodeDesigner.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import CodeDesigner.ui.compont.HBar;
import CodeDesigner.ui.compont.VBar;


public class MainDesigner extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MainDesigner() {
		setLayout(new BorderLayout());
		HBar hb = new HBar();
		VBar vb = new VBar();
		DesignSpace ds = new DesignSpace();
		this.add(hb, BorderLayout.NORTH);
		this.add(vb, BorderLayout.WEST);
		this.add(ds, BorderLayout.CENTER);
	}
}
