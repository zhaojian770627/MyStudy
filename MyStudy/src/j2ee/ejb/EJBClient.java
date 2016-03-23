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

		// 设置JNDI的连接工厂
		// 第一个参数，是规定写法，由JNDI规范所规定
		// 第二个参数，是JBOSS的JNDI连接工厂
		// NamingContextFactory在JBOSS安装目录下client目录下jmp-client包中
		// JNDI通过连接工厂就可以与JBOSS进行连接
		props.setProperty("java.naming.factory.initial", "org.jnp.interfaces.NamingContextFactory");

		// 设置命名服务的连接url
		// 前面也是固定写法
		// 后边是JBOSS的连接字符串,JNDI通过它能得到JBOSS所在的地址和所使用的端口号
		props.setProperty("java.naming.provider.url", "localhost:1099");

		// 如果访问不同的应用服务器，这两行参数设置也不同
		// 如果Application Server不是JBOSS，要查看该Application Server的帮助文档

		InitialContext cts = new InitialContext(props);
		HelloHome helloHome = (HelloHome) cts.lookup("Hello");
		Hello hello=helloHome.create();
		System.out.println(hello.getClass().getName());
		System.out.println(hello.hello());
	}

}
