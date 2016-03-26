package openframework.spring.springinaction.idol;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IdolMain {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-idol.xml", IdolMain.class); // <co
		Performer performer=(Performer) context.getBean("eddie");
		performer.perform();
	}
}
