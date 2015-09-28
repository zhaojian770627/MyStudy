
package ticket.command;

import ticket.business.referencedata.PriceBand;

public interface PriceBandWithAvailability extends PriceBand {
	
	int getFreeSeats();

}
