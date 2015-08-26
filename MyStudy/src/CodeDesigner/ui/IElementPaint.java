package CodeDesigner.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import CodeDesigner.ui.manager.DragDirection;
import CodeDesigner.ui.model.IDataElement;

public interface IElementPaint extends IDataElement {
	
	/*
	 * 进行绘制
	 */
	void paint(Graphics g);
	
	/*
	 * 设置尺寸
	 */
	void setSize(Dimension size);
	
	/*
	 * 得到尺寸
	 */
	Dimension getSize();
	
	/*
	 * 设置位置
	 */
	void setLocation(Point pt);
	
	/*
	 * 得到位置
	 */
	Point getLocation();
	/*
	 * 设置焦点
	 */
	void setFocus(boolean focus);

	boolean contrainXY(int x, int y);
	
	DragDirection inAdjustArea(int x, int y);
}
