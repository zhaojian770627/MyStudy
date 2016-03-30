package openframework.spring.springinaction.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * »ùÓÚAspectj×¢½â
 * 
 * @author zhaojianc
 *
 */
public class t3 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("3.xml", t3.class); // <co
		Thinker performer = (Thinker) context.getBean("volunteer");
		performer.thinkOfSomething("abc");
	}
}
