package j2ee.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Hello EJB组件的Home接口
 * 它由EJB服务器提供的工具实现
 * Home接口实现称之为Home对象
 * Home对象充当了创建EJB对象的工厂
 * 
 * 在该Home接口总，存在create()方法
 * 它对应于HelloBean中的ejbCreate()方法
 * @author zhaojian
 *
 */
public interface HelloHome extends EJBHome {

	/**
	 * 创建EJB对象
	 * @return 新创建的EJB对象
	 * 
	 * @throws RemoteException
	 * @throws CreateException
	 */
	Hello create() throws RemoteException,CreateException;
}
