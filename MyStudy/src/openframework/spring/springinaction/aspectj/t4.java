package openframework.spring.springinaction.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * »ùÓÚAspectj×¢½â
 * 
 * @author zhaojianc
 *
 */
public class t4 {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("4.xml", t4.class); // <co
		Contestant performer = (Contestant) context.getBean("eddie");
		performer.receiveAward();
		Performer p = (Performer) performer;
		p.perform();
	}
}
