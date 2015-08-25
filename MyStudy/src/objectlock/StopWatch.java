package objectlock;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StopWatch extends JFrame {
	private static final long serialVersionUID = -5212710477644044656L;
	private final JLabel label = new JLabel("00:00:000");
	private JButton button1 = new JButton("Start");
	private JButton button2 = new JButton("Suspend");
	private JButton button3 = new JButton("Clear");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();

	private CommandThread command = null;
	private final ClockDisplay clockDisplay = new ClockDisplay();

	public StopWatch() {
		super("√Î±Ì–°≥Ã–Ú");
	}

	public void init() {
		setLayout(new BorderLayout());
		setSize(250, 120);
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER));

		panel1.add(label);
		command = new CommandThread(clockDisplay, label);
		panel2.add(button1);
		panel2.add(button2);
		panel2.add(button3);

		button1.addActionListener(command);
		button2.addActionListener(command);
		button3.addActionListener(command);
		add(BorderLayout.NORTH, panel1);
		add(BorderLayout.CENTER, panel2);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) 
	  { 
	    new StopWatch().init(); 
	  }
}
