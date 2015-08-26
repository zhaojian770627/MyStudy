package CodeDesigner.ui.sheet;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * 属性单的表模型. <br>
 * 
 * @author 雷军 2004-5-6 <BR>
 *         修改人：雷军 2004-12-15 修改setValueAt()方法
 */
public class PropertySheetTableModel extends AbstractTableModel implements
		PropertyChangeListener {

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	private List model;

	private List publishedModel;

	private Property[] properties;

	private int mode;

	private boolean sortingCategories;

	private boolean sortingProperties;

	private Comparator categorySortingComparator;

	private Comparator propertySortingComparator;

	public PropertySheetTableModel() {
		model = new ArrayList();
		publishedModel = new ArrayList();
		mode = PropertySheet.VIEW_AS_FLAT_LIST;
		sortingCategories = false;
		sortingProperties = false;
	}

	public void setProperties(Property[] properties) {
		// unregister the listeners from previous properties
		Property[] oldProperties = this.properties;
		if (oldProperties != null) {
			for (int i = 0, c = oldProperties.length; i < c; i++) {
				oldProperties[i].removePropertyChangeListener(this);
			}
		}

		this.properties = properties;
		// register the listeners to new properties
		if (properties != null) {
			for (int i = 0, c = properties.length; i < c; i++) {
				properties[i].addPropertyChangeListener(this);
			}
		}

		buildModel();
	}

	public Property[] getProperties() {
		return properties;
	}

	public void setMode(int mode) {
		if (this.mode == mode) {
			return;
		}

		this.mode = mode;
		buildModel();
	}

	public int getMode() {
		return mode;
	}

	public Class getColumnClass(int columnIndex) {
		return super.getColumnClass(columnIndex);
	}

	public int getColumnCount() {
		return 2;
	}

	public int getRowCount() {
		return publishedModel.size();
	}

	public Object getPropertySheetElement(int rowIndex) {
		return publishedModel.get(rowIndex);
	}

	public boolean isSortingCategories() {
		return sortingCategories;
	}

	public void setSortingCategories(boolean value) {
		sortingCategories = value;
		buildModel();
	}

	public boolean isSortingProperties() {
		return sortingProperties;
	}

	public void setSortingProperties(boolean value) {
		sortingProperties = value;
		buildModel();
	}

	public void setCategorySortingComparator(Comparator comp) {
		categorySortingComparator = comp;
		buildModel();
	}

	public void setPropertySortingComparator(Comparator comp) {
		propertySortingComparator = comp;
		buildModel();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Object o = getPropertySheetElement(rowIndex);
		if (o instanceof Property) {
			switch (columnIndex) {
			case 0:
				return o;
			case 1:
				try {
					return ((Property) o).getValue();
				} catch (Exception e) {
					//先暂时隐掉
					//Logger.error(e.getMessage(), e);
					return null;
				}
			default:
				// will not happen
			}
		} else if (o instanceof Category) {
			return o;
		}
		return null;
	}

	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Object o = getPropertySheetElement(rowIndex);
		if (o instanceof Property) {
			if (columnIndex == 1) {
				try {
					// lj@
					// ((Property) o).setValue(value);
					if (value instanceof ValueTag)
						((Property) o).setValue(((ValueTag) value).getValue());
					else
						((Property) o).setValue(value);
				} catch (Exception e) {
					//先暂时隐掉
					//Logger.error(e.getMessage(), e);
				}
			}
		}
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// forward the event to registered listeners
		listeners.firePropertyChange(evt);
	}

	private void categoryVisibilityChanged() {
		publishedModel.clear();

		Category currentCategory = null;
		for (Iterator iter = model.iterator(); iter.hasNext();) {
			Object o = iter.next();
			if (o instanceof Category) {
				currentCategory = (Category) o;
				publishedModel.add(o);
			} else if (currentCategory != null && currentCategory.isVisible()) {
				publishedModel.add(o);
			}
		}

		fireTableDataChanged();
	}

	private void buildModel() {
		model.clear();

		publishedModel.clear();

		if (properties != null && properties.length > 0) {

			if (PropertySheet.VIEW_AS_FLAT_LIST == mode) {
				Property[] tempProperties = new Property[properties.length];
				// copy the original array so that we don't mess the original
				// order
				for (int a = 0; a < properties.length; a++) {
					tempProperties[a] = properties[a];
				}
				if (sortingProperties) {
					if (propertySortingComparator == null) {
						// if no comparator was defined by the user, use the
						// default
						propertySortingComparator = new PropertyComparator();
					}
					Arrays.sort(tempProperties, propertySortingComparator);
				}
				for (int i = 0, c = tempProperties.length; i < c; i++) {
					model.add(tempProperties[i]);
				}

			} else if (PropertySheet.VIEW_AS_CATEGORIES == mode) {
				// build the list of unique categories
				List differentCategories = new ArrayList();
				for (int i = 0, c = properties.length; i < c; i++) {
					if (!differentCategories.contains(properties[i]
							.getCategory())) {
						differentCategories.add(properties[i].getCategory());
					}
				}
				String[] categories = (String[]) differentCategories
						.toArray(new String[0]);
				// if category sorting is enabled
				if (sortingCategories) {
					if (categorySortingComparator == null) {
						categorySortingComparator = STRING_COMPARATOR;
					}
					Arrays.sort(categories, categorySortingComparator);
				}
				for (int i = 0, c = categories.length; i < c; i++) {
					model.add(new Category(categories[i]));
					Property[] tempProperties = getPropertiesForCategory(
							properties, categories[i]);
					if (sortingProperties) {
						if (propertySortingComparator == null) {
							propertySortingComparator = new PropertyComparator();
						}
						Arrays.sort(tempProperties, propertySortingComparator);
					}
					for (int a = 0; a < tempProperties.length; a++) {
						model.add(tempProperties[a]);
					}
				}
			}
			publishedModel.addAll(model);
		}

		fireTableDataChanged();
	}

	/*
	 * Convinience method to get all the properties of one category.
	 */
	private Property[] getPropertiesForCategory(Property[] props,
			String category) {
		List propList = new ArrayList();
		for (int a = 0; a < props.length; a++) {
			if ((category == props[a].getCategory())
					|| (category != null && category.equals(props[a]
							.getCategory()))) {
				propList.add(props[a]);
			}
		}
		Property[] propArray = new Property[propList.size()];
		propList.toArray(propArray);
		return propArray;
	}

	public class Category {
		private String name;
		private boolean visible = true;

		public Category(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void toggle() {
			visible = !visible;
			categoryVisibilityChanged();
		}

		public boolean isVisible() {
			return visible;
		}
	}

	/**
	 * The default comparator for Properties. Used if no other comparator is
	 * defined.
	 * 
	 */
	public static  class PropertyComparator implements Comparator,Serializable {
		public int compare(Object o1, Object o2) {
			if (o1 instanceof Property && o2 instanceof Property) {
				Property prop1 = (Property) o1;
				Property prop2 = (Property) o2;
				if (prop1 == null) {
					return prop2 == null ? 0 : -1;
				} else {
					return STRING_COMPARATOR.compare(
							prop1.getDisplayName() == null ? null : prop1
									.getDisplayName().toLowerCase(), prop2
									.getDisplayName() == null ? null : prop2
									.getDisplayName().toLowerCase());
				}
			} else {
				return 0;
			}
		}
	}

	private static final Comparator STRING_COMPARATOR = new NaturalOrderStringComparator();

	public static class NaturalOrderStringComparator implements Comparator,Serializable{
		public int compare(Object o1, Object o2) {
			String s1 = (String) o1;
			String s2 = (String) o2;
			if (s1 == null) {
				return s2 == null ? 0 : -1;
			} else {
				if (s2 == null) {
					return 1;
				} else {
					return s1.compareTo(s2);
				}
			}
		}
	}
}