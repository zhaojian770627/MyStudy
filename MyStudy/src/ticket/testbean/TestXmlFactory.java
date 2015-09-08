package ticket.testbean;


import java.io.IOException;
import java.io.InputStream;

import ticket.interface21.beans.factory.support.XmlBeanFactory;

public class TestXmlFactory {

	public static void main(String[] args) throws IOException {
		InputStream input=TestXmlFactory.class.getResourceAsStream("testbean.xml");
		XmlBeanFactory xbf=new XmlBeanFactory(input);
		Object o=xbf.getBean("Rod");
		int i=0;
		i++;
	}

}
