package system.network.socket.compute;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

public class NetClassLoader extends ClassLoader {
	private Hashtable classes = new Hashtable();

	public NetClassLoader() {
	}

	@Override
	public Class<?> loadClass(String className) throws ClassNotFoundException {
		return (loadClass(className, true));
	}

	@Override
	public synchronized Class loadClass(String className, boolean resolveIt) {
		Class result;
		byte classData[];
		result = (Class) classes.get(className);
		if (result == null) {
			try {
				result = findSystemClass(className);
				if (result != null)
					classes.put(className, result);
			} catch (Exception e) {

			}
		}

		if (result == null) {
			classData = null;
			if (0 == className.indexOf("http://")) {
				classData = loadnet(className);
			}
			if (classData != null) {
				result = defineClass(classData, 0, classData.length);
				if (resolveIt)
					resolveClass(result);
				if (result != null) {
					classes.put(className, result);
				}
			}
		}
		return result;
	}

	private byte[] loadnet(String name) {
		URL url = null;
		DataInputStream dis = null;
		URLConnection urlc = null;
		byte data[];
		int filesize;
		System.out.println("Loading " + name + " from the network");
		try {
			url = new URL(name);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		try {
			urlc = url.openConnection();
			dis = new DataInputStream(urlc.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

		filesize = urlc.getContentLength();
		data = new byte[filesize];
		try {
			dis.readFully(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(data==null)
			System.out.println("DATA=NULL");
		
		return data;
	}
}
