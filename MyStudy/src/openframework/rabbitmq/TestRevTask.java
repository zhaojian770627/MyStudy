package openframework.rabbitmq;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import junit.framework.TestCase;

public class TestRevTask extends TestCase {
	public static void main(String[] args) throws IOException{
		MQConsumer maAdminbean;
		String mqconfilg = "sscrevmqcfg.xml";

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource(mqconfilg, TaskMQUtil.class));

		maAdminbean = (MQConsumer) factory.getBean("mqAdmin");
		maAdminbean.startRecv();
	}

	@Test
	public void testRevMsg() throws Exception {
		MQConsumer maAdminbean;
		String mqconfilg = "sscrevmqcfg.xml";

		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(new ClassPathResource(mqconfilg, TaskMQUtil.class));

		maAdminbean = (MQConsumer) factory.getBean("mqAdmin");
		maAdminbean.startRecv();
	}

}
