package CodeDesigner.ui.sheet;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * ���Խӿ�. <br>Component of a PropertySheet, based on the
 * java.beans.PropertyDescriptor for easy wrapping of beans in PropertySheet.
 * 
 * @author �׾� 2004-5-6
 * @modifier leijun 2009-6 �������Ե���������
 */
public interface Property extends Serializable {

	public String getName();

	public String getDisplayName();

	public String getShortDescription();

	public Class getType();

	public Object getValue();

	public void setValue(Object value);

	public boolean isEditable();

	public String getCategory();

	public void readFromObject(Object object);

	public void writeToObject(Object object);

	public void addPropertyChangeListener(PropertyChangeListener listener);

	public void removePropertyChangeListener(PropertyChangeListener listener);

	//XXX:leijun+2009-6 �����Ե���������
	public Object getOwnerObject();
}
