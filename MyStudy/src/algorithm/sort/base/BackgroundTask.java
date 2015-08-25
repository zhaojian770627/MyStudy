package algorithm.sort.base;

import guiThread.GuiExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class BackgroundTask<V> implements Runnable, Future<V> {
	private final FutureTask<V> computation=new Computation();
	
	private class Computation extends FutureTask<V>{		
		public Computation(){
			super(new Callable<V>(){
				public V call() throws Exception{
					return BackgroundTask.this.compute();
				}
			});
		}
		
		@Override
		protected final void done() {
			GuiExecutor.instance().execute(new Runnable() {
				
				@Override
				public void run() {
					V value=null;
					Throwable thrown=null;
					boolean cancelled=false;
					try{
						value=get();
					}catch(ExecutionException e){
						thrown=e.getCause();
					}catch(CancellationException e){
						cancelled=false;
					}catch(InterruptedException consumed){
						
					}finally{
						onCompletion(value,thrown,cancelled);
					}
				}
			});
		}
	}
	
	protected void setProgress(final int current,final int max){
		GuiExecutor.instance().execute(new Runnable() {
			
			@Override
			public void run() {				
				onProgress(current,max);
			}
		});
	}
	
	// 在后台线程中调用
	protected abstract V compute() throws Exception;
	
	// 在事件线程中调用
	protected void onCompletion(V result,Throwable exception,boolean cancelled){}
	
	protected void onProgress(int current,int max){};
	
	// 其他用于完成计算的方法
	@Override
	public boolean cancel(boolean paramBoolean) {
		return computation.cancel(paramBoolean);
	}

	@Override
	public boolean isCancelled() {
		return computation.isCancelled();
	}

	@Override
	public boolean isDone() {
		return computation.isDone();
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return computation.get();
	}

	@Override
	public V get(long paramLong, TimeUnit paramTimeUnit)
			throws InterruptedException, ExecutionException, TimeoutException {
		return computation.get(paramLong, paramTimeUnit);
	}

	@Override
	public void run() {
		computation.run();		
	}
}
