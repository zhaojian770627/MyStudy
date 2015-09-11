
package ticket.business.boxoffice;

import ticket.business.exceptions.ApplicationException;
import ticket.framework.interface21.core.ErrorCoded;


/**
 * Thrown when a seat reservation has expired and the
 * seats have been sold to someone else
 * @author Rod Johnson
 */
public class ExpiredReservationTakenException extends ApplicationException {

	/**
	 * Constructor for ExpiredReservationTakenException.
	 */
	public ExpiredReservationTakenException(String mesg) {
		super(mesg);
	}
	
	/**
	 * @see ErrorCoded#getErrorCode()
	 */
	public String getErrorCode() {
		return "seatsSold";
	}

}
