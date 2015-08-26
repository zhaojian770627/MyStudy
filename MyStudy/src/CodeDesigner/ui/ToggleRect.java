package CodeDesigner.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Stroke;

public class ToggleRect extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 控制小矩形的大小
	int dis = 5;
	Rectangle rightBottom = new Rectangle();

	public void paint(Graphics g) {
		paintBoder(g);
		paintToggleRect(g);
	}

	private void paintBoder(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.gray);
		Stroke st = g2d.getStroke();
		Stroke bs;
		bs = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
				0, new float[] { 16, 4 }, 0);
		g2d.setStroke(bs);
		g2d.drawRect(0, 0, width, height);
		g2d.setStroke(st);
	}

	private void paintToggleRect(Graphics g) {
		g.setColor(Color.blue);
		int d = 2 * dis;
		g.drawRect(rightBottom.x, rightBottom.y, d, d);

	}

	@Override
	public void setSize(int w, int h) {
		super.setSize(w, h);

		rightBottom.x = w - dis;
		rightBottom.y = h - dis;
		rightBottom.setSize(dis * 2, dis * 2);

	}

	public boolean inTogget(int x, int y) {
		return rightBottom.contains(x, y);
	}
}
