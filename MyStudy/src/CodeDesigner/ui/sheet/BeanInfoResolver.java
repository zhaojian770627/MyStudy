package CodeDesigner.ui.sheet;

import java.beans.BeanInfo;

/**
 * Bean��Ϣ������.<br>
 * @author �׾� 2004-5-6
 */
public interface BeanInfoResolver  {

  public BeanInfo getBeanInfo(Object object);

  public BeanInfo getBeanInfo(Class clazz);

}
