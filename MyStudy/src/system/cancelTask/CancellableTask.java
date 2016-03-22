package system.cancelTask;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * ��newTaskFor��װ�����зǱ�׼ȡ��
 * @author zhaojianc
 *
 * @param <T>
 */
public interface CancellableTask<T> extends Callable<T> {
	void cancel();
	RunnableFuture<T> newTask();
}
