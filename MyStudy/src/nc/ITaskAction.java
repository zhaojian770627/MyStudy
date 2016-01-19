package nc;

import java.util.Map;

/**
 * ��������������
 * @author zhaojianc
 *
 */
public interface ITaskAction {
	/**
	 * ��������
	 * @param taskInfo
	 * @throws Exception
	 */
	Object doAction(TaskInfo taskInfo) throws Exception;
	
	/**
	 * �Դ��ݼ��������ݽ��а�װ����װ����������൱��Э��ת��
	 * @param objAttr
	 * @return
	 * @throws Exception
	 */
	TaskInfo wrap(Map<String,Object> objAttr) throws Exception;
}
