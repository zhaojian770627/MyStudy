package futureTaskTest;

import java.util.concurrent.ExecutionException;

public class TestLoader {

	/**
	 * @param args
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Preloader loader=new Preloader();
		loader.start();
		ProductInfo info=loader.get();
		System.out.println("Ãû³Æ:"+info.getName()+",ÊýÁ¿:"+info.getCount());
	}

}
