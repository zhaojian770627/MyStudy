package classloader.b;

import java.net.URL;
import java.net.URLClassLoader;

public class MyClassLoad extends URLClassLoader {

	public MyClassLoad(URL[] urls) {
		super(urls);
	}

}
