
package ticket.business.boxoffice.support;

import ticket.business.exceptions.FatalException;

public class DataAnomaly extends FatalException {


	/**
	 * Constructor for DataAnomaly.
	 * @param ex
	 */
	public DataAnomaly(String mesg) {
		super(mesg);
	}


}
