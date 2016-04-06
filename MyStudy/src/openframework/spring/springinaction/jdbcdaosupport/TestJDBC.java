package openframework.spring.springinaction.jdbcdaosupport;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Assert;
import openframework.spring.springinaction.jdbc.Spitter;
import openframework.spring.springinaction.jdbc.SpitterDao;

public class TestJDBC {
	
	@Test
	public void testGetData()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("dataAccessContext-local.xml",
				TestJDBC.class); // <co
		SpitterDao dao=(SpitterDao) context.getBean("spitterDao");
		Spitter spitter=dao.getSpitterById(41);
		Assert.assertTrue(spitter!=null);
	}
}
