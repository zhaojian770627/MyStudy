package openframework.spring.springinaction.hibernatetemplate;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.Assert;
import openframework.spring.springinaction.jdbc.Spitter;
import openframework.spring.springinaction.jdbc.SpitterDao;

public class TestHibernate {

	@Test
	public void testAddData() throws ClassNotFoundException, SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "persistence-context.xml",
				"test-dataSource-context.xml", "test-transaction-context.xml" }, TestHibernate.class); // <co
		SpitterDao dao = (SpitterDao) context.getBean("spitterDao");
		Spitter spitter = new Spitter();
		spitter.setUsername("zhaojian1");
		spitter.setFullName("zj1");
		spitter.setPassword("zj1");
		spitter.setEmail("zhaojian770627@163.com");
		spitter.setUpdateByEmail(false);
		dao.addSpitter(spitter);
	}

	@Test
	public void testGetData() {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "persistence-context.xml",
				"test-dataSource-context.xml", "test-transaction-context.xml" }, TestHibernate.class); // <co
		SpitterDao dao = (SpitterDao) context.getBean("hibernateSpitterDao");
		Spitter spitter = dao.getSpitterById(42);
		Assert.assertTrue(spitter != null);
	}
}
