package CodeDesigner.ui.compont;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * 设计器上方的刻度条
 * @author zhaojian
 *
 */
public class HBar extends JComponent {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean paintable = true;
	int width = 0;
	int height = 20;

	public HBar() {

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
		int width = getWidth();
		if (paintable) {
			boolean b = true;
			for (int left = 5; left < width; left += 10) {
				if (b)
					g.drawLine(left, height - 10, left, height);
				else
					g.drawLine(left, height - 5, left, height);
				b=!b;
			}
		}
	}

	/*
	 * 绘制背景
	 */
	private void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), height);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(super.getPreferredSize().width, 20);
	}
}
