package nc;

public class TaskExecutor implements Runnable {
	TaskInfo currentTask;

	public TaskExecutor(TaskInfo taskInfo) {
		currentTask = taskInfo;
	}

	@Override
	public void run() {
		// �õ�������
		String processClassName = currentTask.getTaskProcess();
		// ����ط��п��ܵò�������Ҫ���뵽�̵߳�Run��
		currentTask.setThreadID(Thread.currentThread().getId());
		try {
			ITaskAction taskAction = TaskUtil.getTaskProcessor(processClassName);
			taskAction.doAction(currentTask);
			// ������ɺ󣬴�ʧ�ܶ�����ɾ��������ɹ�����
			DefaultTaskQueueProcessor.getInstance().processSucessTask(currentTask);

		} catch (Exception e) {
			currentTask.setTaskResult("����ʧ�ܣ�" + e.getMessage());
			DefaultTaskQueueProcessor.getInstance().processFailedTask(currentTask);
			return;
		}
	}
}
