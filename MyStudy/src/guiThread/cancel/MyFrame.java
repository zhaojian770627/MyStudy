package guiThread.cancel;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 演示取消耗时任务
 * @author zhaojianc
 *
 */
public class MyFrame extends Frame {
	ExecutorService backgroundExec = Executors.newCachedThreadPool();
	Future<?> runningTask=null;	//线程限制的
	long count=0;
	
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

		final JButton btnSubmit = new JButton("提交任务");
		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setEnabled(false);
				lhit.setText("busy");
				runningTask=backgroundExec.submit(new Runnable() {

					@Override
					public void run() {
						try{
							while(moreWork()){
								if(Thread.interrupted()){
									cleanUpPartialWork();
									break;
								}
								doBigComputation();
							}
						
						}
						finally{
							GuiExecutor.instance().execute(new Runnable() {
								
								@Override
								public void run() {
									btnSubmit.setEnabled(true);
									lhit.setText("idle");
								}
							});
						}
					}
				});
			}
		});
		
		final JButton btnCancel=new JButton("取消任务");
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent paramActionEvent) {
				if(runningTask!=null)
					runningTask.cancel(true);
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
		return true;
	}
	private void doBigComputation() {
		count++;
		System.out.println(count);		
	}
}
