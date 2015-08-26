package CodeDesigner.ui.manager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

import CodeDesigner.ui.DesignSpace;
import CodeDesigner.ui.event.IMouseListener;
import CodeDesigner.ui.event.MouseEventType;

/**
 * 工作区管理器
 * @author zhaojianc
 *
 */
public class WorkSapceManager {
	IMouseListener releasedlistener;
	IMouseListener draggedlistener;
	IMouseListener movedlistener;
	DesignSpace parent;

	// 检测鼠标是否按下
	boolean toggled = false;
	int oldDragX = -1;
	int oldDragY = -1;
	int NewDragX = -1;
	int NewDragY = -1;

	public WorkSapceManager(DesignSpace ds) {
		this.parent = ds;
		regListener();
	}

	void regListener() {
		draggedlistener = new IMouseListener() {

			@Override
			public void doAction(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();

				Graphics g = parent.getGraphics();
				if (parent.getUiControl().containOper(OperateType.WorkSpceDrag)) {
					DrawTogget(false, me.getX(), me.getY(), g);
				} else if (parent.getUiControl().inTogget(x, y)
						&& parent.getUiControl().getOperCount() == 0) // 在拖拽小方框内
				{
					DrawTogget(true, me.getX(), me.getY(), g);
					parent.getUiControl().addOper(OperateType.WorkSpceDrag);
				}

			}
		};

		releasedlistener = new IMouseListener() {

			@Override
			public void doAction(MouseEvent me) {
				oldDragX = -1;
				oldDragY = -1;

				if (parent.getUiControl().containOper(OperateType.WorkSpceDrag)) {
					// 拖拽完毕，设置新的工作区
					parent.getUiControl().setWorkRectSize(NewDragX, NewDragY);
					parent.getUiControl().removeOper(OperateType.WorkSpceDrag);
				}
			}
		};

		parent.addMouseListener(MouseEventType.Released, releasedlistener);
		parent.addMouseListener(MouseEventType.Dragged, draggedlistener);
	}

	private void DrawTogget(boolean first, int x, int y, Graphics g) {
		g.setXORMode(Color.red);
		g.setColor(Color.green);
		NewDragX = x;
		NewDragY = y;
		if (first) {
			g.drawRect(0, 0, x, y);
		} else {
			g.drawRect(0, 0, oldDragX, oldDragY);
			g.drawRect(0, 0, x, y);
		}
		oldDragX = x;
		oldDragY = y;
	}
}
