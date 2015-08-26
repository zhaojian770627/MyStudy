package CodeDesigner.ui.sheet;

import java.beans.BeanInfo;
import java.util.logging.Logger;

/**
 * 缺省Bean信息解释器. <br>
 * 
 * @author 雷军 2004-5-6 <BR>
 *         修改人：雷军 2004-12-2 修改对象的BeanInfo类提供方式
 */
public class DefaultBeanInfoResolver implements BeanInfoResolver {

	public DefaultBeanInfoResolver() {
		super();
	}

	public BeanInfo getBeanInfo(Object object) {
		if (object == null) {
			return null;
		}

		// lj@ 2004-12-2
		if (object instanceof IBeanInfoProvider) {
			String beanInfoClassName = ((IBeanInfoProvider) object)
					.provideBeanInfoClass();
			try {
				BeanInfo beanInfo = (BeanInfo) Class.forName(beanInfoClassName)
						.newInstance();
				return beanInfo;
			} catch (Exception e) {
				//Logger.error(e.getMessage(), e);
				return null;
			}
		} else
			return getBeanInfo(object.getClass());
	}

	public BeanInfo getBeanInfo(Class clazz) {
		if (clazz == null) {
			return null;
		}

		String classname = clazz.getName();

		// look for .impl.basic., remove it and call getBeanInfo(class)
		int index = classname.indexOf(".impl.basic");
		if (index != -1 && classname.endsWith("Basic")) {
			classname = classname.substring(0, index)
					+ classname.substring(index + ".impl.basic".length(),
							classname.lastIndexOf("Basic"));
			try {
				return getBeanInfo(Class.forName(classname));
			} catch (ClassNotFoundException e) {
				//Logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			try {
				BeanInfo beanInfo = (BeanInfo) Class.forName(
						classname + "BeanInfo").newInstance();
				return beanInfo;
			} catch (Exception e) {
				//Logger.error(e.getMessage(), e);
				return null;
			}
		}
	}

}