
package ticket.business.boxoffice.support;

import ticket.business.boxoffice.InvalidSeatingRequestException;
import ticket.business.boxoffice.NotEnoughSeatsException;

/**
 * Interface representing seating in a class.
 * We can disconnect this from the database.
 */
public interface SeatTypeAvailability {
	
	
	// must be adjacent param!?
	SeatAllocation allocateSeats(int howMany, boolean mustBeAdjacent) throws NotEnoughSeatsException, InvalidSeatingRequestException;
	
	int getFreeSeatCount();


}
