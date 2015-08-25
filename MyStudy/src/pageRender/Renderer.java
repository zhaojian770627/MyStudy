package pageRender;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Renderer {
	private static final long IME_BUDGET = 2000;
	private static final TimeUnit NANOSECONDS = TimeUnit.SECONDS;
	private static final Ad DEFAULT_AD = null;
	private final ExecutorService executor;
	
	Renderer(ExecutorService executor){
		this.executor=executor;
	}
	
	void renderPage(CharSequeence source) throws Exception{
		final List<ImageInfo> info=scanForImageInfo(source);
		CompletionService<ImageData> completionService=new ExecutorCompletionService<ImageData>(executor);
		for(final ImageInfo imageInfo:info)
			completionService.submit(new Callable<ImageData>() {
				
				@Override
				public ImageData call() throws Exception {
					return imageInfo.downloadImage();
				}
			});
		renderText(source);
		try{
			for(int t=0,n=info.size();t<n;t++){
				Future<ImageData> f=completionService.take();
				ImageData imageData=f.get();
				renderImage(imageData);
			}
		}catch(InterruptedException e){
			Thread.currentThread().interrupt();
		}catch(ExecutionException e){
			throw launderThrowable(e.getCause());
		}
	}

	/*
	 * 演示了具有限时功能的任务，任务执行超过一定时限后，要么取消
	 * 要么用指定内容进行替换
	 */
	Page renderPageWithAd() throws InterruptedException {
		long endNanos=System.nanoTime()+IME_BUDGET;
		Future<Ad> f=executor.submit(new FetchAdTask());
		// 等待广告呈现界面
		Page page=renderPageBody();
		Ad ad;
		try{
			long timeLeft=endNanos-System.nanoTime();
			ad=f.get(timeLeft, NANOSECONDS);			
		}catch(ExecutionException e){
			ad=DEFAULT_AD;
		}catch(TimeoutException e)
		{
			ad=DEFAULT_AD;
			f.cancel(true);
		}
		page.setAd(ad);
		return page;
	}
	private Page renderPageBody() {
		// TODO Auto-generated method stub
		return null;
	}

	private Exception launderThrowable(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	private void renderImage(ImageData imageData) {
		// TODO Auto-generated method stub
		
	}

	private void renderText(CharSequeence source) {
		// TODO Auto-generated method stub
		
	}

	private List<ImageInfo> scanForImageInfo(CharSequeence source) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static RuntimeException launderThrowable1(Throwable t) {
		if (t instanceof RuntimeException)
			return (RuntimeException) t;
		else if (t instanceof Error)
			throw (Error) t;
		else
			throw new IllegalStateException("Not unchecked", t);
	}
}
