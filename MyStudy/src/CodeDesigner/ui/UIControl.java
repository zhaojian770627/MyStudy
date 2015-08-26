package CodeDesigner.ui;

import java.util.HashMap;
import java.util.Map;

import CodeDesigner.ui.manager.OperateType;

/**
 * 界面辅助控制类
 * 
 * @author zhaojian
 * 
 */
public class UIControl {
	// 保存正在操作的操作列表
	Map<OperateType, OperateType> operMap = new HashMap<OperateType, OperateType>();
	DesignSpace ds;

	UIControl(DesignSpace ds) {
		this.ds = ds;
	}

	/*
	 * 是否在拖拽小矩形区
	 */
	public boolean inTogget(int x, int y) {
		return ds.getWorkRect().inTogget(x, y);
	}

	/*
	 * 设置工作区尺寸
	 */
	public void setWorkRectSize(int newDragX, int newDragY) {
		ds.getWorkRect().setSize(newDragX, newDragY);
		ds.repaint();
	}

	/*
	 * 设置处于鼠标点击范围的元素的焦点
	 */
	public void setEleFocus(int x, int y) {
		for (IElementPaint ele : ds.getDataModel().getElements()) {
			if (ele.contrainXY(x, y)) {
				ele.setFocus(true);
			} else {
				ele.setFocus(false);
			}
		}
		ds.repaint();
	}

	/*
	 * 得到鼠标击中的元素
	 */
	public IElementPaint getHitEle(int x, int y) {
		for (IElementPaint ele : ds.getDataModel().getElements()) {
			if (ele.contrainXY(x, y)) {
				return ele;
			}
		}
		return null;
	}

	public void addOper(OperateType t) {
		operMap.put(t, t);
	}

	public void removeOper(OperateType t) {
		if (operMap.containsKey(t))
			operMap.remove(t);
	}

	public boolean containOper(OperateType t) {
		return operMap.containsKey(t);
	}

	public int getOperCount() {
		return operMap.size();
	}
}
