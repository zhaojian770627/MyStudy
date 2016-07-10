package springweb.remoteclient;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import springweb.model.Spitter;
import springweb.service.SpitterService;

public class RemoteMain {

	public static void main(String[] args) {
		//callRmi();
		//callHessian();
		//callbulap();
		//callhttpInvoker();
		callws();
	}
	
	private static void callws() {
		ApplicationContext context = new ClassPathXmlApplicationContext("jaxws-client-context.xml", RemoteMain.class); // <co
		SpitterService spitterService = (SpitterService) context.getBean("spitterService");
		Spitter spitter=spitterService.getSpitter(44);
		System.out.println(spitter.getUsername());			
	}

	private static void callhttpInvoker() {
		ApplicationContext context = new ClassPathXmlApplicationContext("httpInvoker-client-context.xml", RemoteMain.class); // <co
		SpitterService spitterService = (SpitterService) context.getBean("spitterService");
		Spitter spitter=spitterService.getSpitter(44);
		System.out.println(spitter.getUsername());		
	}
	
	private static void callbulap() {
		ApplicationContext context = new ClassPathXmlApplicationContext("burlap-client-context.xml", RemoteMain.class); // <co
		SpitterService spitterService = (SpitterService) context.getBean("spitterService");
		Spitter spitter=spitterService.getSpitter(44);
		System.out.println(spitter.getUsername());		
	}
	
	private static void callHessian() {
		ApplicationContext context = new ClassPathXmlApplicationContext("hessian-client-context.xml", RemoteMain.class); // <co
		SpitterService spitterService = (SpitterService) context.getBean("spitterService");
		Spitter spitter=spitterService.getSpitter("zhaojian");
		System.out.println(spitter.getUsername());		
	}

	static void callRmi()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext("rmi-client-context.xml", RemoteMain.class); // <co
		SpitterService spitterService = (SpitterService) context.getBean("spitterService");
		Spitter spitter=spitterService.getSpitter(44);
		System.out.println(spitter.getUsername());
	}

}
