package nc;

import java.util.List;
import java.util.Map;

/**
 * 远程任务的处理接口
 * 
 * @author zhaojianc
 * 
 */
public interface ITaskManagerProxy {
	
	/**
	 * 获取待处理任务
	 * @return
	 */
	List<TaskInfo> getPendingTask();

	/**
	 * 获取处理中任务
	 * @return
	 */
	Map<String,TaskInfo> getHandingTask();

	/**
	 * 成功处理的任务
	 * @return
	 */
	Map<String,TaskInfo> getSucessedTask();

	/**
	 * 处理失败的任务
	 * @return
	 */
	Map<String,TaskInfo> getFailedTask();
	
	/**
	 * 重新执行失败的任务
	 * @param taskID
	 */
	void processFailedTask(String taskID);
}
