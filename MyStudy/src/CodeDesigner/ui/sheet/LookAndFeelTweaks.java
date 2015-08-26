package CodeDesigner.ui.sheet;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.text.JTextComponent;
import javax.swing.text.View;
import javax.swing.text.html.HTMLDocument;

/**
 * LookAndFeelTweaks. <br>
 * 
 */
public class LookAndFeelTweaks {

	public final static Border PANEL_BORDER = BorderFactory.createEmptyBorder(
			3, 3, 3, 3);

	public final static Border WINDOW_BORDER = BorderFactory.createEmptyBorder(
			4, 10, 10, 10);

	public final static Border EMPTY_BORDER = BorderFactory.createEmptyBorder();

	public static void tweak() {
		Object listFont = UIManager.get("List.font");
		UIManager.put("Table.font", listFont);
		UIManager.put("ToolTip.font", listFont);
		UIManager.put("TextField.font", listFont);
		UIManager.put("FormattedTextField.font", listFont);
		UIManager.put("Viewport.background", "Table.background");
	}

	public static PercentLayout createVerticalPercentLayout() {
		return new PercentLayout(PercentLayout.VERTICAL, 8);
	}

	public static PercentLayout createHorizontalPercentLayout() {
		return new PercentLayout(PercentLayout.HORIZONTAL, 8);
	}

	public static ButtonAreaLayout createButtonAreaLayout() {
		return new ButtonAreaLayout(6);
	}

	public static BorderLayout createBorderLayout() {
		return new BorderLayout(8, 8);
	}

	public static void setBorder(JComponent component) {
		if (component instanceof JPanel) {
			component.setBorder(PANEL_BORDER);
		}
	}

	public static void setBorderLayout(Container container) {
		container.setLayout(new BorderLayout(3, 3));
	}

	public static void makeBold(JComponent component) {
		component.setFont(component.getFont().deriveFont(Font.BOLD));
	}

	public static void makeMultilineLabel(JTextComponent area) {
		area.setFont(UIManager.getFont("Label.font"));
		area.setEditable(false);
		area.setOpaque(false);
		if (area instanceof JTextArea) {
			((JTextArea) area).setWrapStyleWord(true);
			((JTextArea) area).setLineWrap(true);
		}
	}

	public static void htmlize(JComponent component) {
		Font defaultFont = UIManager.getFont("Button.font");

		String stylesheet = "body { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0; font-family: "
				+ defaultFont.getName()
				+ "; font-size: "
				+ defaultFont.getSize()
				+ "pt;	}"
				+ "a, p, li { margin-top: 0; margin-bottom: 0; margin-left: 0; margin-right: 0; font-family: "
				+ defaultFont.getName()
				+ "; font-size: "
				+ defaultFont.getSize() + "pt;	}";

		try {
			HTMLDocument doc = null;
			if (component instanceof JEditorPane) {
				doc = (HTMLDocument) ((JEditorPane) component).getDocument();
			} else {
				View v = (View) component
						.getClientProperty(javax.swing.plaf.basic.BasicHTML.propertyKey);
				if (v != null) {
					doc = (HTMLDocument) v.getDocument();
				}
			}
			if (doc != null) {
				doc.getStyleSheet().loadRules(
						new java.io.StringReader(stylesheet), null);
			} // end of if (doc != null)
		} catch (Exception e) {
			//��ʱ����
			//Logger.error(e.getMessage(), e);
		}
	}

	public static Border addMargin(Border border) {
		return new CompoundBorder(border, PANEL_BORDER);
	}

}
