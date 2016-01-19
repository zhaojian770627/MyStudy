package nc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;

/**
 * 提供统一的接口访问队列 先暂时使用Java的Queue实现，后期考虑使用 持久化的机制实现 或者 消息平台 <br/>
 * <br/>
 * <b>需要考虑多线程调用</b>
 * 
 * @author zhaojianc
 * 
 */
public class DefaultTaskQueueProcessor implements ITaskQueueProcessor {

	/*
	 * 待处理队列
	 */
	private Queue<TaskInfo> pendingQueue = new ConcurrentLinkedQueue<TaskInfo>();

	/*
	 * 处理中Map
	 */
	private ConcurrentMap<String, TaskInfo> handingMap = new ConcurrentHashMap<String, TaskInfo>();

	/*
	 * 任务成功Map
	 */
	private ConcurrentMap<String, TaskInfo> sucessedMap = new ConcurrentHashMap<String, TaskInfo>();

	/*
	 * 任务失败Map
	 */
	private ConcurrentMap<String, TaskInfo> failedMap = new ConcurrentHashMap<String, TaskInfo>();

	/*
	 * 单例模式，解决并发问题
	 */
	private static class InnerInstance {
		public static DefaultTaskQueueProcessor instance = new DefaultTaskQueueProcessor();
	}

	public static DefaultTaskQueueProcessor getInstance() {
		return InnerInstance.instance;
	}

	@Override
	public void addTask(TaskInfo taskInfo) {
		pendingQueue.offer(taskInfo);
	}

	@Override
	public TaskInfo getTask() {
		TaskInfo taskInfo = pendingQueue.peek();
		if (taskInfo == null)
			return null;
		handingMap.putIfAbsent(taskInfo.taskID, taskInfo);
		return pendingQueue.poll();
	}

	@Override
	public TaskInfo getFailedTask(String id) {
		return failedMap.get(id);
	}

	@Override
	public void processSucessTask(TaskInfo taskInfo) {
		sucessedMap.putIfAbsent(taskInfo.taskID, taskInfo);
		handingMap.remove(taskInfo.taskID);
	}

	@Override
	public void processFailedTask(TaskInfo taskInfo) {
		failedMap.putIfAbsent(taskInfo.taskID, taskInfo);
		handingMap.remove(taskInfo.taskID);
	}

	@Override
	public List<TaskInfo> getPendingTask() {
		List<TaskInfo> lstTasks = new ArrayList<TaskInfo>();
		lstTasks.addAll(pendingQueue);
		return lstTasks;
	}

	@Override
	public Map<String, TaskInfo> getHandingTask() {
		Map<String, TaskInfo> mapTasks = new HashMap<String, TaskInfo>();
		mapTasks.putAll(handingMap);
		return mapTasks;
	}

	@Override
	public Map<String, TaskInfo> getSucessedTask() {
		Map<String, TaskInfo> mapTasks = new HashMap<String, TaskInfo>();
		mapTasks.putAll(sucessedMap);
		return mapTasks;
	}

	@Override
	public Map<String, TaskInfo> getFailedTask() {
		Map<String, TaskInfo> mapTasks = new HashMap<String, TaskInfo>();
		mapTasks.putAll(failedMap);
		return mapTasks;
	}

	@Override
	public void executeFailedTask(String taskID) {
		// 从失败队列中取出任务
		TaskInfo ti=failedMap.get(taskID);
		if(ti==null)
			return;
		// 生成新的任务号
		ti.setTaskID(UUID.randomUUID().toString());
		addTask(ti);
		failedMap.remove(taskID);
	}
}
