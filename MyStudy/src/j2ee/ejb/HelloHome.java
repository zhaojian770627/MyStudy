package j2ee.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

/**
 * Hello EJB�����Home�ӿ�
 * ����EJB�������ṩ�Ĺ���ʵ��
 * Home�ӿ�ʵ�ֳ�֮ΪHome����
 * Home����䵱�˴���EJB����Ĺ���
 * 
 * �ڸ�Home�ӿ��ܣ�����create()����
 * ����Ӧ��HelloBean�е�ejbCreate()����
 * @author zhaojian
 *
 */
public interface HelloHome extends EJBHome {

	/**
	 * ����EJB����
	 * @return �´�����EJB����
	 * 
	 * @throws RemoteException
	 * @throws CreateException
	 */
	Hello create() throws RemoteException,CreateException;
}
