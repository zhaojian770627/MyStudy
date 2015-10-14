package ejb;

import java.rmi.RemoteException;

import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * 演示无状态会话Bean
 * @author zhaojian
 *
 */
public class HelloBean implements SessionBean {
	private SessionContext ctx;
	
	public void ejbCreate()  {
		System.out.println("ejbCreate");
	}
	@Override
	public void ejbActivate()  {
		System.out.println("ejbActivate");
	}

	@Override
	public void ejbPassivate()  {
		System.out.println("ejbPassivate");

	}

	@Override
	public void ejbRemove()  {
		System.out.println("ejbRemove");

	}

	@Override
	public void setSessionContext(SessionContext ctx)  {
		this.ctx=ctx;
	}

	/**
	 * 业务方法
	 * @return
	 */
	public String hello(){
		System.out.println("hello()");
		return "Hello,World";
	}
}
