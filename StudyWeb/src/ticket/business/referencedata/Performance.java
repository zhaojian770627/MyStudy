
package ticket.business.referencedata;

import java.util.Date;
import java.util.List;

public interface Performance extends ReferenceItem {

	Show getShow();
	
	Date getWhen();
	
	List getPriceBands();
	
	PriceBand getPriceBandForSeatType(int seatTypeId); // throws IllegalA;

}
