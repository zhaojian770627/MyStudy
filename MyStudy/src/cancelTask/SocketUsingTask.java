package cancelTask;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

/**
 * 用newTaskFor封装任务中非标准取消
 * 
 * @author zhaojianc
 * 
 * @param <T>
 */
public abstract class SocketUsingTask<T> implements CancellableTask<T> {
	private Socket socket;

	protected synchronized void setSocket(Socket s) {
		socket = s;
	}

	@Override
	public T call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public synchronized void cancel() {
		try {
			if (socket != null)
				socket.close();
		} catch (IOException ignored) {

		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<T>(this) {

			@Override
			public boolean cancel(boolean mayInterruptIfRuning) {
				try {
					SocketUsingTask.this.cancel();
				}
				finally 
				{
					return super.cancel(mayInterruptIfRuning);
				}
			}

		};
	}

}
