package ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

/**
 * Hello EJB �����Զ�̽ӿ�
 * 
 * �ڿͻ�ͨEJB���󽻻�ʱ��
 * ��Ҫʹ����һ�ӿ�
 * �������̻�ʵ����һ�ӿ�
 * ����Ӧ��ʵ�ֶ������EJB����
 * 
 * @author zhaojian
 *
 */
public interface Hello extends EJBObject {
	public String hello() throws RemoteException;
}
