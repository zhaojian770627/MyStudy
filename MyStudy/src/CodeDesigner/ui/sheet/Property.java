package CodeDesigner.ui.sheet;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * 属性接口. <br>Component of a PropertySheet, based on the
 * java.beans.PropertyDescriptor for easy wrapping of beans in PropertySheet.
 * 
 * @author 雷军 2004-5-6
 * @modifier leijun 2009-6 增加属性的宿主对象
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

	//XXX:leijun+2009-6 该属性的宿主对象
	public Object getOwnerObject();
}
