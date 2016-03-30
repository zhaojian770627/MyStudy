package openframework.spring.springinaction.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * »ùÓÚAspectj×¢½â
 * 
 * @author zhaojianc
 *
 */
public class t2 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("2.xml", t2.class); // <co
		Performer performer = (Performer) context.getBean("eddie");
		performer.perform();
	}
}
