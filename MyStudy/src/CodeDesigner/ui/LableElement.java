package CodeDesigner.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import CodeDesigner.ui.graph.RectSelectBorder;
import CodeDesigner.ui.manager.DragDirection;
import CodeDesigner.ui.model.DataType;
import CodeDesigner.ui.model.IDataItem;
import CodeDesigner.ui.model.IFormat;

public class LableElement implements IElementPaint {
	IDataItem data;
	Dimension size=new Dimension();
	Point pos=new Point();
	IFormat format;
	boolean focus = false;
	RectSelectBorder selBorder = new RectSelectBorder(this);

	public LableElement() {

	}

	@Override
	public void setFormat(IFormat format) {
		this.format = format;
	}

	@Override
	public void setValue(IDataItem data) throws Exception {
		if (!data.getDataType().equals(DataType.String))
			throw new Exception("数据类型不匹配");
		this.data = data;
	}

	@Override
	public void paint(Graphics g) {
		drawBorder(g);
		drawTitle(g);
	}

	private void drawTitle(Graphics g) {
		g.setColor(Color.BLUE);
		String lbltile = "标题";
		/* 计算字体长度 */
		FontMetrics metrics = g.getFontMetrics(format.getFont());
		int fontWidth = metrics.stringWidth(lbltile);
		int fontHeight = metrics.getHeight();

		int fontX = (size.width - fontWidth) / 2;
		int fontY = (size.height - fontHeight) / 2;

		if (fontX < 0)
			fontX = 0;
		fontX = pos.x + fontX;

		if (fontY < 0)
			fontY = 0;
		fontY = pos.y + size.height - fontY - 4;
		g.setColor(Color.BLACK);
		g.drawString(lbltile, fontX, fontY);
	}

	private void drawBorder(Graphics g) {
		if (focus)
			selBorder.paint(g);
		else {
			g.setColor(Color.BLACK);
			g.drawRect(pos.x, pos.y, size.width, size.height);
		}
	}

	@Override
	public void setSize(Dimension size) {
		this.size = new Dimension(size);
		selBorder.ajustRect();
	}

	@Override
	public Dimension getSize() {
		return new Dimension(size);
	}

	@Override
	public void setLocation(Point pt) {
		pos = new Point(pt);
		selBorder.ajustRect();
	}

	@Override
	public Point getLocation() {
		return new Point(pos);
	}

	@Override
	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	@Override
	public boolean contrainXY(int x, int y) {
		Rectangle rect = new Rectangle(pos, size);
		return rect.contains(x, y);
	}

	public DragDirection inAdjustArea(int x, int y) {
		return selBorder.inAdjustArea(x, y);
	}
}
