package CodeDesigner.ui.sheet;

import java.lang.reflect.Method;

/**
 * Bean工具类 
 * 
 * @author 雷军 2004-5-6 
 */
public class BeanUtilities {

  private BeanUtilities() {}

  /**
   * 获得某类的“读”属性方法
   * @param clazz 类
   * @param propertyName 属性名
   * @return Method
   */
  public static Method getReadMethod(Class clazz, String propertyName) {
    Method readMethod = null;
    String base = capitalize(propertyName);

    // Since there can be multiple setter methods but only one getter
    // method, find the getter method first so that you know what the
    // property type is. For booleans, there can be "is" and "get"
    // methods. If an "is" method exists, this is the official
    // reader method so look for this one first.
    try {
      readMethod = clazz.getMethod("is" + base, null);
    } catch (Exception getterExc) {
      try {
        // no "is" method, so look for a "get" method.
        readMethod = clazz.getMethod("get" + base, null);
      } catch (Exception e) {
        // no is and no get, we will return null
      }
    }

    return readMethod;
  }

  /**
   * 获得某类的“写”属性方法
   * @param clazz 类
   * @param propertyName 属性名称
   * @param propertyType 属性类型
   * @return
   */
  public static Method getWriteMethod(
    Class clazz,
    String propertyName,
    Class propertyType) {
    Method writeMethod = null;
    String base = capitalize(propertyName);

    Class params[] = { propertyType };
    try {
      writeMethod = clazz.getMethod("set" + base, params);
    } catch (Exception e) {
      // no write method
    }

    return writeMethod;
  }

  /**
   * 大写化一个字串
   * @param s
   * @return
   */
  private static String capitalize(String s) {
    if (s.length() == 0) {
      return s;
    } else {
      char chars[] = s.toCharArray();
      chars[0] = Character.toUpperCase(chars[0]);
      return String.valueOf(chars);
    }
  }

}
