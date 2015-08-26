package CodeDesigner.ui.manager;

import java.awt.event.MouseEvent;

import CodeDesigner.ui.DesignSpace;
import CodeDesigner.ui.event.IMouseListener;
import CodeDesigner.ui.event.MouseEventType;

/**
 * Ñ¡Ôñ¹ÜÀíÆ÷
 * @author zhaojianc
 *
 */
public class SelectManager {
	DesignSpace parent;
	IMouseListener pressedlistener;
	public SelectManager(DesignSpace ds) {
		this.parent = ds;
		regListener();
	}
	private void regListener() {
		pressedlistener = new IMouseListener() {

			@Override
			public void doAction(MouseEvent me) {
				int x = me.getX();
				int y = me.getY();
				
				parent.getUiControl().setEleFocus(x,y);
			}
		};
		parent.addMouseListener(MouseEventType.Pressed, pressedlistener);
	}
}
