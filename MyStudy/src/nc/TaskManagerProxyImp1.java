package nc;

import java.util.List;
import java.util.Map;

public class TaskManagerProxyImp1 implements ITaskManagerProxy {

	@Override
	public List<TaskInfo> getPendingTask() {
		return DefaultTaskQueueProcessor.getInstance().getPendingTask();
	}

	@Override
	public Map<String,TaskInfo> getHandingTask() {
		return DefaultTaskQueueProcessor.getInstance().getHandingTask();
	}

	@Override
	public Map<String,TaskInfo> getSucessedTask() {
		return DefaultTaskQueueProcessor.getInstance().getSucessedTask();
	}

	@Override
	public Map<String,TaskInfo> getFailedTask() {
		return DefaultTaskQueueProcessor.getInstance().getFailedTask();
	}

	@Override
	public void processFailedTask(String taskID) {
		DefaultTaskQueueProcessor.getInstance().executeFailedTask(taskID);
	}

}
