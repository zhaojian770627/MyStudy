package ai.Queen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawArea extends JPanel {

	private static final int rectWdith = 40;
	Image img;
	Data model;

	public DrawArea() {
		img = loadFromResource("qeen.png");
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		PaintBoard(g);
		PaintQueen(g);
	}

	private void PaintQueen(Graphics g) {
		if (model != null)
			for (int i = 0; i < model.max; i++) {
				int j = model.s[i];
				g.drawImage(img, i * rectWdith, j * rectWdith, rectWdith,
						rectWdith, this);
			}
	}

	private void PaintBoard(Graphics g) {
		Boolean b = true;
		for (int i = 0; i < 8; i++) {
			boolean b1 = b;
			for (int j = 0; j < 8; j++) {
				if (b1)
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);
				g.fillRect(i * rectWdith, j * rectWdith, rectWdith, rectWdith);

				b1 = !b1;
			}
			b = !b;
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, 8 * rectWdith, 8 * rectWdith);
	}

	private Image loadFromResource(String full_path) {
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(full_path);
			if (is != null) {
				BufferedImage img = ImageIO.read(is);
				return img;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (Exception e) {
				}
			}
		}
		return null;
	}

	public void setModel(Data data) {
		this.model = data;
		this.repaint();
	}
}
