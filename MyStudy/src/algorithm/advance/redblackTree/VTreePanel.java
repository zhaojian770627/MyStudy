package algorithm.advance.redblackTree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class VTreePanel extends JPanel {
	VTreeModel model;

	public VTreeModel getModel() {
		return model;
	}

	public void setModel(VTreeModel model) {
		this.model = model;
		if (model.getRoot() != null) {
			// 设置高度
			int childs = (int) Math.pow(2, model.getTreeHight() - 1);
			int w = (childs - 1) * model.gethDistance();
			int h = (model.getTreeHight() - 1) * model.getvDistance();
			if (w > this.getWidth() || h > this.getHeight())
				this.setPreferredSize(new Dimension(w, h + 50));
		}
		this.revalidate();
		this.repaint();

	}

	public VTreePanel() {
		this.setBackground(Color.gray);
	}

	public VTreePanel(VTreeModel model) {
		setModel(model);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model == null)
			return;
		RedBlackTree.RedBlackNode<Integer> root = model.getRoot();
		if (root == null)
			return;
		VTreeNode vroot = new VTreeNode(root);
		vroot.setX(this.getWidth() / 2);
		vroot.setY(10);
		paintNode(g, vroot, 0, this.getWidth() / 2);
	}

	/**
	 * 采用后续遍历的方法进行绘制
	 * 
	 * @param root
	 * @param h
	 *            控制高度*
	 */
	private void paintNode(Graphics g, VTreeNode node, int level, int distance) {
		if (node.getInnernode() == model.tree.getNullNode())
			return;

		// 画根
		node.paint(g);
		if (node.getInnernode().left != model.tree.getNullNode()) {
			node.setLeft(new VTreeNode(node.getInnernode().left));
			node.getLeft().setX(node.getX() - distance / 2);
			node.getLeft().setY((level + 1) * model.getvDistance());
			paintNode(g, node.getLeft(), level + 1, distance / 2);
			g.setColor(Color.BLUE);
			g.drawLine(node.getX() + 15 / 2, node.getY() + 15, node.getLeft()
					.getX() + 15 / 2, node.getLeft().getY());
		}

		if (node.getInnernode().right != model.tree.getNullNode()) {
			node.setRight(new VTreeNode(node.getInnernode().right));
			node.getRight().setX(node.getX() + distance / 2);
			node.getRight().setY((level + 1) * model.getvDistance());
			paintNode(g, node.getRight(), level + 1, distance / 2);
			g.setColor(Color.BLUE);
			g.drawLine(node.getX() + 15 / 2, node.getY() + 15, node.getRight()
					.getX() + 15 / 2, node.getRight().getY());
		}
	}
}