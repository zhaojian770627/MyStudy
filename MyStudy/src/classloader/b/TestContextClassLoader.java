package classloader.b;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class TestContextClassLoader {

	public static void main(String[] args) throws MalformedURLException {
		File xFile=new File("d:/a1.jar"); 
		URL  xUrl= xFile.toURL() ;  
		MyClassLoad ClassLoader=new MyClassLoad(new URL[]{ xUrl });  
		B1 b1=new B1();
		b1.setContextClassLoader(ClassLoader);
		b1.run();
	}

}
