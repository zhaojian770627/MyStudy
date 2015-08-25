package algorithm.sort.base;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import algorithm.sort.SortTool;

/**
 * 演示查看下一步的等待任务
 * 
 * @author zhaojianc
 * 
 */
public class MyFrame extends Frame {
	ExecutorService backgroundExec = Executors.newCachedThreadPool();
	// 模拟模型数据
	int model;

	Integer[] source = { 81, 94, 11, 96, 12, 35, 17, 95, 28, 58, 41, 75, 15 };
	Integer[] target = {};

	int count;

	volatile boolean pause = true;
	final JTextArea txtModel;

	public MyFrame() {
		setSize(400, 400);
		setLayout(new BorderLayout());
		JPanel hitPnl = new JPanel();
		JPanel mPnl = new JPanel();

		add(mPnl, BorderLayout.NORTH);
		add(hitPnl, BorderLayout.CENTER);

		txtModel = new JTextArea("提示");
		hitPnl.setLayout(new BorderLayout());
		hitPnl.add(txtModel, BorderLayout.CENTER);

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
		final JButton btnNext = new JButton("下一步");
		final JButton btnCancel = new JButton("取消任务");

		btnSubmit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnSubmit.setEnabled(false);
				txtModel.setText("busy");

				target = source.clone();
				showTarget();

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
						// System.out.println(current);
						showTarget();
					}

					@Override
					protected void onCompletion(Void result,
							Throwable exception, boolean cancelled) {
						btnCancel.removeActionListener(listener);
						showTarget();
						// txtModel.setText("done");
						btnSubmit.setEnabled(true);
					}

					@Override
					protected Void compute() throws Exception {
						IWaitNext waitNext = new IWaitNext() {

							@Override
							public void waitNext() throws InterruptedException {
								pause();

							}

							@Override
							public void continueNext() {
								goOn();
							}

							@Override
							public void notify(int i, int j) {
								setProgress(count, 1);

							}
						};
						// 插入排序
						// SortTool.insertionSort(target, waitNext);
						// 希尔排序
						// SortTool.shellsort(target, waitNext);
						// 堆排序
						// SortTool.heapsort(target, waitNext);
						// 归并排序
						SortTool.mergeSort(target, waitNext);

						return null;
					}

					void pause() throws InterruptedException {
						while (pause)
							Thread.sleep(200);
					}

					void goOn() {
						pause = true;
					}
				};
				btnCancel.addActionListener(listener);
				backgroundExec.execute(listener.task);
			}
		});

		btnNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pause = false;
			}
		});

		mPnl.add(btnSubmit);
		mPnl.add(btnNext);
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

	private void showTarget() {
		String s = "";
		for (int i : target) {
			s = s + " " + String.valueOf(i);
		}
		txtModel.setText(s);
	}
}