package ai.Queen;

/**
 * 记录棋盘皇后的状态
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

	// 皇后数目
	int max;
	// 第几层
	int index;
	/*
	 * s[i]=j，用来表示i行j列有一个皇后
	 */
	int s[];
}