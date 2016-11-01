package openframework.rtext;

import java.awt.Toolkit;
import javax.swing.*;


/**
 * Standalone version of the demo.
 *
 * @author Robert Futrell
 * @version 1.0
 */
public class RSyntaxTextAreaDemoApp extends JFrame {


	public RSyntaxTextAreaDemoApp() {
		setRootPane(new DemoRootPane());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("RSyntaxTextArea Demo Application");
		pack();
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.
											getSystemLookAndFeelClassName());
//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
				} catch (Exception e) {
					e.printStackTrace(); // Never happens
				}
				Toolkit.getDefaultToolkit().setDynamicLayout(true);
				new RSyntaxTextAreaDemoApp().setVisible(true);
			}
		});
	}


}