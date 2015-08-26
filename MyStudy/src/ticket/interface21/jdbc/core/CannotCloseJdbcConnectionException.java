
package ticket.interface21.jdbc.core;

import ticket.interface21.dao.CleanupFailureDataAccessException;



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
