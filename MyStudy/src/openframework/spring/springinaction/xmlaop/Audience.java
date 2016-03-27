package openframework.spring.springinaction.xmlaop;

import org.aspectj.lang.ProceedingJoinPoint;

public class Audience {
	public void takeSeats(){
		System.out.println("The audience is taking their seats.");
	}
	
	public void turnOffCellPhones(){
		System.out.println("The audience is turning off cellphones.");
	}
	
	public void applaud(){
		System.out.println("CLAP CLAP CLAP CLAP CLAP CLAP CLAP.");
	}
	
	public void demandRefund(){
		System.out.println("Boo! We want our money back.");
	}
	
	public void watchPerfomance(ProceedingJoinPoint joinpoint){
		try{
			System.out.println("watchPerfomance:The audience is taking their seats.");
			System.out.println("watchPerfomance:The audience is turning off their cellphones");
			long start=System.currentTimeMillis();
			joinpoint.proceed();
			long end=System.currentTimeMillis();
			System.out.println("watchPerfomance:CLAP CLAP CLAP CLAP CLAP CLAP");
			System.out.println("watchPerfomance:The performance took "+(end-start)+" milliseconds.");
		}catch(Throwable t){
			System.out.println("watchPerfomance:Boo!We want our money back!");
		}
	}
}
