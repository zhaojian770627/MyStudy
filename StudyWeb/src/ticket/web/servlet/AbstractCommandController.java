package ticket.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ticket.framework.interface21.validation.Errors;
import ticket.web.servlet.mvc.AbstractController;
import ticket.web.servlet.mvc.BaseCommandController;

public abstract class AbstractCommandController extends BaseCommandController {

	/**
	 * Constructor for AbstractCommandController.
	 * @param commandClass
	 */
	public AbstractCommandController(Class commandClass, String beanName) {
		setCommandClass(commandClass);
		setBeanName(beanName);
	}
	
	public AbstractCommandController(Class commandClass) {
		setCommandClass(commandClass);
	}

	/**
	 * Constructor for AbstractCommandController.
	 */
	public AbstractCommandController() {
	}

	/**
	 * @see AbstractController#handleRequestInternal(HttpServletRequest, HttpServletResponse)
	 */
	protected final ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Object command = userObject(request);
		Errors errors = bindAndValidate(request, command);
		return handle(request, response, command, errors);
		
		// PUBLISH EVENT

	}

	/**
	 * Subclasses must check for errors
	 */
	protected abstract ModelAndView handle(
		HttpServletRequest request,
		HttpServletResponse response,
		Object command,
		Errors errors);

}