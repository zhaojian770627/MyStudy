package ai.Queen;

/**
 * ��¼���̻ʺ��״̬
 * 
 * @author zhaojian
 * 
 */
class Data {
	public Data(int m) {
		if (m > 8)
			m = 8;
		max = m;
		index = 0;
		s = new int[m];
		for (int i = 0; i < m; i++)
			s[i] = -1;
	}

	public void setValue(int row, int Col) {
		this.s[row] = Col;
	}

	public Data Clone() {
		Data d = new Data(max);
		d.index = this.index;
		for (int i = 0; i < max; i++)
			d.s[i] = this.s[i];
		return d;
	}

	// �ʺ���Ŀ
	int max;
	// �ڼ���
	int index;
	/*
	 * s[i]=j��������ʾi��j����һ���ʺ�
	 */
	int s[];
}