package CodeDesigner.ui.model;

import java.awt.Font;

/**
 * 格式设定
 * @author zhaojianc
 *
 */
public class Format implements IFormat {
	Font font;

	@Override
	public void setFont(Font font) {
		this.font = font;
	}

	@Override
	public Font getFont() {
		return font;
	}

}
