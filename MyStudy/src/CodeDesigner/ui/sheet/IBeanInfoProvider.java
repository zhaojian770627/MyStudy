package CodeDesigner.ui.sheet;

/**
 * 如果某个对象需要被属性编辑器编辑，需要提供一个BeanInfo类
 * @author 雷军 created on 2004-12-2
 */
public interface IBeanInfoProvider {
  /**
   * 获得该对象对应的BeanInfo类名
   * @return
   */
  public String provideBeanInfoClass();
}
