package nc;

import java.util.List;
import java.util.Map;

/**
 * 进行任务消息的抽象
 * 
 * @author zhaojianc
 * 
 */
public interface ITaskQueueProcessor {

	/**
	 * 添加待处理的任务
	 * 
	 * @param taskInfo
	 */
	void addTask(TaskInfo taskInfo);

	/**
	 * 得到待处理的任务
	 * 
	 * @return
	 */
	TaskInfo getTask();

	/**
	 * 从失败队列中取消息
	 * 
	 * @return
	 */
	TaskInfo getFailedTask(String id);

	/**
	 * 处理成功的任务，主要是添加到处理成功的队列
	 * 
	 * @param taskInfo
	 */
	void processSucessTask(TaskInfo taskInfo);

	/**
	 * 处理失败的任务，主要是添加到处理失败的队列 需要提供机制，有人工从失败队列中取任务，进行人工处理
	 * 
	 * @param taskInfo
	 */
	void processFailedTask(TaskInfo taskInfo);

	/**
	 * 得到待处理的任务的快照
	 * @return
	 */
	List<TaskInfo> getPendingTask();
	
	/**
	 * 得到处理中的任务快照
	 * @return
	 */
	Map<String, TaskInfo> getHandingTask();
	
	/**
	 * 得到处理成功的任务快照
	 * @return
	 */
	Map<String, TaskInfo> getSucessedTask();
	
	/**
	 * 得到处理失败的任务快照
	 * @return
	 */
	Map<String, TaskInfo> getFailedTask();
	
	/**
	 * 重新执行失败的任务
	 * @param taskID
	 */
	void executeFailedTask(String taskID);
}
