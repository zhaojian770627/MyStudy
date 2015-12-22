package classloader.b;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoad extends URLClassLoader {

	private static MyClassLoad instance = newInstance();

	public static MyClassLoad getInstance() {
		return instance;
	}

	private static MyClassLoad newInstance() {
		MyClassLoad ret = null;
		File xFile = new File("e:/calimp.jar");
		URL xUrl = null;
		try {
			xUrl = xFile.toURL();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		ret = new MyClassLoad(new URL[] { xUrl });
		return ret;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}

	public MyClassLoad(URL[] urls) {
		super(urls);
	}
	
	

}
