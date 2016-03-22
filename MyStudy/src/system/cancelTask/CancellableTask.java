package system.cancelTask;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * 用newTaskFor封装任务中非标准取消
 * @author zhaojianc
 *
 * @param <T>
 */
public interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}
