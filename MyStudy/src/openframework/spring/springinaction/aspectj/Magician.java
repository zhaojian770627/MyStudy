// <start id="magician_java" /> 
package openframework.spring.springinaction.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Magician implements MindReader {
	private String thoughts;

	@Pointcut("execution(* openframework.spring.springinaction.aspectj.Thinker.thinkOfSomething(String)) && args(thoughts)")
	public void thinking(String thoughts) {
	}

	@Before("thinking(thoughts)") // <co id="co_passInParameters"/>
	public void interceptThoughts(String thoughts) {
		System.out.println("Intercepting volunteer's thoughts : " + thoughts);
		this.thoughts = thoughts;
	}

	public String getThoughts() {
		return thoughts;
	}
}
// <end id="magician_java" />
