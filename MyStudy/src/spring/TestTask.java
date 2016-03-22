package spring;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class TestTask extends TestCase {

	@Test
	public void testAAA() {
		DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource("taskbean_config.xml", TestTask.class));
		TaskConfigBean configBean = (TaskConfigBean) factory.getBean("taskConfigBean");

		// String wsdl =
		// "http://20.10.80.93:1028/uapws/service/ssc.wf.util.IInterWFTaskProcess?wsdl";
		assertNotNull(configBean!=null);
	}

}
