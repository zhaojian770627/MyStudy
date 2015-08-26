package CodeDesigner.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import CodeDesigner.ui.event.IMouseListener;
import CodeDesigner.ui.event.MouseEventType;
import CodeDesigner.ui.manager.SelectMoveManager;
import CodeDesigner.ui.manager.SelectManager;
import CodeDesigner.ui.manager.WorkSapceManager;
import CodeDesigner.ui.model.DataModel;

public class DesignSpace extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<IMouseListener> releasedListeners;
	List<IMouseListener> pressedListeners;
	List<IMouseListener> exitedListeners;
	List<IMouseListener> enteredListeners;
	List<IMouseListener> clickedListeners;
	List<IMouseListener> movedListeners;
	List<IMouseListener> draggedListeners;

	/*
	 * 界面辅助控制器
	 */
	UIControl uiControl;

	/*
	 * 工作区域拖拽管理器
	 */
	WorkSapceManager tm;

	/*
	 * 选择管理器
	 */
	SelectManager sm;

	/*
	 * 选择移动管理器
	 */
	SelectMoveManager sdm;
	
	/*
	 * 数据模型
	 */
	DataModel dataModel;

	public DataModel getDataModel() {
		return dataModel;
	}

	public void setDataModel(DataModel dataModel) {
		this.dataModel = dataModel;
	}

	/*
	 * 得到界面控制器
	 */
	public UIControl getUiControl() {
		return uiControl;
	}

	/*
	 * 初始操作区
	 */
	ToggleRect workRect = new ToggleRect();

	public ToggleRect getWorkRect() {
		return workRect;
	}

	int i = 0;
	// 检测鼠标是否按下
	boolean inToggle = false;
	boolean toggled = false;
	int oldDragX = -1;
	int oldDragY = -1;
	int NewDragX = -1;
	int NewDragY = -1;

	public DesignSpace() {
		init();
		addMouseListen();
		this.getLocation();
	}

	private void fireEvent(MouseEventType t, MouseEvent me) {
		List<IMouseListener> lstlistener = null;
		switch (t) {
		case Pressed:
			lstlistener = pressedListeners;
			break;
		case Clicked:
			lstlistener = clickedListeners;
			break;
		case Dragged:
			lstlistener = draggedListeners;
			break;
		case Entered:
			lstlistener = enteredListeners;
			break;
		case Exited:
			lstlistener = exitedListeners;
			break;
		case Moved:
			lstlistener = movedListeners;
			break;
		case Released:
			lstlistener = releasedListeners;
			break;
		}

		if (lstlistener == null)
			return;

		for (IMouseListener l : lstlistener) {
			l.doAction(me);
		}
	}

	private void addMouseListen() {
		addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent me) {
				fireEvent(MouseEventType.Moved, me);
			}

			@Override
			public void mouseDragged(MouseEvent me) {				
				fireEvent(MouseEventType.Dragged, me);
			}

		});

		addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent me) {				
				fireEvent(MouseEventType.Released, me);
			}

			@Override
			public void mousePressed(MouseEvent me) {
				fireEvent(MouseEventType.Pressed, me);
			}

			@Override
			public void mouseExited(MouseEvent me) {
				fireEvent(MouseEventType.Exited, me);
			}

			@Override
			public void mouseEntered(MouseEvent me) {
				fireEvent(MouseEventType.Entered, me);
			}

			@Override
			public void mouseClicked(MouseEvent me) {
				fireEvent(MouseEventType.Clicked, me);
			}
		});
	}

	/*
	 * 增加鼠标监视器
	 */
	public void addMouseListener(MouseEventType t, IMouseListener listener) {
		switch (t) {
		case Pressed:
			pressedListeners.add(listener);
			break;
		case Clicked:
			clickedListeners.add(listener);
			break;
		case Dragged:
			draggedListeners.add(listener);
			break;
		case Entered:
			enteredListeners.add(listener);
			break;
		case Exited:
			exitedListeners.add(listener);
			break;
		case Moved:
			movedListeners.add(listener);
			break;
		case Released:
			releasedListeners.add(listener);
			break;
		}
	}

	/**
	 * 
	 */
	private void init() {
		dataModel = new DataModel();

		releasedListeners = new ArrayList<IMouseListener>();
		pressedListeners = new ArrayList<IMouseListener>();
		exitedListeners = new ArrayList<IMouseListener>();
		enteredListeners = new ArrayList<IMouseListener>();
		clickedListeners = new ArrayList<IMouseListener>();
		movedListeners = new ArrayList<IMouseListener>();
		draggedListeners = new ArrayList<IMouseListener>();

		workRect.setSize(500, 500);

		uiControl = new UIControl(this);
		tm = new WorkSapceManager(this);
		sm = new SelectManager(this);
		sdm=new SelectMoveManager(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawWorkBg(g);
		drawToggleRect(g);
		drawData(g);
	}

	private void drawData(Graphics g) {
		for (IElementPaint ele : dataModel.getElements()) {
			ele.paint(g);
		}
	}

	/*
	 * 绘制工作区背景
	 */
	private void drawWorkBg(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, (int) workRect.getWidth(), (int) workRect.getHeight());
	}

	/*
	 * 绘制拖拽线
	 */
	private void drawToggleRect(Graphics g) {
		workRect.paint(g);
	}
}
