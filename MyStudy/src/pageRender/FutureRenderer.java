package pageRender;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureRenderer {
	private final ExecutorService executor = Executors.newFixedThreadPool(1000);

	void renderPage(CharSequeence source) throws Exception {
		final List<ImageInfo> imageInfos = scanForImageInfo(source);
		Callable<List<ImageData>> task = new Callable<List<ImageData>>() {

			@Override
			public List<ImageData> call() throws Exception {
				List<ImageData> result = new ArrayList<ImageData>();
				for (ImageInfo imageInfo : imageInfos)
					result.add(imageInfo.downloadImage());
				return result;
			}
		};
		Future<List<ImageData>> future=executor.submit(task);
		renderText(source);
		try{
			List<ImageData> imageData=future.get();
			for(ImageData data:imageData)
				renderImage(data);
		}catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			future.cancel(true);
		}catch(ExecutionException e){
			throw launderThrowable(e.getCause());
		}
	}

	private void renderImage(ImageData data) {
		// TODO Auto-generated method stub
		
	}

	private Exception launderThrowable(Throwable cause) {
		// TODO Auto-generated method stub
		return null;
	}

	private void renderText(CharSequeence source) {
		// TODO Auto-generated method stub
		
	}

	private List<ImageInfo> scanForImageInfo(CharSequeence source) {
		// TODO Auto-generated method stub
		return null;
	}
}
