package objectlock;

public class ClockDisplay {
	private int minute = 0;
	private int second = 0;
	private int millsecond = 0;

	public ClockDisplay() {
	}

	public void clear() {
		this.minute = 0;
		this.second = 0;
		this.millsecond = 0;
	}

	public String refresh() {
		if (millsecond >= 999) {
			second++;
			millsecond = 0;
		} else {
			millsecond++;
		}

		if (second >= 59) {
			minute++;
			second = 0;
		}

		return toString();
	}

	public String toString() {
		return String.format("%02d", minute) + ":"
				+ String.format("%02d", second) + ":"
				+ String.format("%03d", millsecond);
	}
}
