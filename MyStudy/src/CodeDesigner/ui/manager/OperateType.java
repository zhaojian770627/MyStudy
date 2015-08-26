package CodeDesigner.ui.manager;

/**
 * 正在执行的操作，避免多个操作在
 * 操作链上冲突
 * @author zhaojian
 *
 */
public enum OperateType {
	/*
	 * 工作区拖拽 
	 */
	WorkSpceDrag,
	
	/*
	 * 单击选择
	 */
	SingleSelect,
	
	/*
	 * 多选
	 */
	MultSelect,
	
	/*
	 * 单选移动
	 */
	SingleSelectMove,
	
	/*
	 * 多选移动
	 */
	MultSelectMove, 
	
	/*
	 * 调整尺寸
	 */
	AjdustSize
	
}
