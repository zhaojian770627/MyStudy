package CodeDesigner.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.encode.InvalidAtributeException;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WidthCodedPainter;

import CodeDesigner.ui.graph.RectSelectBorder;
import CodeDesigner.ui.manager.DragDirection;
import CodeDesigner.ui.model.IDataItem;
import CodeDesigner.ui.model.IFormat;

public class CodeElement implements IElementPaint {
	Dimension size=new Dimension();
	Point pos=new Point();
	boolean focus = false;
	RectSelectBorder selBorder = new RectSelectBorder(this);
	
	@Override
	public void setFormat(IFormat format) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setValue(IDataItem data) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(),
				WidthCodedPainter.getInstance(),
				EAN13TextPainter.getInstance());
		// 生成. 欧洲商品条码(=European Article Number)
		// 这里我们用作图书条码
		localJBarcode.setShowText(false);
		String str = "788515004012";
		try {
			BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
			g.drawImage(localBufferedImage, pos.x, pos.y, size.width, size.height, null);
		} catch (InvalidAtributeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	@Override
	public DragDirection inAdjustArea(int x, int y) {
		return selBorder.inAdjustArea(x, y);
	}

	private void drawBorder(Graphics g) {
		if (focus)
			selBorder.paint(g);
		else {
			g.setColor(Color.BLACK);
			g.drawRect(pos.x, pos.y, size.width, size.height);
		}
	}
}
