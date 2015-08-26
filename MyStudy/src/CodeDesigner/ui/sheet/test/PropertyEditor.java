package CodeDesigner.ui.sheet.test;

import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Hashtable;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class PropertyEditor extends JTable {
	PropertyEditorModel ptm = new PropertyEditorModel();
	DefaultCellEditor longEditor;
	DefaultTableCellRenderer longRenderer;

	public PropertyEditor(){
		createLongEditorRenderer();
		setModel(ptm);
	}
	public class PropertyEditorModel extends DefaultTableModel {
		public PropertyEditorModel() {
			super(0, 2); // 只有两个列
		}

		public String getColumnName(int col) {
			return " "; // 不需要列标题
		}

		public boolean isCellEditable(int row, int col) {
			if (col == 0)
				return false; // 第一列是属性名，不可编辑
			else

				// 属性值是否可编辑要看用户指定的情况

				return ((Boolean) propertyEditable.get(this.getValueAt(row, 0)))
						.booleanValue();

		}

	}

	/**
	 * 每一个属性项都对应一个单元编辑器，用Hashtable来保存这些编辑器
	 */
	protected Hashtable propertyEditors = new Hashtable(10);

	/**
	 * 每一个属性项都对应一个单元渲染器
	 */
	protected Hashtable propertyRenderers = new Hashtable(10);

	/**
	 * 属性是否可编辑
	 */
	protected Hashtable propertyEditable = new Hashtable(10);

	/**
	 * 获取指定单元格的编辑器
	 * 
	 * @param row
	 *            行
	 * @param col
	 *            列
	 */
	public TableCellEditor getCellEditor(int row, int col) {
		TableCellEditor editor = null;
		if (col == 1) { // 属性值列才需要编辑器。这个判断条件不要也可，效率会低一点。
			editor = (TableCellEditor) propertyEditors.get(this.getValueAt(row,
					0));
		}
		if (editor == null) { // 没找到编辑器，则用系统默认的。
			editor = super.getCellEditor(row, col);
		}
		return editor;
	}

	/**
	 * 获取指定单元格的渲染器
	 */

	public TableCellRenderer getCellRenderer(int row, int col) {
		TableCellRenderer renderer = null;
		if (col == 1) {
			renderer = (TableCellRenderer) propertyRenderers.get(this
					.getValueAt(row, 0));
		}

		if (renderer == null) {
			renderer = super.getCellRenderer(row, col);
		}

		// 给表格元素提供Hint提示
		if (renderer instanceof JComponent) {
			Object v = this.getModel().getValueAt(row, col);
			if (v == null) { // 属性值有可能为空，则取属性名；属性名必不为空。
				v = this.getModel().getValueAt(row, 0);
			}
			((JComponent) renderer).setToolTipText(v.toString());
		}
		return renderer;
	}

	/**
	 * 在属性表中增加整数属性,允许为空值，编辑器和渲染器为long型编辑器和渲染器。 当属性值为空值时，必须写成：
	 * addProperty("pName", (Long)null)
	 * 
	 * @param propertyName
	 *            属性名
	 * @param longNumObj
	 *            属性初始值
	 */
	public void addProperty(String propertyName, Long longNumObj) {
		if (propertyName == null)
			throw new RuntimeException(
					"Coding error : property name can NOT be null !");
		Object[] row = new Object[2];
		row[0] = propertyName;
		row[1] = longNumObj;
		ptm.addRow(row);// 往表格增加行
		//ptm.appendRow(row); // 往表格增加行
		propertyEditors.put(propertyName, longEditor); // 添加整型编辑器
		propertyRenderers.put(propertyName, longRenderer); // 添加整型绘制器
		propertyEditable.put(propertyName, new Boolean(true)); // 设置该属性允许编辑
	}

	/**
	 * 
	 * 
	 * 根据属性名得到属性值
	 * @param propertyName
	 *            属性名
	 */

	public Object getPropertyValue(String propertyName) {
		Object retValue = null;
		for (int i = 0; i < ptm.getRowCount(); i++) {
			if (ptm.getValueAt(i, 0).equals(propertyName)) {
				retValue = ptm.getValueAt(i, 1);
				break;
			}
		}
		return retValue;
	}

	/**
	 * 设置属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @param newValue
	 *            新的属性值
	 */
	public void setPropertyValue(String propertyName, Object newValue) {
		for (int i = 0; i < ptm.getRowCount(); i++) {
			if (ptm.getValueAt(i, 0).equals(propertyName)) {
				ptm.setValueAt(newValue, i, 1);
				break;
			}

		}

	}

	/**
	 * 创建并初始化long型数据的编辑器和渲染器
	 */
	private void createLongEditorRenderer() {
		final JTextField longTextField = new JTextField("0", 5);
		longTextField.setHorizontalAlignment(JTextField.LEFT);

		longEditor = new DefaultCellEditor(longTextField) {
			private Object previousValue = null;

			public Object getCellEditorValue() {
				if (longTextField.getText().equals(""))
					return (Long) null;
				else
					return new Long(longTextField.getText());
			}

			public Component getTableCellEditorComponent(JTable table,
					Object value, boolean isSelected, int row, int column) {
				Component c = super.getTableCellEditorComponent(table, value,
						isSelected, row, column);
				previousValue = value; // 开始编辑前记下初始值
				return c;
			}

			public boolean stopCellEditing() {
				Long lv = null;
				// 如果输入了，要判断是否是整数
				if (!longTextField.getText().equals("")) {
					try {
						lv = new Long(longTextField.getText());
					} catch (Exception e) {
						cancelCellEditing(); // 用户输入了非数字，则还原到未编辑状态
						return true;
					}
				}

				if ((previousValue == null) ? (previousValue == lv)
						: previousValue.equals(lv)) {
					cancelCellEditing(); // 修改前后的值相等则认为属性值未修改
					return true;
				}

				return super.stopCellEditing();

			}
		};

		longEditor.setClickCountToStart(2);
		// 控件失去焦点时，停止编辑，接受已输入的数据
		longTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				longEditor.stopCellEditing();
			}
		});
		// 渲染器用系统默认的JLabel，但字符靠左
		longRenderer = (DefaultTableCellRenderer) this
				.getDefaultRenderer(Long.class);
		longRenderer.setHorizontalAlignment(JLabel.LEFT);

	}

	protected EventListenerList propertyListeners = new EventListenerList();

	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 增加一个属性值变化监听器
	 */

	public void addPropertyUpdateListener(PropertyChangeListener l) {
		propertyListeners.add(PropertyChangeListener.class, l);
	}

	/**
	 * 表格数据变化，由表格模型触发
	 */

	public void tableChanged(TableModelEvent e) {
		super.tableChanged(e);
		// 如果是属性值的列发生数据变化，要通知属性值变化监听器
		if (e.getType() == TableModelEvent.UPDATE
				&& e.getFirstRow() == e.getLastRow() && e.getColumn() == 1) {
			String pName = (String) ptm.getValueAt(e.getFirstRow(), 0);
			Object nValue = ptm.getValueAt(e.getFirstRow(), 1);
			firePropertyUpdated(pName, nValue);
		}
	}

	/**
	 * 通知属性值变化监听器
	 * 
	 * @param propertyName
	 *            属性名
	 * @param newValue
	 *            属性变化后的新值
	 */
	protected void firePropertyUpdated(String propertyName, Object newValue) {
		Object[] listeners = propertyListeners.getListenerList();
		for (int i = listeners.length - 2; i >= 0; i -= 2) {
			if (listeners[i] == PropertyChangeListener.class) {
				PropertyChangeEvent event = new PropertyChangeEvent(this, propertyName, "a", newValue);
				((PropertyChangeListener) listeners[i + 1])
						.propertyChange(event);

			}

		}
	}
}
