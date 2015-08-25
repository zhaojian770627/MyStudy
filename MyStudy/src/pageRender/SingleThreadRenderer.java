package pageRender;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadRenderer {
	void renderPage(CharSequeence source){
		renderText(source);
		List<ImageData> imageData=new ArrayList<ImageData>();
		for(ImageInfo imageInfo:scanForImageInfo(source))
			imageData.add(imageInfo.downloadImage());
		
		for(ImageData data:imageData)
			renderImage(data);
	}

	private void renderImage(ImageData data) {
		
	}

	private List<ImageInfo> scanForImageInfo(CharSequeence source) {
		return null;
	}

	private void renderText(CharSequeence source) {
		
	}
}
