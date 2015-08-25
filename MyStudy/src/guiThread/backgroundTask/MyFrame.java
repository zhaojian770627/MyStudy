package guiThread.backgroundTask;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 演示取消耗时任务
 * 
 * @author zhaojianc
 * 
 */
public class MyFrame extends Frame {
	ExecutorService backgroundExec = Executors.newCachedThreadPool();

	int count = 0;

	public MyFrame() {
		setSize(400, 400);
		setLayout(new BorderLayout());
		JPanel hitPnl = new JPanel();
		JPanel mPnl = new JPanel();

		add(hitPnl, BorderLayout.NORTH);
		add(mPnl, BorderLayout.CENTER);

		final JLabel lhit = new JLabel("提示");
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

		final JButton btnSubmit = new JButton("提交任务");
		final JButton btnCancel = new JButton("取消任务");

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setEnabled(false);
				lhit.setText("busy");

				class CancelListener implements ActionListener {
					BackgroundTask<?> task;

					@Override
					public void actionPerformed(ActionEvent event) {
						if (task != null)
							task.cancel(true);
					}
				}
				final CancelListener listener = new CancelListener();
				listener.task = new BackgroundTask<Void>() {

					@Override
					protected void onProgress(int current, int max) {
						System.out.println(current);
					}

					@Override
					protected void onCompletion(Void result,
							Throwable exception, boolean cancelled) {
						btnCancel.removeActionListener(listener);
						lhit.setText("done");
					}

					@Override
					protected Void compute() throws Exception {
						while (moreWork() && !isCancelled()) {
							doBigComputation();
							setProgress(count, 1);
						}
						return null;
					}
				};
				btnCancel.addActionListener(listener);
				backgroundExec.execute(listener.task);
			}
		});

		mPnl.add(btnSubmit);
		mPnl.add(btnCancel);
	}

	/*
	 * 清理工作
	 */
	private void cleanUpPartialWork() {

	}

	/*
	 * 判断是否有更多的任务
	 */
	private boolean moreWork() {
		if (count > 100000)
			return false;
		else
			return true;
	}

	private void doBigComputation() {
		count++;
	}
}
