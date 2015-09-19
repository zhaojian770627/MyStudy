
package ticket.business.boxoffice;

import ticket.business.exceptions.ApplicationException;
import ticket.framework.interface21.core.ErrorCoded;


/**
 * Should really be unchecked, but we want to ensure
 * that it won't be lost inside the EJB container and that
 * it will be reported to a client without rollback
 */
public class BoxOfficeInternalException extends ApplicationException {

	/**
	 * Constructor for BoxOfficeInternalException.
	 * @param s
	 */
	public BoxOfficeInternalException(String s) {
		super(s);
	}

	/**
	 * @see ErrorCoded#getErrorCode()
	 */
	public String getErrorCode() {
		return "boxOfficeInternalFailure";
	}

}
