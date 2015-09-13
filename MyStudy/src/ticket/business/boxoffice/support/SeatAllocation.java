
package ticket.business.boxoffice.support;

import java.io.Serializable;

import ticket.business.boxoffice.Seat;

public final class SeatAllocation implements Serializable {
	
	private final Seat[] seats;
	
	private final boolean adjacent;

	/**
	 * Constructor for SeatAllocation.
	 */
	public SeatAllocation(Seat[] pseats, boolean adjacent) {
		seats = new Seat[pseats.length];
		// Deep copy: immutable
		System.arraycopy(pseats, 0, seats, 0, pseats.length);
		this.adjacent = adjacent;
	}
	
	public Seat[] getSeats() {
		return seats;
	}
	
	public boolean adjacent() {
		return adjacent;
	}

}
