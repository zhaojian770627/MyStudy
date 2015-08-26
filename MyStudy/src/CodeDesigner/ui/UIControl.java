package CodeDesigner.ui;

import java.util.HashMap;
import java.util.Map;

import CodeDesigner.ui.manager.OperateType;

/**
 * ���渨��������
 * 
 * @author zhaojian
 * 
 */
public class UIControl {
	// �������ڲ����Ĳ����б�
	Map<OperateType, OperateType> operMap = new HashMap<OperateType, OperateType>();
	DesignSpace ds;

	UIControl(DesignSpace ds) {
		this.ds = ds;
	}

	/*
	 * �Ƿ�����קС������
	 */
	public boolean inTogget(int x, int y) {
		return ds.getWorkRect().inTogget(x, y);
	}

	/*
	 * ���ù������ߴ�
	 */
	public void setWorkRectSize(int newDragX, int newDragY) {
		ds.getWorkRect().setSize(newDragX, newDragY);
		ds.repaint();
	}

	/*
	 * ���ô����������Χ��Ԫ�صĽ���
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
	 * �õ������е�Ԫ��
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
