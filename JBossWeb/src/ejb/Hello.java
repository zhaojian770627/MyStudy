package ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * Hello EJB 组件的远程接口
 * 
 * 在客户通EJB对象交互时，
 * 需要使用这一接口
 * 容器厂商会实现这一接口
 * 而相应的实现对象就是EJB对象
 * 
 * @author zhaojian
 *
 */
public interface Hello extends EJBObject {
	public String hello() throws RemoteException;
}
