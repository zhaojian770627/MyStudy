package classloader;

import java.io.IOException;

public class ClassLoaderTree {

	public static void main(String[] args) throws IOException {
		ClassLoader loader = ClassLoaderTree.class.getClassLoader();
		while (loader != null) {
			System.out.println(loader.toString());
			loader = loader.getParent();
		}
		System.in.read();
	}
}