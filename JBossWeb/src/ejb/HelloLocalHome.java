package ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

/**
 * Hello EJB����ı���Home�ӿ�
 * ����EJB�������ṩ�Ĺ���ʵ��
 * ����Home�ӿ�ʵ�ֳ�֮Ϊ����Home����
 * ����Home����䵱�˴���EJB���ض���Ĺ���
 * 
 * @author zhaojian
 *
 */
public interface HelloLocalHome extends EJBLocalHome {
	/**
	 * ����EJB���ض���
	 * @return �����´�����EJB���ض���
	 * @throws CreateException
	 */
	HelloLocal create() throws CreateException;
}
