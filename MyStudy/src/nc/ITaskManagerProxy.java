package nc;

import java.util.List;
import java.util.Map;

/**
 * Զ������Ĵ���ӿ�
 * 
 * @author zhaojianc
 * 
 */
public interface ITaskManagerProxy {
	
	/**
	 * ��ȡ����������
	 * @return
	 */
	List<TaskInfo> getPendingTask();

	/**
	 * ��ȡ����������
	 * @return
	 */
	Map<String,TaskInfo> getHandingTask();

	/**
	 * �ɹ����������
	 * @return
	 */
	Map<String,TaskInfo> getSucessedTask();

	/**
	 * ����ʧ�ܵ�����
	 * @return
	 */
	Map<String,TaskInfo> getFailedTask();
	
	/**
	 * ����ִ��ʧ�ܵ�����
	 * @param taskID
	 */
	void processFailedTask(String taskID);
}
