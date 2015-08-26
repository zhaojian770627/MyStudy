package CodeDesigner.print;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PrintUI extends JPanel implements ActionListener {
	PrintUIComponent pc=new PrintUIComponent();
	public PrintUI()
	{
		JButton printButton = new JButton("Print This TextArea");
		printButton.setName("printButton");
		printButton.addActionListener(this);
		JButton printPreviewButton = new JButton("Print Preview");
		printPreviewButton.setName("printPreviewButton");
		printPreviewButton.addActionListener(this);
		JPanel buttonGroup = new JPanel(new GridLayout(2, 1));
		buttonGroup.add(printButton);
		buttonGroup.add(printPreviewButton);
		add(buttonGroup, BorderLayout.SOUTH);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object actionEventSource = e.getSource();
		if (actionEventSource instanceof JButton) {
			JButton button = (JButton) actionEventSource;
			if (button.getName().equals("printButton")) {
				pc.setPageBreaks(null);// reset pagination
				boolean ok = pc.getJob().printDialog();
				if (ok) {
					try {
						pc.getJob().print();
					} catch (PrinterException ex) {
						/* The job did not successfully complete */
						ex.printStackTrace();
					}
				}
			} else if (button.getName().equals("printPreviewButton")) {
				pc.setPageBreaks(null);// reset pagination
				pc.createAndShowPreviewDialog();
			}
		}
	}
}
