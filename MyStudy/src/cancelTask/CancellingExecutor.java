package cancelTask;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 用newTaskFor封装任务中非标准取消
 * @author zhaojianc
 *
 */
public class CancellingExecutor extends ThreadPoolExecutor {

	@Override
	protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
		if(callable instanceof CancellableTask)
			return ((CancellableTask<T>)callable).newTask();
		else
			return super.newTaskFor(callable);
	}

	public CancellingExecutor(int paramInt1, int paramInt2, long paramLong,
			TimeUnit paramTimeUnit, BlockingQueue<Runnable> paramBlockingQueue) {
		super(paramInt1, paramInt2, paramLong, paramTimeUnit, paramBlockingQueue);
	}
	
	
}
