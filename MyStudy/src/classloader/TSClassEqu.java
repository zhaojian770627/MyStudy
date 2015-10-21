package classloader;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 测试不同类加载器加载相同的类是否相等
 * 测试本例需要先把Sample编译，然后将class文件移除，不启动自动编译，否则不走自己的classload
 * @author zhaojianc
 *
 */
public class TSClassEqu {
	public void testClassIdentity() { 
	    String classDataRootPath = "E:\\classload"; 
	    FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath); 
	    FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath); 
	    String className = "classloader.Sample"; 	
	    try { 
	        Class<?> class1 = fscl1.loadClass(className); 
	        Object obj1 = class1.newInstance(); 
	        System.out.println(ClassLoaderAnalyzer.showClassLoaderHierarchy(obj1, "zj", "\n", "-"));
	        Class<?> class2 = fscl2.loadClass(className); 
	        Object obj2 = class2.newInstance();
	        System.out.println(ClassLoaderAnalyzer.showClassLoaderHierarchy(obj2, "zj", "\n", "-"));
	        Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class); 
	        setSampleMethod.invoke(obj1, obj2); 
	    } catch (Exception e) { 
	        e.printStackTrace(); 
	    } 
	 }
	
	public static void main(String[] args) throws IOException {
		TSClassEqu e=new TSClassEqu();
		e.testClassIdentity();
	}
}

