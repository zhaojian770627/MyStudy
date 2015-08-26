package CodeDesigner.ui.compont;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * 设计器左的刻度条
 * 
 * @author zhaojian
 * 
 */
public class VBar extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean paintable = true;
	int width = 20;

	public VBar() {

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		drawBackground(g);
		drawMeasure(g);
	}

	/*
	 * 绘制刻度线
	 */
	private void drawMeasure(Graphics g) {
		g.setColor(Color.BLACK);
		int height = getHeight();
		if (paintable) {
			boolean b = true;
			for (int top = 5; top < height; top += 10) {
				if (b)
					g.drawLine(width - 10, top, width, top);
				else
					g.drawLine(width - 5, top, width, top);
				b = !b;
			}
		}
	}

	/*
	 * 绘制背景
	 */
	private void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, getHeight());
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(20, super.getPreferredSize().height);
	}
}
