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
			super(0, 2); // ֻ��������
		}

		public String getColumnName(int col) {
			return " "; // ����Ҫ�б���
		}

		public boolean isCellEditable(int row, int col) {
			if (col == 0)
				return false; // ��һ���������������ɱ༭
			else

				// ����ֵ�Ƿ�ɱ༭Ҫ���û�ָ�������

				return ((Boolean) propertyEditable.get(this.getValueAt(row, 0)))
						.booleanValue();

		}

	}

	/**
	 * ÿһ���������Ӧһ����Ԫ�༭������Hashtable��������Щ�༭��
	 */
	protected Hashtable propertyEditors = new Hashtable(10);

	/**
	 * ÿһ���������Ӧһ����Ԫ��Ⱦ��
	 */
	protected Hashtable propertyRenderers = new Hashtable(10);

	/**
	 * �����Ƿ�ɱ༭
	 */
	protected Hashtable propertyEditable = new Hashtable(10);

	/**
	 * ��ȡָ����Ԫ��ı༭��
	 * 
	 * @param row
	 *            ��
	 * @param col
	 *            ��
	 */
	public TableCellEditor getCellEditor(int row, int col) {
		TableCellEditor editor = null;
		if (col == 1) { // ����ֵ�в���Ҫ�༭��������ж�������ҪҲ�ɣ�Ч�ʻ��һ�㡣
			editor = (TableCellEditor) propertyEditors.get(this.getValueAt(row,
					0));
		}
		if (editor == null) { // û�ҵ��༭��������ϵͳĬ�ϵġ�
			editor = super.getCellEditor(row, col);
		}
		return editor;
	}

	/**
	 * ��ȡָ����Ԫ�����Ⱦ��
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

		// �����Ԫ���ṩHint��ʾ
		if (renderer instanceof JComponent) {
			Object v = this.getModel().getValueAt(row, col);
			if (v == null) { // ����ֵ�п���Ϊ�գ���ȡ���������������ز�Ϊ�ա�
				v = this.getModel().getValueAt(row, 0);
			}
			((JComponent) renderer).setToolTipText(v.toString());
		}
		return renderer;
	}

	/**
	 * �����Ա���������������,����Ϊ��ֵ���༭������Ⱦ��Ϊlong�ͱ༭������Ⱦ���� ������ֵΪ��ֵʱ������д�ɣ�
	 * addProperty("pName", (Long)null)
	 * 
	 * @param propertyName
	 *            ������
	 * @param longNumObj
	 *            ���Գ�ʼֵ
	 */
	public void addProperty(String propertyName, Long longNumObj) {
		if (propertyName == null)
			throw new RuntimeException(
					"Coding error : property name can NOT be null !");
		Object[] row = new Object[2];
		row[0] = propertyName;
		row[1] = longNumObj;
		ptm.addRow(row);// �����������
		//ptm.appendRow(row); // �����������
		propertyEditors.put(propertyName, longEditor); // ������ͱ༭��
		propertyRenderers.put(propertyName, longRenderer); // ������ͻ�����
		propertyEditable.put(propertyName, new Boolean(true)); // ���ø���������༭
	}

	/**
	 * 
	 * 
	 * �����������õ�����ֵ
	 * @param propertyName
	 *            ������
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
	 * ��������ֵ
	 * 
	 * @param propertyName
	 *            ������
	 * @param newValue
	 *            �µ�����ֵ
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
	 * ��������ʼ��long�����ݵı༭������Ⱦ��
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
				previousValue = value; // ��ʼ�༭ǰ���³�ʼֵ
				return c;
			}

			public boolean stopCellEditing() {
				Long lv = null;
				// ��������ˣ�Ҫ�ж��Ƿ�������
				if (!longTextField.getText().equals("")) {
					try {
						lv = new Long(longTextField.getText());
					} catch (Exception e) {
						cancelCellEditing(); // �û������˷����֣���ԭ��δ�༭״̬
						return true;
					}
				}

				if ((previousValue == null) ? (previousValue == lv)
						: previousValue.equals(lv)) {
					cancelCellEditing(); // �޸�ǰ���ֵ�������Ϊ����ֵδ�޸�
					return true;
				}

				return super.stopCellEditing();

			}
		};

		longEditor.setClickCountToStart(2);
		// �ؼ�ʧȥ����ʱ��ֹͣ�༭�����������������
		longTextField.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				longEditor.stopCellEditing();
			}
		});
		// ��Ⱦ����ϵͳĬ�ϵ�JLabel�����ַ�����
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
	 * ����һ������ֵ�仯������
	 */

	public void addPropertyUpdateListener(PropertyChangeListener l) {
		propertyListeners.add(PropertyChangeListener.class, l);
	}

	/**
	 * ������ݱ仯���ɱ��ģ�ʹ���
	 */

	public void tableChanged(TableModelEvent e) {
		super.tableChanged(e);
		// ���������ֵ���з������ݱ仯��Ҫ֪ͨ����ֵ�仯������
		if (e.getType() == TableModelEvent.UPDATE
				&& e.getFirstRow() == e.getLastRow() && e.getColumn() == 1) {
			String pName = (String) ptm.getValueAt(e.getFirstRow(), 0);
			Object nValue = ptm.getValueAt(e.getFirstRow(), 1);
			firePropertyUpdated(pName, nValue);
		}
	}

	/**
	 * ֪ͨ����ֵ�仯������
	 * 
	 * @param propertyName
	 *            ������
	 * @param newValue
	 *            ���Ա仯�����ֵ
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
