package algorithm.linear;

/**
 * 拓扑排序
 * @author zhaojian
 *
 */
public class TopologySort {
	int n = 10;

	class GX {
		int x, y;

		public GX(int x, int y) throws Exception {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}

	class Node {
		int suc;
		Node next;

		public int getSuc() {
			return suc;
		}

		public void setSuc(int suc) {
			this.suc = suc;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	class Top {
		int count;
		Node next;

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}
	}

	public void Sort() throws Exception {
		GX[] gx = new GX[10];
		gx[0] = new GX(9, 2);
		gx[1] = new GX(3, 7);
		gx[2] = new GX(7, 5);
		gx[3] = new GX(5, 8);
		gx[4] = new GX(8, 6);
		gx[5] = new GX(4, 6);
		gx[6] = new GX(1, 3);
		gx[7] = new GX(7, 4);
		gx[8] = new GX(9, 5);
		gx[9] = new GX(2, 8);

		Top[] tops = new Top[10];
		for (int i = 1; i < 10; i++) {
			Top t = new Top();
			t.setCount(0);
			t.setNext(null);
			tops[i] = t;
		}

		// 录入数据
		for (int i = 0; i < gx.length; i++) {
			// x-->y
			tops[gx[i].y].count++;
			Node p = new Node();
			p.setSuc(gx[i].y);
			p.setNext(tops[gx[i].x].getNext());
			tops[gx[i].x].setNext(p);
		}

		int R = 0, F = 0;
		int[] QLINK = new int[10];
		for (int i = 0; i < QLINK.length; i++)
			QLINK[i] = 0;

		for (int k = 1; k < tops.length; k++) {
			if (tops[k].getCount() == 0) {
				QLINK[R] = k;
				R = k;
			}
		}
		F = QLINK[0];
		int N = n;

		while (F != 0) {
			// 输入出数据
			System.out.println(F);

			N--;
			Node p = tops[F].next;
			while (p != null) {
				tops[p.getSuc()].count--;
				if (tops[p.getSuc()].count == 0) {
					QLINK[R] = p.getSuc();
					R = p.getSuc();
				}
				p = p.next;
			}
			F = QLINK[F];
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		TopologySort ts = new TopologySort();
		ts.Sort();
	}

}
