package CodeDesigner.ui.sheet;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.Icon;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class PropertySheetTable extends JTable {
	private static final TableCellRenderer categoryRenderer = new CategoryRenderer();

	private static final TableCellRenderer propertyRenderer = new PropertyRenderer();
	private static final String BACKGROUND_COLOR_KEY = "Panel.background";
	private static final int HOTSPOT_SIZE = 18;
	
	private static class CategoryRenderer extends DefaultTableCellRenderer {

		private Color background;

		private Color foreground;

		public CategoryRenderer() {
			this(UIManager.getColor(BACKGROUND_COLOR_KEY), UIManager.getColor(BACKGROUND_COLOR_KEY).darker());
		}

		public CategoryRenderer(Color background, Color foreground) {
			setBorder(new CellBorder(UIManager.getColor(BACKGROUND_COLOR_KEY)));
			this.background = background;
			this.foreground = foreground;
			setFont(getFont().deriveFont(Font.BOLD));
		}

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

			PropertySheetTableModel.Category category = (PropertySheetTableModel.Category) value;

			setBackground(background);
			setForeground(foreground);

			if (column == 0) {
				((CellBorder) getBorder()).setToggle(category.isVisible());
				((CellBorder) getBorder()).setToggleVisible(true);
				setText(category.getName());
			} else {
				((CellBorder) getBorder()).setToggleVisible(false);
				setText("");
			}
			return this;
		}
	}
	
	private static class PropertyRenderer extends DefaultTableCellRenderer {

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			Property property = (Property) value;
			setText(property.getDisplayName());
			if (!isSelected) {
				setEnabled(property.isEditable());
			} else {
				setEnabled(true);
			}
			setBorder(new CellBorder(UIManager.getColor(BACKGROUND_COLOR_KEY)));
			return this;
		}
	}
	
	private static class CellBorder implements Border {

		private Color background;

		private boolean showToggle;

		private boolean toggleState;

		private Icon expandedIcon = (Icon) UIManager.get("Tree.expandedIcon");

		private Icon collapsedIcon = (Icon) UIManager.get("Tree.collapsedIcon");

		public CellBorder(Color background) {
			this.background = background;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(1, HOTSPOT_SIZE, 1, 1);
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			Color oldColor = g.getColor();
			g.setColor(background);
			g.fillRect(x, y, x + HOTSPOT_SIZE - 2, y + height);
			g.setColor(oldColor);

			if (showToggle) {
				if (toggleState) {
					expandedIcon.paintIcon(c, g, x + (HOTSPOT_SIZE - 2 - expandedIcon.getIconWidth()) / 2, y + (height - expandedIcon.getIconHeight()) / 2);
				} else {
					collapsedIcon.paintIcon(c, g, x + (HOTSPOT_SIZE - 2 - collapsedIcon.getIconWidth()) / 2, y + (height - collapsedIcon.getIconHeight()) / 2);
				}
			}
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void setToggle(boolean state) {
			toggleState = state;
		}

		public void setToggleVisible(boolean visible) {
			showToggle = visible;
		}
	}
}
