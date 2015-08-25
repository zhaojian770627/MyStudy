package ai.Eight;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawArea extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BufferedImage img;
	Data model;
	BufferedImage imgs[];
	int[][] bit;

	public DrawArea(int[][] gary) {
		img = loadFromResource("Koala.jpg");
		bit = gary;
		splitImage();
	}

	void splitImage() {
		int rows = 3;
		int cols = 3;
		int count = 0;
		int chunkWidth = img.getWidth() / cols;
		int chunkHeight = img.getHeight() / rows;
		int chunks = rows * cols;
		imgs = new BufferedImage[chunks];
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {
				count = bit[x][y];
				imgs[count] = new BufferedImage(chunkWidth, chunkHeight,
						img.getType());
				Graphics2D gr = imgs[count].createGraphics();

				gr.drawImage(img, 0, 0, chunkWidth, chunkHeight,
						chunkWidth * y, chunkHeight * x, chunkWidth * y
								+ chunkWidth, chunkHeight * x + chunkHeight,
						null);

				// if (count == 0) {
				gr.setColor(Color.RED);
				gr.setFont(new Font("Times New Roman", Font.BOLD, 40));
				gr.drawString(String.valueOf(count), 50, 50);
				// }
				gr.dispose();
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			PaintImage(g);
			PaintBoard(g);
		}
	}

	private void PaintImage(Graphics g) {
		for (int i = 0; i < model.dim; i++)
			for (int j = 0; j < model.dim; j++) {
				int pos = model.s[i][j];
				g.drawImage(imgs[pos], j * imgs[0].getWidth(),
						i * imgs[0].getHeight(), imgs[pos].getWidth(),
						imgs[pos].getHeight(), this);
			}
	}

	private void PaintBoard(Graphics g) {
		g.setColor(Color.BLACK);
		for (int i = 0; i < model.dim; i++) {
			for (int j = 0; j < model.dim; j++) {
				g.drawRect(i * imgs[0].getWidth(), j * imgs[0].getHeight(),
						imgs[0].getWidth(), imgs[0].getHeight());
			}
		}

	}

	private BufferedImage loadFromResource(String full_path) {
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
