
package ticket.framework.interface21.jdbc.core;

import ticket.framework.interface21.dao.CleanupFailureDataAccessException;



public class CannotCloseJdbcConnectionException extends CleanupFailureDataAccessException {

	/**
	 * Constructor for CannotGetJdbcConnectionException.
	 * @param s
	 * @param ex
	 */
	public CannotCloseJdbcConnectionException(String s, Throwable ex) {
		super(s, ex);
	}

}
