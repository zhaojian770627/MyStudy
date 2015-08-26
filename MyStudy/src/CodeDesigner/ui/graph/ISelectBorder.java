package CodeDesigner.ui.graph;

import java.awt.Graphics;

import CodeDesigner.ui.manager.DragDirection;

/**
 * 绘制选中框，包含可拖动大小的小方块
 * @author zhaojian
 *
 */
public interface ISelectBorder {
	void paint(Graphics g);
	DragDirection inAdjustArea(int x,int y);
}
