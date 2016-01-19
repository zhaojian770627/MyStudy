package nc;

import java.io.Serializable;

/**
 * ������Ϣ����
 * 
 * @author zhaojianc
 * 
 */
public class TaskInfo implements Serializable {

	/**
	 * NC��������
	 */
	public static final String TASKTYPE_NCWF = "TASKTYPE_NCWF";

	/**
	 * ��������
	 */
	public static final String TASKOPER_NCWF_START = "OPER_START";

	/*
	 * �����Ψһ��ʾ
	 */
	String taskID;

	/**
	 * �����̺߳�
	 */
	long threadID;

	/*
	 * ��������ͣ����ǹ��������͡��������� ������Ҫ���ݸ������͹���һ������Ĵ�����
	 */
	String taskType;

	/*
	 * ���������µĲ������� ���������Ͱ������������������������� ���ݣ� ���浥��
	 * 
	 * ����Ĳ����д�����д���
	 */
	String taskOperCode;

	/*
	 * �������͵ľ��崦���࣬���ڿ��Բ�������������Ϣ�� ����ע����ĳ���ط�
	 */
	String taskProcessor;

	/*
	 * �����״̬ �� ������ ������ ����ʧ��
	 * 
	 * ����ʧ�ܵ�������Ҫ��ӻ��ƣ����˹��������ҵ�񲹳�
	 */
	String taskStatus;

	/*
	 * ��������
	 */
	String taskDescript;

	/*
	 * ����������������
	 */
	transient Object taskData;

	/*
	 * ����������ϸ�������ɹ����Բ���ȷ��Ϣ��ʧ����Ҫ����ʧ����Ϣ
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
