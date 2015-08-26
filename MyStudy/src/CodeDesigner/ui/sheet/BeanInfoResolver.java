package CodeDesigner.ui.sheet;

import java.beans.BeanInfo;

/**
 * Bean信息解释器.<br>
 * @author 雷军 2004-5-6
 */
public interface BeanInfoResolver  {

  public BeanInfo getBeanInfo(Object object);

  public BeanInfo getBeanInfo(Class clazz);

}
