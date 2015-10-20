package classloader;

import java.lang.reflect.Method;

/**
 * 测试不同类加载器加载相同的类是否相等
 * @author zhaojianc
 *
 */
public class TSClassEqu {
	public void testClassIdentity() { 
	    String classDataRootPath = "C:\\workspace\\Classloader\\classData"; 
	    FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath); 
	    FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath); 
	    String className = "com.example.Sample"; 	
	    try { 
	        Class<?> class1 = fscl1.loadClass(className); 
	        Object obj1 = class1.newInstance(); 
	        Class<?> class2 = fscl2.loadClass(className); 
	        Object obj2 = class2.newInstance(); 
	        Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class); 
	        setSampleMethod.invoke(obj1, obj2); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	 }
}

