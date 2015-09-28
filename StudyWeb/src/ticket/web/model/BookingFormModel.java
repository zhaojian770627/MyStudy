
package ticket.web.model;

import ticket.business.referencedata.Performance;
import ticket.command.PriceBandWithAvailability;


/**
 * Immutable object containing data for the seat booking form
 */
public class BookingFormModel {

	private final Performance p;
	
	private final PriceBandWithAvailability pba;
	
	private final int dropdownValue  =0;
	
	private final int maxDropdownValue;
	
	public BookingFormModel(Performance p, PriceBandWithAvailability pba, int maxDropdownValue) {
		this.p = p;
		this.pba = pba;
		this.maxDropdownValue = maxDropdownValue;
	}
	
	
	public Performance getPerformance() {
		return p;
	}
	
	public PriceBandWithAvailability getSeatTypeInfo() {
		return pba;
	}
	
	public int getMaxDropdownValue() {
		return this.maxDropdownValue;
	}

}
