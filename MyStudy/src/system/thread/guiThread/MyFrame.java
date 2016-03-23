package system.thread.guiThread;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 演示提供用户反馈的耗时任务
 * @author zhaojianc
 *
 */
public class MyFrame extends Frame {
	ExecutorService backgroundExec = Executors.newCachedThreadPool();

	public MyFrame() {
		setSize(400, 400);
		setLayout(new BorderLayout());
		JPanel hitPnl=new JPanel();
		JPanel mPnl=new JPanel();
		
		add(hitPnl,BorderLayout.NORTH);
		add(mPnl,BorderLayout.CENTER);
		
		final JLabel lhit=new JLabel("提示");
		hitPnl.add(lhit);
		
		
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				setVisible(false);
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		final JButton btn = new JButton("Click");
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btn.setEnabled(false);
				lhit.setText("busy");
				backgroundExec.execute(new Runnable() {

					@Override
					public void run() {
						try{
						doBigComputation();
						}finally{
							GuiExecutor.instance().execute(new Runnable() {
								
								@Override
								public void run() {
									btn.setEnabled(true);
									lhit.setText("idle");
								}
							});
						}
					}

				});
			}
		});
		mPnl.add(btn);
	}

	private void doBigComputation() {
		try {
			Thread.sleep(5000);
			//System.out.println("Click");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
