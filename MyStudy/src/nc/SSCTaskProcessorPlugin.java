package nc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务的后台处理类 设定为同一时刻只有一个任务在执行，启动一个任务可以处理完毕所以当前待处理队列中的任务，然后退出
 * 
 * 根据时间阈值，下次启动处理另外一批，为防止有耗时任务，从队列中取任务后
 * 
 * @author zhaojianc
 * 
 */
public class SSCTaskProcessorPlugin {

	ExecutorService service=Executors.newCachedThreadPool();


	private void processTask(TaskInfo taskInfo) {
		// 先加入失败队列，防止线程启动失败，造成消息无法处理
		TaskExecutor taskExecutor = new TaskExecutor(taskInfo);
		DefaultTaskQueueProcessor.getInstance().processFailedTask(taskInfo);
		service.execute(taskExecutor);
	}
}
