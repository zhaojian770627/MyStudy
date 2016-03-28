package openframework.spring.springinaction.xmlaop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * ͨ�����������¹���
 * 
 * @author zhaojianc
 *
 */
public class AudieceMainIntroductions {
	public static void main(String[] args) throws PerformanceException {
		ApplicationContext context = new ClassPathXmlApplicationContext("xmlaopintroductions.xml", AudieceMainIntroductions.class); // <co
		Contestant performer1=(Contestant) context.getBean("eddie");
		performer1.receiveAward();
	}
	
}
