package classloader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.ejb.EJBLocalHome;

public class TestDynamicProxyMain {

	public static void main(String[] args) throws IOException {
		TestDynamicProxyMain m = new TestDynamicProxyMain();
		m.execute();
	}

	void execute() {
		BizImp imp = new BizImp();
		Object proxy = Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { IBiz.class },
				new LocalEjbProxy(imp));
		IBiz biz = (IBiz) proxy;
		int i = biz.getNumber();
		System.out.println(i);
	}

	private class LocalEjbProxy extends DynamicProxy {

		private EJBLocalHome home;

		/**
		 * Constructor for LocalEjbProxy.
		 * 
		 * @param wrappedObject
		 * @param className
		 */
		public LocalEjbProxy(Object ejbObject) {
			super(ejbObject);
		}

		/**
		 * Refresh the session bean instance if necessary before method
		 * invocation
		 * 
		 * @see DynamicProxy#beforeInvocation(Object, Method, Object[])
		 */
		protected boolean beforeInvocation(Object proxy, Method method, Object[] args) {
			IBiz newEjbObject = new BizImp();
			switchTarget(newEjbObject);
			return true;
		}
	}

}
