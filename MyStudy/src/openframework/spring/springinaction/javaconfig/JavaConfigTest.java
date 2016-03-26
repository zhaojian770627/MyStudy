package openframework.spring.springinaction.javaconfig;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigTest {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("javaconfig.xml", JavaConfigTest.class); // <co
		Performer performer=(Performer) context.getBean("duke15");
		performer.perform();
	}
}
