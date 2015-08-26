
package ticket.interface21.jdbc.core;

import ticket.interface21.dao.DataAccessResourceFailureException;

public class CannotGetJdbcConnectionException extends DataAccessResourceFailureException {

	/**
	 * Constructor for CannotGetJdbcConnectionException.
	 * @param s
	 * @param ex
	 */
	public CannotGetJdbcConnectionException(String s, Throwable ex) {
		super(s, ex);
	}

}
