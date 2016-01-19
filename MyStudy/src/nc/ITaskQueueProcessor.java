package nc;

import java.util.List;
import java.util.Map;

/**
 * ����������Ϣ�ĳ���
 * 
 * @author zhaojianc
 * 
 */
public interface ITaskQueueProcessor {

	/**
	 * ��Ӵ����������
	 * 
	 * @param taskInfo
	 */
	void addTask(TaskInfo taskInfo);

	/**
	 * �õ������������
	 * 
	 * @return
	 */
	TaskInfo getTask();

	/**
	 * ��ʧ�ܶ�����ȡ��Ϣ
	 * 
	 * @return
	 */
	TaskInfo getFailedTask(String id);

	/**
	 * ����ɹ���������Ҫ����ӵ�����ɹ��Ķ���
	 * 
	 * @param taskInfo
	 */
	void processSucessTask(TaskInfo taskInfo);

	/**
	 * ����ʧ�ܵ�������Ҫ����ӵ�����ʧ�ܵĶ��� ��Ҫ�ṩ���ƣ����˹���ʧ�ܶ�����ȡ���񣬽����˹�����
	 * 
	 * @param taskInfo
	 */
	void processFailedTask(TaskInfo taskInfo);

	/**
	 * �õ������������Ŀ���
	 * @return
	 */
	List<TaskInfo> getPendingTask();
	
	/**
	 * �õ������е��������
	 * @return
	 */
	Map<String, TaskInfo> getHandingTask();
	
	/**
	 * �õ�����ɹ����������
	 * @return
	 */
	Map<String, TaskInfo> getSucessedTask();
	
	/**
	 * �õ�����ʧ�ܵ��������
	 * @return
	 */
	Map<String, TaskInfo> getFailedTask();
	
	/**
	 * ����ִ��ʧ�ܵ�����
	 * @param taskID
	 */
	void executeFailedTask(String taskID);
}
