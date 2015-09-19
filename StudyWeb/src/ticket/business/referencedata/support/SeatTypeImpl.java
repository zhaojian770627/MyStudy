
package ticket.business.referencedata.support;

import ticket.business.referencedata.SeatType;

/**
 * SCO: no id. Must be returned by a performance.
 */
public class SeatTypeImpl extends AbstractReferenceItem implements SeatType  {
	
	private String description;

	/**
	 * Constructor for PriceBand.
	 */
	public SeatTypeImpl(int classId, String name, String description) {
		super(classId, name);
		this.description = description;
	}
	
	public int getSeatTypeId() {
		return getId();
	}  

	/**
	 * @see SeatType#getDescription()
	 */
	public String getDescription() {
		return this.description;
	}

}
