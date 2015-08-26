package CodeDesigner.ui.sheet;

import java.beans.BeanDescriptor;
import java.util.MissingResourceException;

/**
 * È±Ê¡BeanÃèÊöÆ÷.<br>
 * @author À×¾ü 2004-5-6
 */
final class DefaultBeanDescriptor extends BeanDescriptor {

  private String displayName;

  public DefaultBeanDescriptor(BaseBeanInfo beanInfo) {
    super(beanInfo.getType());
    try {
      //setDisplayName(beanInfo.getResources().getString("beanName"));
      setDisplayName("beanName");
    } catch (MissingResourceException e) {
      // this resource is not mandatory
    }
    try {
      //setShortDescription(beanInfo.getResources().getString("beanDescription"));
      setShortDescription("beanDescription");
    } catch (MissingResourceException e) {
      // this resource is not mandatory
    }
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String p_name) {
    displayName = p_name;
  }

}