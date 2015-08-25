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
 * ��ʾ�鿴��һ���ĵȴ�����
 * 
 * @author zhaojianc
 * 
 */
public class MyFrame extends Frame {
	ExecutorService backgroundExec = Executors.newCachedThreadPool();
	// ģ��ģ������
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

		txtModel = new JTextArea("��ʾ");
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

		final JButton btnSubmit = new JButton("�ύ����");
		final JButton btnNext = new JButton("��һ��");
		final JButton btnCancel = new JButton("ȡ������");

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
						// ��������
						// SortTool.insertionSort(target, waitNext);
						// ϣ������
						// SortTool.shellsort(target, waitNext);
						// ������
						// SortTool.heapsort(target, waitNext);
						// �鲢����
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
	 * ������
	 */
	private void cleanUpPartialWork() {

	}

	/*
	 * �ж��Ƿ��и��������
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