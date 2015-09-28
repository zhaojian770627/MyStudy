
package ticket.command;

import ticket.business.exceptions.NoSuchPerformanceException;
import ticket.business.referencedata.Performance;
import ticket.business.referencedata.Show;

public interface AvailabilityCheck {
	
	Show getShowWithAvailability(Show s, boolean acceptCached) throws NoSuchPerformanceException;
	
	PerformanceWithAvailability getPerformanceWithAvailability(Performance p, boolean acceptCached) throws NoSuchPerformanceException;

}
