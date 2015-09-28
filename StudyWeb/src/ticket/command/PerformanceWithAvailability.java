
package ticket.command;

import ticket.business.referencedata.Performance;
import ticket.framework.interface21.core.TimeStamped;

public interface PerformanceWithAvailability extends Performance, TimeStamped {

	int getFreeSeats();
	
}
