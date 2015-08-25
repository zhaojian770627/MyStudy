package algorithm.linear;

/**
 * ����ѭ������������ʽ�ӷ�
 * 
 * @author zhaojian
 * 
 */
public class CircleLink {

	class Coef {
		// ϵ��
		int value;

		// ָ��
		int A, B, C;
		// ��һ���ڵ�
		Coef next;

		public Coef() {
			A = B = C = 0;
			next = null;
		}

		public Coef(int v, int a, int b, int c) {
			this.value = v;
			A = a;
			B = b;
			C = c;
			next = null;
		}

		public int getA() {
			return A;
		}

		public void setA(int a) {
			A = a;
		}

		public int getB() {
			return B;
		}

		public void setB(int b) {
			B = b;
		}

		public int getC() {
			return C;
		}

		public void setC(int c) {
			C = c;
		}

		public Coef getNext() {
			return next;
		}

		public void setNext(Coef next) {
			this.next = next;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	class CoefList {
		// ͷ�ڵ�
		Coef ptr;

		public CoefList() {
			ptr = new Coef();
			ptr.setNext(ptr);
			ptr.setA(-1);
			ptr.setB(-1);
			ptr.setC(-1);
		}

		/*
		 * ��ϵ����С���в��� A B C�ɴ�С��˳��
		 */
		public void sortInsert(Coef node) {
			Coef f = ptr;
			Coef n = ptr.next;

			int c = Compare(node, n);

			// Ѱ�Һ��ʵĲ���λ��
			while (c == -1) {
				f = n;
				n = n.next;
				c = Compare(node, n);
			}

			node.setNext(n);
			f.setNext(node);
		}
	}

	/**
	 * 
	 * ϵ���ıȽϣ� 1 ���� -1 С�� 0����
	 **/
	private int Compare(Coef node, Coef n) {
		// A
		if (node.getA() > n.getA())
			return 1;
		else if (node.getA() == n.getA()) {
			if (node.getB() > n.getB())
				return 1;
			else if (node.getB() == n.getB()) {
				if (node.getC() > n.getC())
					return 1;
				else if (node.getC() == n.getC())
					return 0;
			}
		}
		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CircleLink cl = new CircleLink();
		cl.execute();
		System.exit(0);
	}

	private void execute() {
		CoefList l1 = new CoefList();
		Coef c11 = new Coef(1, 1, 0, 0);
		Coef c12 = new Coef(1, 0, 1, 0);
		Coef c13 = new Coef(1, 0, 0, 1);

		l1.sortInsert(c11);
		l1.sortInsert(c12);
		l1.sortInsert(c13);

		CoefList l2 = new CoefList();
		Coef c21 = new Coef(1, 2, 0, 0);
		Coef c22 = new Coef(-2, 0, 1, 0);
		Coef c23 = new Coef(-1, 0, 0, 1);

		l2.sortInsert(c21);
		l2.sortInsert(c22);
		l2.sortInsert(c23);

		Coef lp = l1.ptr;
		Coef lq = l2.ptr;
		Coef lq1;

		lp = lp.getNext();
		lq1 = lq;
		lq = lq.getNext();
		int c;

		while (true) {
			if (lp.getA() == -1)
				break;
			
			// ����ǳ˷������������lq��lm�ĳ���
			// ���������lp�滻Ϊ�����������

			c = Compare(lp, lq);
			if (c == -1) {
				lq1 = lq;
				lq = lq.next;
			} else if (c == 0) {
				lq.setValue(lq.getValue() + lp.getValue());
				// ϵ��Ϊ0������ɾ��
				if (lq.getValue() == 0) {
					lq = lq.next;
					lq1.setNext(lq);
					lp = lp.next;
				} else {
					lp = lp.next;
					lq1 = lq;
					lq = lq.next;
				}
			} else {
				Coef q2 = new Coef();
				// ��ֵ
				q2.setValue(lp.getValue());
				q2.setA(lp.getA());
				q2.setB(lp.getB());
				q2.setC(lp.getC());
				// ����
				q2.setNext(lq);
				lq1.setNext(q2);
				lq1 = q2;

				lp = lp.next;
			}
		}

		// ��ӡl2 �б�
		lq = l2.ptr.getNext();
		String pt = "";
		while (lq.getA() != -1) {
			pt = pt + "," + lq.getValue();
			if (lq.getA() != 0)
				pt = pt + "x" + lq.getA();
			if (lq.getB() != 0)
				pt = pt + "y" + lq.getB();
			if (lq.getC() != 0)
				pt = pt + "z" + lq.getC();
			lq = lq.next;
		}
		System.out.print(pt.substring(1));
	}

}
