package ejb;

import javax.ejb.EJBLocalObject;

/**
 * Hello EJB����ı��ؽӿ�
 * 
 * �����ؿͻ�ͨEJB���ض��󽻻�ʱ
 * ��Ҫʹ����һ�ӿ�
 * �������̻�ʵ����һ�ӿ�
 * ����Ӧ��ʵ�ֶ������EJB���ض���
 * EJB���ض���Ὣ�ͻ�����ί�ɸ�ʵ�ʵ�EJB Bean��
 * 
 * @author zhaojian
 *
 */
public interface HelloLocal extends EJBLocalObject {
	public String hello();
}
