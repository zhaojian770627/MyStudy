package CodeDesigner.ui.sheet;

import java.awt.Image;
import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;

/**
 * Bean的描述信息
 * 
 * <p>
 * 修改人：雷军 2004-5-8 修改为使用统一字符串资源管理器
 * <p>
 * 修改人：雷军 2005-3-3 增加方法removeProperty
 */
public class BaseBeanInfo extends SimpleBeanInfo {

	private Class type;

	private BeanDescriptor beanDescriptor;

	private List properties = new ArrayList(0);

	public BaseBeanInfo(Class type) {
		this.type = type;
	}

	public final Class getType() {
		return type;
	}

	/**
	 * Get the bean descriptor.
	 * 
	 * @return Bean descriptor.
	 */
	public BeanDescriptor getBeanDescriptor() {
		if (beanDescriptor == null) {
			beanDescriptor = new DefaultBeanDescriptor(this);
		}
		return beanDescriptor;
	}

	public PropertyDescriptor[] getPropertyDescriptors() {
		return (PropertyDescriptor[]) properties
				.toArray(new PropertyDescriptor[0]);
	}

	public int getPropertyDescriptorCount() {
		return properties.size();
	}

	public PropertyDescriptor getPropertyDescriptor(int index) {
		return (PropertyDescriptor) properties.get(index);
	}

	protected PropertyDescriptor addPropertyDescriptor(
			PropertyDescriptor property) {
		properties.add(property);
		return property;
	}

	/**
	 * 删除某个属性描述器
	 * 
	 * @param propertyName
	 * @return 被删除的
	 */
	public PropertyDescriptor removeProperty(String propertyName) {
		PropertyDescriptor removed = null;
		for (Iterator iter = properties.iterator(); iter.hasNext();) {
			removed = (PropertyDescriptor) iter.next();
			if (propertyName.equals(removed.getName()))
				break;
		}
		properties.remove(removed);
		return removed;
	}

	/**
	 * @param propertyName
	 * @return
	 */
	public ExtendedPropertyDescriptor addProperty(String propertyName) {
		ExtendedPropertyDescriptor descriptor;
		try {
			if (propertyName == null || propertyName.trim().length() == 0) {
				throw new IntrospectionException("bad property name");
			}

			Method readMethod = BeanUtilities.getReadMethod(getType(),
					propertyName);
			Method writeMethod = null;
			if (readMethod == null) {
				throw new IntrospectionException("No getter for property "
						+ propertyName + " in class " + getType().getName());
			}
			writeMethod = BeanUtilities.getWriteMethod(getType(), propertyName,
					readMethod.getReturnType());
			descriptor = new ExtendedPropertyDescriptor(propertyName,
					readMethod, writeMethod);

			try {
				// 雷军 修改为使用统一资源管理器 2004-10-08
				// descriptor.setDisplayName(ResManager.getLanguageDependentString(propertyName));
				String str = "aaa";// NCLangRes.getInstance().getStrByID("pfworkflow",
									// propertyName);
				descriptor.setDisplayName(str == null ? propertyName : str);
			} catch (MissingResourceException e) {
				// ignore, the resource may not be provided
			}
			try {
				// 雷军 修改为使用统一资源管理器 2004-10-08
				// descriptor.setShortDescription(ResManager.getLanguageDependentString(propertyName
				// + ".shortDescription"));
				String shortDescription = "aa";// NCLangRes.getInstance().getStrByID("pfworkflow",propertyName
												// + ".shortDescription");
				descriptor.setShortDescription(shortDescription);
			} catch (MissingResourceException e) {
				// ignore, the resource may not be provided
			}
			addPropertyDescriptor(descriptor);
			return descriptor;
		} catch (IntrospectionException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Get the icon for displaying this bean.
	 * 
	 * @param kind
	 *            Kind of icon.
	 * @return Image for bean, or null if none.
	 */
	public Image getIcon(int kind) {
		return null;
	}

	/**
	 * Return a text describing the object.
	 * 
	 * @param value
	 *            an <code>Object</code> value
	 * @return a text describing the object.
	 */
	public String getText(Object value) {
		return value.toString();
	}

	/**
	 * Return a text describing briefly the object. The text will be used
	 * whereever a explanation is needed to give to the user
	 * 
	 * @param value
	 *            an <code>Object</code> value
	 * @return a <code>String</code> value
	 */
	public String getDescription(Object value) {
		return getText(value);
	}

	/**
	 * Return a text describing the object. The text will be displayed in a
	 * tooltip.
	 * 
	 * @param value
	 *            an <code>Object</code> value
	 * @return a <code>String</code> value
	 */
	public String getToolTipText(Object value) {
		return getText(value);
	}

}