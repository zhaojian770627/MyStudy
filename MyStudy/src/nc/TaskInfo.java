package nc;

import java.io.Serializable;

/**
 * 任务信息定义
 * 
 * @author zhaojianc
 * 
 */
public class TaskInfo implements Serializable {

	/**
	 * NC流程类型
	 */
	public static final String TASKTYPE_NCWF = "TASKTYPE_NCWF";

	/**
	 * 启动流程
	 */
	public static final String TASKOPER_NCWF_START = "OPER_START";

	/*
	 * 任务的唯一标示
	 */
	String taskID;

	/**
	 * 处理线程号
	 */
	long threadID;

	/*
	 * 任务的类型，如是工作流类型、单据类型 可能需要根据根据类型关联一个具体的处理类
	 */
	String taskType;

	/*
	 * 任务类型下的操作，如 工作流类型包括启动工作流、驱动工作流 单据： 保存单据
	 * 
	 * 具体的操作有处理进行处理
	 */
	String taskOperCode;

	/*
	 * 任务类型的具体处理类，后期可以不包含在任务消息中 可以注册在某个地方
	 */
	String taskProcessor;

	/*
	 * 任务的状态 如 待处理 处理中 处理失败
	 * 
	 * 处理失败的任务需要添加机制，供人工参与进行业务补偿
	 */
	String taskStatus;

	/*
	 * 任务描述
	 */
	String taskDescript;

	/*
	 * 任务所关联的数据
	 */
	transient Object taskData;

	/*
	 * 处理结果的详细描述，成功可以不明确信息，失败需要填入失败信息
	 */
	String taskResult;

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskOperCode() {
		return taskOperCode;
	}

	public void setTaskOperCode(String taskOperCode) {
		this.taskOperCode = taskOperCode;
	}

	public String getTaskProcess() {
		return taskProcessor;
	}

	public void setTaskProcess(String taskProcessor) {
		this.taskProcessor = taskProcessor;
	}

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Object getTaskData() {
		return taskData;
	}

	public void setTaskData(Object taskData) {
		this.taskData = taskData;
	}

	public String getTaskResult() {
		return taskResult;
	}

	public void setTaskResult(String taskResult) {
		this.taskResult = taskResult;
	}

	public long getThreadID() {
		return threadID;
	}

	public void setThreadID(long threadID) {
		this.threadID = threadID;
	}

	public String getTaskDescript() {
		return taskDescript;
	}

	public void setTaskDescript(String taskDescript) {
		this.taskDescript = taskDescript;
	}
}
