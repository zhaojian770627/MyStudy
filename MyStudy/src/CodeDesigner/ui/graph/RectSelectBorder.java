package CodeDesigner.ui.graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import CodeDesigner.ui.IElementPaint;
import CodeDesigner.ui.manager.DragDirection;

/**
 * 矩形选择框
 * @author zhaojianc
 *
 */
public class RectSelectBorder implements ISelectBorder {
	IElementPaint ele;
	int dis = 10;
	Rectangle leftTop = new Rectangle();
	Rectangle leftBottom = new Rectangle();
	Rectangle rightTop = new Rectangle();
	Rectangle rightBottom = new Rectangle();

	public RectSelectBorder(IElementPaint ele) {
		this.ele = ele;
	}

	/*
	 * 计算调整框的位置和尺寸
	 */
	public void ajustRect() {
		leftTop.setRect(ele.getLocation().x, ele.getLocation().y, dis, dis);
		leftBottom.setRect(ele.getLocation().x,
				ele.getLocation().y + ele.getSize().height - dis, dis, dis);
		rightTop.setRect(ele.getLocation().x + ele.getSize().width - dis,
				ele.getLocation().y, dis, dis);
		rightBottom.setRect(ele.getLocation().x + ele.getSize().width - dis,
				ele.getLocation().y + ele.getSize().height - dis, dis, dis);
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(ele.getLocation().x, ele.getLocation().getLocation().y,
				ele.getSize().width, ele.getSize().height);

		drawAdjustRect(g);
	}

	/*
	 * 绘制尺寸拖动框
	 */
	private void drawAdjustRect(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(leftTop.x, leftTop.y, dis, dis);
		g.drawRect(leftBottom.x, leftBottom.y, dis, dis);
		g.drawRect(rightTop.x, rightTop.y, dis, dis);
		g.drawRect(rightBottom.x, rightBottom.y, dis, dis);
	}

	@Override
	public DragDirection inAdjustArea(int x, int y) {
		if (leftTop.contains(x, y))
			return DragDirection.LeftTop;
		else if (leftBottom.contains(x, y))
			return DragDirection.LeftBottom;
		else if (rightTop.contains(x, y))
			return DragDirection.RightTop;
		else if (rightBottom.contains(x, y))
			return DragDirection.RightBottom;
		else
			return DragDirection.NONE;
	}

}
