
package ticket.business.boxoffice.support;

import ticket.business.boxoffice.InvalidSeatingRequestException;
import ticket.business.referencedata.PriceBand;


/**
 * Lower-level interface used to implement the BoxOffice
 * interface.
 * Represents a SeatingPlan for one performance.
 * SeatingPlan objects must be threadsafe
 * Designed to allow caching of read-only data.
 */
public interface PerformanceSeatingPlan {
	
	//---------------------------------------------------------------------
	// Cachable data methods
	//---------------------------------------------------------------------

	PriceBand[] getPriceBands();
	
	double getPriceOfSeatType(int classId);
	
	int getSeatCount();
	
	
	//---------------------------------------------------------------------
	// Dynamic methods
	//---------------------------------------------------------------------

	int getFreeSeatCount();
	
	int getFreeSeatCount(int classId); 
	
	//PerformanceWithAvailability getAvailability();
	
	
	/**
	 * This approach means that the created object contains
	 * only the data that changes, and doesn't need to be threadsafe
	 * @param lock guarantees that these seats will be available to the current transaction
	 */
	SeatTypeAvailability newSeatingStatus(int classId, boolean lock) throws InvalidSeatingPlanDataException, InvalidSeatingRequestException;

}
