package nc;

public class TaskExecutor implements Runnable {
	TaskInfo currentTask;

	public TaskExecutor(TaskInfo taskInfo) {
		currentTask = taskInfo;
	}

	@Override
	public void run() {
		// 得到处理类
		String processClassName = currentTask.getTaskProcess();
		// 这个地方有可能得不到，需要加入到线程的Run中
		currentTask.setThreadID(Thread.currentThread().getId());
		try {
			ITaskAction taskAction = TaskUtil.getTaskProcessor(processClassName);
			taskAction.doAction(currentTask);
			// 正常完成后，从失败队列中删除，加入成功队列
			DefaultTaskQueueProcessor.getInstance().processSucessTask(currentTask);

		} catch (Exception e) {
			currentTask.setTaskResult("处理失败：" + e.getMessage());
			DefaultTaskQueueProcessor.getInstance().processFailedTask(currentTask);
			return;
		}
	}
}
