package classloader.b;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class B1 extends Thread {
	public B1() {
		Thread.currentThread().setContextClassLoader(MyClassLoad.getInstance());
	}

	@Override
	public void run() {
		try {
			Class<?> c = Class.forName("classloader.a.CalImp", false, MyClassLoad.getInstance());
			Object cmp = c.newInstance();
			Class<?> cls = cmp.getClass();
			Method m = cls.getDeclaredMethod("add");
			int i = (int) m.invoke(cmp);
			System.out.println(i);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
