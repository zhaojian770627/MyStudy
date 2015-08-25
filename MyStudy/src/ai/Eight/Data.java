package ai.Eight;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author zhaojian
 * 
 */
public class Data {
	// ״̬����
	int[][] s;

	// ά��
	int dim = 3;

	// ���
	int deep;

	// Ϊ���ٶԱȣ���¼0��λ�ã���Ϊֻ��0���ſ����ƶ�
	int r, c;

	// ��¼·��
	Data parent;

	// ��¼���ӣ������޸�ָ��
	List<Data> children;

	int _blank = 0;
	
	// �����õ�FTֵ
	int FT;

	void addChildren(Data c) {
		if (!children.contains(c))
			children.add(c);
	}

	void romoveChildren(Data c) {
		children.remove(c);
	}

	public Data() {
		s = new int[dim][dim];
		children = new ArrayList<Data>();
		deep = 0;
	}

	public void setValue(int[][] initData) {
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++) {
				s[i][j] = initData[i][j];
				if (s[i][j] == _blank) {
					r = i;
					c = j;
				}
			}
	}

	/*
	 * �������ʼ��
	 */
	void random() {
		List<Integer> lst = new ArrayList<Integer>();
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim;) {
				double d = Math.random();
				int n = (int) (d * dim * dim);
				if (!lst.contains(n)) {
					s[i][j] = n;
					// ��¼_blank��λ��
					if (n == _blank) {
						r = i;
						c = j;
					}
					lst.add(n);
					j++;
				}
			}
	}

	void out() {
		System.out.println("-------------");
		System.out.println("| " + s[0][0] + " | " + s[0][1] + " | " + s[0][2]
				+ " |");
		System.out.println("-------------");
		System.out.println("| " + s[1][0] + " | " + s[1][1] + " | " + s[1][2]
				+ " |");
		System.out.println("-------------");
		System.out.println("| " + s[2][0] + " | " + s[2][1] + " | " + s[2][2]
				+ " |");
		System.out.println("-------------");
	}

	public Data Clone() {
		Data d = new Data();
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				d.s[i][j] = this.s[i][j];
		d.r = this.r;
		d.c = this.c;
		return d;
	}

	/*
	 * (non-Javadoc) ����״̬��Ψһ�Ա�ʾ
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer keyBuf = new StringBuffer();
		for (int i = 0; i < dim; i++)
			for (int j = 0; j < dim; j++)
				keyBuf.append(s[i][j]);

		return keyBuf.toString();
	}

	@Override
	public boolean equals(Object obj) {
		Data o = (Data) obj;
		return this.toString().equals(o.toString());
	}
}
