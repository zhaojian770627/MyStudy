package CodeDesigner.ui.sheet;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

/**
 * À©Õ¹µÄÊôÐÔÃèÊöÆ÷. <br>
 * 
 * @author À×¾ü 2004-5-6
 */
public class ExtendedPropertyDescriptor extends PropertyDescriptor {

	private String category = "";

	public ExtendedPropertyDescriptor(String propertyName, Class beanClass)
			throws IntrospectionException {
		super(propertyName, beanClass);
	}

	public ExtendedPropertyDescriptor(String propertyName, Method getter,
			Method setter) throws IntrospectionException {
		super(propertyName, getter, setter);
	}

	public ExtendedPropertyDescriptor(String propertyName, Class beanClass,
			String getterName, String setterName) throws IntrospectionException {
		super(propertyName, beanClass, getterName, setterName);
	}

	/**
	 * Sets this property category
	 * 
	 * @param category
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setCategory(String category) {
		this.category = category;
		return this;
	}

	/**
	 * @return the category in which this property belongs
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Force this property to be readonly
	 * 
	 * @return this property for chaining calls.
	 */
	public ExtendedPropertyDescriptor setReadOnly() {
		try {
			setWriteMethod(null);
		} catch (IntrospectionException e) {
			//ÏÈ×¢ÊÍµô
			//Logger.error(e.getMessage(), e);
		}
		return this;
	}
	//
	// public static final Comparator BY_CATEGORY_COMPARATOR = new Comparator()
	// {
	// public int compare(Object o1, Object o2) {
	// PropertyDescriptor desc1 = (PropertyDescriptor)o1;
	// PropertyDescriptor desc2 = (PropertyDescriptor)o2;
	//
	// if (desc1 == null && desc2 == null) {
	// return 0;
	// } else if (desc1 != null && desc2 == null) {
	// return 1;
	// } else if (desc1 == null && desc2 != null) {
	// return -1;
	// } else {
	// if (desc1 instanceof ExtendedPropertyDescriptor
	// && !(desc2 instanceof ExtendedPropertyDescriptor)) {
	// return -1;
	// } else if (
	// !(desc1 instanceof ExtendedPropertyDescriptor)
	// && desc2 instanceof ExtendedPropertyDescriptor) {
	// return 1;
	// } else if (
	// !(desc1 instanceof ExtendedPropertyDescriptor)
	// && !(desc2 instanceof ExtendedPropertyDescriptor)) {
	// return String.CASE_INSENSITIVE_ORDER.compare(
	// desc1.getDisplayName(),
	// desc2.getDisplayName());
	// } else {
	// int category =
	// String.CASE_INSENSITIVE_ORDER.compare(
	// ((ExtendedPropertyDescriptor)desc1).getCategory() == null
	// ? ""
	// : ((ExtendedPropertyDescriptor)desc1).getCategory(),
	// ((ExtendedPropertyDescriptor)desc2).getCategory() == null
	// ? ""
	// : ((ExtendedPropertyDescriptor)desc2).getCategory());
	// if (category == 0) {
	// return String.CASE_INSENSITIVE_ORDER.compare(
	// desc1.getDisplayName(),
	// desc2.getDisplayName());
	// } else {
	// return category;
	// }
	// }
	// }
	// }
	// };

}
