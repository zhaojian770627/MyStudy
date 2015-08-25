package futureTaskTest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ʹ��FutureTaskԤ���Ժ���Ҫ������
 * @author zhaojianc
 *
 */
public class Preloader {
	private final FutureTask<ProductInfo> future=new FutureTask<ProductInfo>(new Callable<ProductInfo>(){
		@Override
		public ProductInfo call() throws Exception {
			return loadProductInfo();
		}		
	});
	
	private final Thread thread=new Thread(future);
	public void start()
	{
		thread.start();
	}
	
	public ProductInfo get() throws InterruptedException, ExecutionException{
		return future.get();
	}
	
	private ProductInfo loadProductInfo() {
		ProductInfo info=new ProductInfo("ƿ��",100);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return info;		
	}		
}
