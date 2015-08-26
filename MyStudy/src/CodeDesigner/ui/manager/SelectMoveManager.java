package CodeDesigner.ui.manager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import CodeDesigner.ui.DesignSpace;
import CodeDesigner.ui.IElementPaint;
import CodeDesigner.ui.event.IMouseListener;
import CodeDesigner.ui.event.MouseEventType;

/**
 * 选择移动管理器
 * @author zhaojianc
 *
 */
public class SelectMoveManager {
	IMouseListener draggedlistener;
	IMouseListener releasedlistener;

	DesignSpace parent;
	// 当前正在移动的元素
	IElementPaint ele;

	DragDirection direct;

	int disX = -1;
	int disY = -1;

	int oldDragX = -1;
	int oldDragY = -1;

	int NewDragX = -1;
	int NewDragY = -1;

	Rectangle oldRect;
	Rectangle newRect;

	public SelectMoveManager(DesignSpace ds) {
		this.parent = ds;
		regListener();
	}

	private void regListener() {
		draggedlistener = new IMouseListener() {

			@Override
			public void doAction(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				Graphics g = parent.getGraphics();

				if (parent.getUiControl().containOper(
						OperateType.SingleSelectMove)) {
					execRectMove(false, me.getX(), me.getY(), g);
				} else if (parent.getUiControl().containOper(
						OperateType.AjdustSize)) {
					execAdjustRect(false, direct, me.getX(), me.getY(), g);
				} else {
					ele = parent.getUiControl().getHitEle(x, y);

					if (ele == null)
						return;

					// 判断是否移动或调整大小
					direct = ele.inAdjustArea(x, y);
					if (direct.equals(DragDirection.NONE)) {
						disX = x - ele.getLocation().x;
						disY = y - ele.getLocation().y;
						execRectMove(true, me.getX(), me.getY(), g);
						parent.getUiControl().addOper(
								OperateType.SingleSelectMove);
					} else {
						execAdjustRect(true, direct, x, y, g);
						parent.getUiControl().addOper(OperateType.AjdustSize);
					}
				}
			}
		};

		releasedlistener = new IMouseListener() {

			@Override
			public void doAction(MouseEvent me) {
				if (parent.getUiControl().containOper(
						OperateType.SingleSelectMove)) {
					disX = -1;
					disY = -1;

					oldDragX = -1;
					oldDragY = -1;

					ele.setLocation(new Point(NewDragX, NewDragY));
					NewDragX = -1;
					NewDragY = -1;
					parent.repaint();
					ele = null;
					parent.getUiControl().removeOper(
							OperateType.SingleSelectMove);
				} else if (parent.getUiControl().containOper(
						OperateType.AjdustSize)) {
					ele.setLocation(new Point(newRect.x, newRect.y));
					ele.setSize(new Dimension(newRect.width, newRect.height));
					parent.repaint();
					ele = null;
					parent.getUiControl().removeOper(OperateType.AjdustSize);
				}
			}
		};

		parent.addMouseListener(MouseEventType.Dragged, draggedlistener);
		parent.addMouseListener(MouseEventType.Released, releasedlistener);
	}

	private void execAdjustRect(boolean first, DragDirection direct, int x,
			int y, Graphics g) {
		g.setXORMode(Color.red);
		g.setColor(Color.green);
		Rectangle rt = new Rectangle();

		int tx, ty, w, h;
		switch (direct) {
		case LeftTop:
			w = ele.getLocation().x + ele.getSize().width - x;
			h = ele.getLocation().y + ele.getSize().height - y;
			rt.setRect(x, y, w, h);
			break;
		case LeftBottom:
			w = ele.getLocation().x + ele.getSize().width - x;
			h = y - ele.getLocation().y;
			rt.setRect(x, ele.getLocation().getY(), w, h);
			break;
		case RightTop:
			rt.setRect(ele.getLocation().getX(), y, x - ele.getLocation().x,
					ele.getLocation().y + ele.getSize().height - y);
			break;
		case RightBottom:
			rt.setRect(ele.getLocation().x, ele.getLocation().y,
					x - ele.getLocation().x, y - ele.getLocation().y);
			break;
		}

		newRect = rt;
		if (first)
			g.drawRect(newRect.x, newRect.y, newRect.width, newRect.height);
		else {
			g.drawRect(oldRect.x, oldRect.y, oldRect.width, oldRect.height);
			g.drawRect(newRect.x, newRect.y, newRect.width, newRect.height);
		}
		oldRect = rt;
	}

	private void execRectMove(boolean first, int x, int y, Graphics g) {
		g.setXORMode(Color.red);
		g.setColor(Color.green);

		int posX = x - disX;
		int posY = y - disY;

		NewDragX = posX;
		NewDragY = posY;

		if (first) {
			g.drawRect(posX, posY, ele.getSize().width, ele.getSize().height);
		} else {
			g.drawRect(oldDragX, oldDragY, ele.getSize().width,
					ele.getSize().height);
			g.drawRect(posX, posY, ele.getSize().width, ele.getSize().height);
		}
		oldDragX = posX;
		oldDragY = posY;
	}
}
