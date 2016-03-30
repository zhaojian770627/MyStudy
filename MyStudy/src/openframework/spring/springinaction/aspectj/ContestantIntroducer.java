package openframework.spring.springinaction.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

@Aspect
public class ContestantIntroducer {

	@DeclareParents( // <co id="co_declareParents"/>
	value = "openframework.spring.springinaction.aspectj.Performer+", defaultImpl = GraciousContestant.class)
	public static Contestant contestant;
}