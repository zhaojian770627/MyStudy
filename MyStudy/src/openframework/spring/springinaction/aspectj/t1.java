package openframework.spring.springinaction.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * »ùÓÚAspectj×¢½â
 * 
 * @author zhaojianc
 *
 */
public class t1 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("1.xml", t1.class); // <co
		Performer performer = (Performer) context.getBean("eddie");
		performer.perform();
	}
}
