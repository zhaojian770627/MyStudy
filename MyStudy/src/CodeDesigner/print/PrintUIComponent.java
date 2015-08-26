package CodeDesigner.print;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

public class PrintUIComponent implements Printable {
	private int[] pageBreaks;// array of page break line positions
	private Header header;
	private PrinterJob job;

	// 虚拟的数据源
	DataSource ds = new DataSource();
	// 横向两栏
	final int column = 2;
	// 宽度假设200
	final int columnWidth = 200;
	int lineHeight;

	// -------------------------------------
	public PrinterJob getJob() {
		return job;
	}

	public void setJob(PrinterJob job) {
		this.job = job;
	}

	public int[] getPageBreaks() {
		return pageBreaks;
	}

	public void setPageBreaks(int[] pageBreaks) {
		this.pageBreaks = pageBreaks;
	}

	// --------------------------------------------------

	public PrintUIComponent() {
		initPrinterJob();
	}

	private void initPrinterJob() {
		job = PrinterJob.getPrinterJob();
		job.setJobName("Print TextArea");// 出现在系统打印任务列表
		job.setPrintable(this);
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
			throws PrinterException {
		Graphics2D g2 = (Graphics2D) graphics.create();
		/* Calculate "pageBreaks" */
		Font font = new Font("Serif", Font.PLAIN, 12);
		FontMetrics metrics = g2.getFontMetrics(font);
		lineHeight = metrics.getHeight();

		// 每个卡片占用的高度,假设每个卡片为两行数据，按两栏进行计算，横向每栏两个卡片
		// 每个卡片占用的高度,加10个间距
		int cardHeight = lineHeight * 2 + 10;

		if (pageBreaks == null) {
			// 每页的Card数
			int cardPerPage = (int) (pageFormat.getImageableHeight() / cardHeight);

			// 计算分页数
			int numBreaks = ds.getRecordCount() / column ;
			pageBreaks = new int[numBreaks];
			// 每页开始卡片数
			for (int b = 0; b < numBreaks; b++) {
				pageBreaks[b] = (b + 1) * cardPerPage;
			}
		}

		if (pageIndex > pageBreaks.length-1) {
			return NO_SUCH_PAGE;
		}

		/* (0,0) is outside the imageable area, translate to avoid clipping */
		g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		/* Draw each line card that is on this page */
		int y = 0;
		int x = 0;
		// 从数据源的开始记录数
		int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex - 1];
		int end = (pageIndex == pageBreaks.length - 1) ? ds.getRecordCount()
				: pageBreaks[pageIndex];

		for (int cardindex = start; cardindex < end; cardindex++) {
			// 计算第几栏
			int iColumn = cardindex % column;
			// 计算起始x,计算起始y
			int iRow = cardindex / column;

			y = iRow * cardHeight+lineHeight;;
			x = iColumn * columnWidth;

			drawCard(g2, x, y, cardindex);
		}

		/* dispose of the graphics copy */
		g2.dispose();
		/* Tell the caller that this page is part of the printed document */
		return PAGE_EXISTS;
	}

	public void createAndShowPreviewDialog() {
		JDialog previewDialog = new JDialog();
		previewDialog.setTitle("Print Preview Dialog");
		JPanel contentPane = new JPanel(new BorderLayout());
		PreviewArea previewArea = new PreviewArea();
		previewArea.addMouseListener(new PreviewAreaMouseAdapter(previewArea));
		JScrollPane scrollPane = new JScrollPane(previewArea);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		header = new Header(previewArea);
		contentPane.add(header, BorderLayout.NORTH);
		previewDialog.setContentPane(contentPane);
		previewDialog.setSize(600, 600);
		previewDialog
				.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		previewDialog.setVisible(true);
	}

	private void drawCard(Graphics2D g2, int x, int y, int cardindex) {
		// 绘制标题
		g2.drawString(ds.tile[0], x, y);
		// 绘制数据
		g2.drawString(ds.data[cardindex][0], x + 30, y);

		g2.drawString(ds.tile[1], x, y + lineHeight);
		// 绘制数据
		g2.drawString(ds.data[cardindex][1], x + 30, y + lineHeight);
	}

	private class PreviewArea extends Component {
		private static final long serialVersionUID = -6384174997251439843L;
		private PageFormat pageFormat;
		private int pageIndex;
		private int w;
		private int h;
		private final int marginX = 10;
		private final int marginY = 20;

		private PreviewArea() {
			pageFormat = job.pageDialog(job.defaultPage());
			pageIndex = 0;
			w = (int) pageFormat.getWidth();
			h = (int) pageFormat.getHeight();			
		}

		private int getPageIndex() {
			return pageIndex;
		}

		private void setPageIndex(int pageIndex) {
			this.pageIndex = pageIndex;
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(w + 2 * marginX, h + 2 * marginY);
		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g.create();			
			g2.translate(marginX, marginY);
			g2.drawRect(0, 0, w, h);
			int ix = (int) (pageFormat.getImageableX() - 1);
			int iy = (int) (pageFormat.getImageableY() - 1);
			int iw = (int) (pageFormat.getImageableWidth() + 1);
			int ih = (int) (pageFormat.getImageableHeight() + 1);
			
			g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_ROUND,
					BasicStroke.JOIN_ROUND, 10f, new float[] { 5, 5 }, 0f));
			g2.drawRect(ix, iy, iw, ih);
			try {
				PrintUIComponent.this.print(g2, pageFormat, pageIndex);
			} catch (PrinterException e) {
				e.printStackTrace();
			}
			g2.dispose();
			header.setPaintable(true);
			header.repaint();
		}
	}

	private class PreviewAreaMouseAdapter extends MouseAdapter {
		private PreviewArea previewArea;

		private PreviewAreaMouseAdapter(PreviewArea previewArea) {
			this.previewArea = previewArea;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			int currentIndex = previewArea.getPageIndex();
			if (e.getButton() == MouseEvent.BUTTON1) {
				/* next page */
				if (currentIndex < pageBreaks.length-1) {
					previewArea.setPageIndex(currentIndex + 1);
					previewArea.repaint();
				}
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				/* previous page */
				if (currentIndex > 0) {
					previewArea.setPageIndex(currentIndex - 1);
					previewArea.repaint();
				}
			}
		}
	}

	private class Header extends Component {
		private static final long serialVersionUID = -1741188309769027249L;
		private PreviewArea previewArea;
		private boolean paintable;

		private Header(PreviewArea previewArea) {
			this.previewArea = previewArea;
		}

		private void setPaintable(boolean paintable) {
			this.paintable = paintable;
		}

		@Override
		public void paint(Graphics g) {
			if (paintable) {
				g.setColor(Color.GRAY);
				g.drawString(
						String.valueOf(previewArea.getPageIndex() + 1)
								+ "/"
								+ String.valueOf(pageBreaks.length + 1)
								+ " pages (Click left mouse button to preview next page; right to previous)",
						10, 15);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(super.getPreferredSize().width, 20);
		}
	}
}
