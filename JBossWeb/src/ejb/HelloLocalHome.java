package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * Hello EJB组件的本地Home接口
 * 它由EJB服务器提供的工具实现
 * 本地Home接口实现称之为本地Home对象
 * 本地Home对象充当了创建EJB本地对象的工厂
 * 
 * @author zhaojian
 *
 */
public interface HelloLocalHome extends EJBLocalHome {
	/**
	 * 创建EJB本地对象
	 * @return 返回新创建的EJB本地对象
	 * @throws CreateException
	 */
	HelloLocal create() throws CreateException;
}
