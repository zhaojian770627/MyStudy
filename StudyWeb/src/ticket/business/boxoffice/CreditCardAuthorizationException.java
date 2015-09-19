
package ticket.business.boxoffice;

import ticket.business.exceptions.ApplicationException;
import ticket.framework.interface21.core.ErrorCoded;

public class CreditCardAuthorizationException extends ApplicationException {

	/**
	 * Constructor for CreditCardAuthorizationException.
	 * @param s
	 */
	public CreditCardAuthorizationException(String s) {
		super(s);
	}
	
	/**
	 * @see ErrorCoded#getErrorCode()
	 */
	public String getErrorCode() {
		return "creditCardAuthorizationFaile";
	}

}
