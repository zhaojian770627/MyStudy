package ticket.web.servlet.mvc.multiaction;

import javax.servlet.http.HttpServletRequest;

public interface MethodNameResolver {
	
	/** Never return null */
	String getHandlerMethodName(HttpServletRequest request) throws NoSuchRequestHandlingMethodException;
}