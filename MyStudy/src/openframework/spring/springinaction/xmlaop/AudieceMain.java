package openframework.spring.springinaction.xmlaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AudieceMain {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("xmlaop.xml", AudieceMain.class); // <co
		Performer performer1=(Performer) context.getBean("eddie");
		performer1.perform();
		System.out.println("-------------------------");
		Performer performer=(Performer) context.getBean("perform");
		performer.perform();
	}
}
