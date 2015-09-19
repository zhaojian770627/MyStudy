
package ticket.business.referencedata;

import java.util.List;

public interface Show extends ReferenceItem {
	
	// GetPARENT!?
	
	int getSeatingPlanId();

	List getPerformances();
	
	List getSeatTypes();

}
