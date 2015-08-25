package objectlock;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

public class CommandThread implements Runnable, ActionListener {
	private boolean flag = true;
	private ClockDisplay clockDisplay = null;
	private JLabel label = null;
	private Thread t = null;
	private boolean hasStart = false;
	private boolean start = false;

	public CommandThread(ClockDisplay display, JLabel label) {
		this.clockDisplay = display;
		this.label = label;
		t = new Thread(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		command(e.getActionCommand());
	}

	@Override
	public void run() {
		while (flag) {
			try {
				start();
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private void start() {
		synchronized (clockDisplay) {
			if (start) {
				label.setText(clockDisplay.refresh());
			} else {
				try {
					System.out.println("µÈ´ýÖÐ");
					clockDisplay.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void close() {
		this.flag = false;
	}

	private void command(String type) {
		synchronized (clockDisplay) {
			if (type.equalsIgnoreCase("start"))// Æô¶¯
			{
				start = true;
				if (!hasStart) {
					t = new Thread(this);
					hasStart = true;
					t.start();
				}
				clockDisplay.notify();
			} else if (type.equalsIgnoreCase("clear")) // ÔÝÍ£
			{
				start = false;
				clockDisplay.clear();
				label.setText(clockDisplay.toString());
			} else {
				start = false;
			}
		}
	}
}