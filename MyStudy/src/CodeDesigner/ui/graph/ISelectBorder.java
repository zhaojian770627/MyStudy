package CodeDesigner.ui.graph;

import java.awt.Graphics;

import CodeDesigner.ui.manager.DragDirection;

/**
 * ����ѡ�п򣬰������϶���С��С����
 * @author zhaojian
 *
 */
public interface ISelectBorder {
	void paint(Graphics g);
	DragDirection inAdjustArea(int x,int y);
}
