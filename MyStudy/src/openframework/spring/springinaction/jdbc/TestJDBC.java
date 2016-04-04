package openframework.spring.springinaction.jdbc;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestJDBC {

	@Test
	public void testAddData() throws ClassNotFoundException, SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("dataAccessContext-local.xml",
				TestJDBC.class); // <co
		SpitterDao dao=(SpitterDao) context.getBean("spitterDao");
		Spitter spitter=new Spitter();
		spitter.setUsername("zhaojian");
		spitter.setFullName("zj");
		spitter.setPassword("zj");
		spitter.setEmail("zhaojian770627@163.com");
		spitter.setUpdateByEmail(false);
		dao.addSpitter(spitter);
	}

}
