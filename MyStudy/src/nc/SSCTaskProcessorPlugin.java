package nc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ����ĺ�̨������ �趨Ϊͬһʱ��ֻ��һ��������ִ�У�����һ��������Դ���������Ե�ǰ����������е�����Ȼ���˳�
 * 
 * ����ʱ����ֵ���´�������������һ����Ϊ��ֹ�к�ʱ���񣬴Ӷ�����ȡ�����
 * 
 * @author zhaojianc
 * 
 */
public class SSCTaskProcessorPlugin {

	ExecutorService service=Executors.newCachedThreadPool();


	private void processTask(TaskInfo taskInfo) {
		// �ȼ���ʧ�ܶ��У���ֹ�߳�����ʧ�ܣ������Ϣ�޷�����
		TaskExecutor taskExecutor = new TaskExecutor(taskInfo);
		DefaultTaskQueueProcessor.getInstance().processFailedTask(taskInfo);
		service.execute(taskExecutor);
	}
}
