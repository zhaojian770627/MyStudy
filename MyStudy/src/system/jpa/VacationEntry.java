package system.jpa;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class VacationEntry {
	public VacationEntry() {

	}

	public VacationEntry(Calendar startDate, int daysTaken) {
		super();
		this.startDate = startDate;
		this.daysTaken = daysTaken;
	}

	@Temporal(TemporalType.DATE)
	private Calendar startDate;

	@Column(name = "DAYS")
	private int daysTaken;

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public int getDaysTaken() {
		return daysTaken;
	}

	public void setDaysTaken(int daysTaken) {
		this.daysTaken = daysTaken;
	}
}
