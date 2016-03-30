package openframework.spring.springinaction.aspectj;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {
	@Pointcut("execution(* openframework.spring.springinaction.aspectj.Performer.perform(..))")
	public void performance() { // <co id="co_definePointcut"/>
	}

	@Before("performance()")
	public void takeSeats() { // <co id="co_takeSeatsBefore"/>
		System.out.println("The audience is taking their seats.");
	}

	@Before("performance()")
	public void turnOffCellPhones() { // <co id="co_turnOffCellPhonesBefore"/>
		System.out.println("The audience is turning off their cellphones");
	}

	@AfterReturning("performance()")
	public void applaud() { // <co id="co_applaudAfter"/>
		System.out.println("CLAP CLAP CLAP CLAP CLAP");
	}

	@AfterThrowing("performance()")
	public void demandRefund() { // <co id="co_demandRefundAfterException"/>
		System.out.println("Boo! We want our money back!");
	}
}