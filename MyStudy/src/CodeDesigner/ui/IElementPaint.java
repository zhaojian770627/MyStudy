package CodeDesigner.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import CodeDesigner.ui.manager.DragDirection;
import CodeDesigner.ui.model.IDataElement;

public interface IElementPaint extends IDataElement {
	
	/*
	 * ���л���
	 */
	void paint(Graphics g);
	
	/*
	 * ���óߴ�
	 */
	void setSize(Dimension size);
	
	/*
	 * �õ��ߴ�
	 */
	Dimension getSize();
	
	/*
	 * ����λ��
	 */
	void setLocation(Point pt);
	
	/*
	 * �õ�λ��
	 */
	Point getLocation();
	/*
	 * ���ý���
	 */
	void setFocus(boolean focus);

	boolean contrainXY(int x, int y);
	
	DragDirection inAdjustArea(int x, int y);
}
