package timer;

import java.util.TimerTask;

public class ThrowTask extends TimerTask {

	@Override
	public void run() {
		throw new RuntimeException();
	}

}
