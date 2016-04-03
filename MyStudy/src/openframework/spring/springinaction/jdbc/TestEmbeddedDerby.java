package openframework.spring.springinaction.jdbc;

import java.sql.SQLException;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmbeddedDerby {

	@Test
	public void testReadData() throws ClassNotFoundException, SQLException {
		ApplicationContext context = new ClassPathXmlApplicationContext("dataAccessContext-local.xml",
				TestEmbeddedDerby.class); // <co
	}

}
