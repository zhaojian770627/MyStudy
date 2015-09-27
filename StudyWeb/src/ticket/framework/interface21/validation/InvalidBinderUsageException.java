
package ticket.framework.interface21.validation;

import ticket.framework.interface21.core.NestedRuntimeException;

public class InvalidBinderUsageException extends NestedRuntimeException {

	/**
	 * Constructor for InvalidBinderUsageException.
	 * @param s
	 */
	public InvalidBinderUsageException(String s) {
		super(s);
	}

	/**
	 * Constructor for InvalidBinderUsageException.
	 * @param s
	 * @param ex
	 */
	public InvalidBinderUsageException(String s, Throwable ex) {
		super(s, ex);
	}
}
