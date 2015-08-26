package CodeDesigner.ui.sheet;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.ColorUIResource;
import javax.swing.text.StyleConstants.ColorConstants;

public class PropertySheetPanel extends JPanel implements PropertySheet {
	private PropertySheetTableModel model;
	private PropertySheetTable table;
	private JScrollPane tableScroll;
	private JToggleButton asCategoryButton;
	private ListSelectionListener selectionListener;
	private JPanel actionPanel;

	public PropertySheetPanel() {
		this(new PropertySheetTable());
	}

	public PropertySheetPanel(PropertySheetTable table) {
		model = new PropertySheetTableModel();
		buildUI();
		setTable(table);
		setBackGroundStyle();
	}

	@Override
	public void setProperties(Property[] properties) {
		model.setProperties(properties);
	}

	@Override
	public Property[] getProperties() {
		return model.getProperties();
	}

	public void setTable(PropertySheetTable table) {
		if (table == null) {
			throw new IllegalArgumentException("table must not be null");
		}
		// prepare the new table
		table.setModel(model);
		table.getSelectionModel().addListSelectionListener(selectionListener);
		tableScroll.getViewport().setView(table);
		tableScroll.getViewport().setBackground(table.getBackground());

		// remove the listener from the old table
		if (this.table != null) {
			this.table.getSelectionModel().removeListSelectionListener(
					selectionListener);
		}

		// use the new table as our table
		this.table = table;
	}

	private void buildUI() {
		LookAndFeelTweaks.setBorderLayout(this);
		LookAndFeelTweaks.setBorder(this);

		actionPanel = new JPanel(new PercentLayout(PercentLayout.HORIZONTAL, 4));
		actionPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 2, 0));
		add("North", actionPanel);

		// asCategoryButton = new JToggleButton("");
		// asCategoryButton.setAction(new ToggleModeAction());
		//
		// asCategoryButton.setUI(new BlueishButtonUI());
		// asCategoryButton.setText(null);
		// //actionPanel.add(asCategoryButton);//lj@
		// actionPanel.add(asCategoryButton, "26");

		tableScroll = new JScrollPane();
		add("Center", tableScroll);
	}

	public void setBackGroundStyle() {
		actionPanel.setBackground(backgroundColor);
		tableScroll.setBackground(backgroundColor);
		setBackground(backgroundColor);
	}

	/**
	 * 通用的背景色
	 * */
	public final static Color backgroundColor = new ColorUIResource(0xB0, 0xC9,
			0xE2);
}
