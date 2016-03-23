package j2ee.ejb;

import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.ejb.RemoveException;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EJBClient {

	public static void main(String[] args) throws NamingException, RemoteException, CreateException, RemoveException {
		Properties props = new Properties();

		// ����JNDI�����ӹ���
		// ��һ���������ǹ涨д������JNDI�淶���涨
		// �ڶ�����������JBOSS��JNDI���ӹ���
		// NamingContextFactory��JBOSS��װĿ¼��clientĿ¼��jmp-client����
		// JNDIͨ�����ӹ����Ϳ�����JBOSS��������
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");

		// �����������������url
		// ǰ��Ҳ�ǹ̶�д��
		// �����JBOSS�������ַ���,JNDIͨ�����ܵõ�JBOSS���ڵĵ�ַ����ʹ�õĶ˿ں�
		props.setProperty("java.naming.provider.url", "localhost:1099");

		// ������ʲ�ͬ��Ӧ�÷������������в�������Ҳ��ͬ
		// ���Application Server����JBOSS��Ҫ�鿴��Application Server�İ����ĵ�

		InitialContext cts = new InitialContext(props);
		HelloHome helloHome = (HelloHome) cts.lookup("Hello");
		Hello hello=helloHome.create();
		System.out.println(hello.getClass().getName());
		System.out.println(hello.hello());
	}

}
