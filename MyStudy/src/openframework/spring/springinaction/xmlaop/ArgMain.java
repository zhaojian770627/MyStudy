package openframework.spring.springinaction.xmlaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ArgMain {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("xmlaopArg.xml", ArgMain.class); // <co

		Thinker think1 = (Thinker) context.getBean("volunteer");
		Magician magician = (Magician) context.getBean("magician");
		think1.thinkOfSomething("abc");
		System.out.println(magician.getThoughts());
	}
}
