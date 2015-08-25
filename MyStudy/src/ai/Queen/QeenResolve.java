package ai.Queen;

import java.util.ArrayList;
import java.util.List;

public class QeenResolve {
	private List<Data> result = new ArrayList<Data>();
	private int backCount = 0;
	private int resultCount = 0;

	public void resolve() {
		backCount = 0;
		resultCount = 0;
		Data d = new Data(8);
		result.clear();
		backTrack(d);
	}

	public boolean backTrack(Data d) {
		backCount++;
		if (term(d)) {
			showAnswer(d);
			return false;
		}
		// if (!deadend(d))
		// return false;
		List<Integer> rules = AppRules(d);
		for (Integer i : rules) {
			Data rdata = Gen(i, d);
			backTrack(rdata);
		}
		return true;
	}

	private Data Gen(Integer i, Data d) {
		Data nd = d.Clone();
		nd.setValue(d.index, i);
		nd.index++;
		return nd;
	}

	private List<Integer> AppRules(Data d) {
		int row = d.index;
		List<Integer> rules = new ArrayList<Integer>();
		for (int col = 0; col < d.max; col++) {
			Data temp = d.Clone();
			temp.setValue(row, col);
			if (deadend(temp))
				rules.add(col);
		}
		return rules;
	}

	private void showAnswer(Data d) {
		resultCount++;
		result.add(d);
//		for (int i = 0; i < d.max; i++) {
//			System.out.print(i + "," + d.s[i]+" ");			
//		}
//		System.out.println();
	}

	boolean deadend(Data d) {
		// ͬһ����Խ��ߵ���Ŀ
		int zdjs = d.max * (d.max - 1) / 2 - 1;
		// ���·���Խ��ߵĻʺ���
		int a[] = new int[zdjs];
		// ���·���Խ��ߵĻʺ���
		int b[] = new int[zdjs];
		// ��¼ͬһ���Ƿ��лʺ�
		int c[] = new int[d.max];

		// Ϊ�˿����������¼�ʺ���������һ��ֵ��ʹ������������0
		int m = zdjs / 2;

		for (int i = 0; i < d.max; i++)
			c[i] = 0;
		for (int i = 0; i < zdjs; i++) {
			a[i] = b[i] = 0;
		}

		for (int row = 0; row < d.max; row++) {
			int hasCol = d.s[row];
			if (hasCol == -1)
				break;
			c[hasCol] = c[hasCol] + 1;
			// ���ͬһ���ϻʺ���������1 ��������λ�ò��ʺ�
			if (c[hasCol] >= 2)
				return false;

			// ��������ж������Խ����ϵĻʺ�������һ�����1������λ�ò��ʺ�
			int z = row - hasCol;
			if (Math.abs(z) < d.max - 1) {
				a[z + m] = a[z + m] + 1;
				if (a[z + m] >= 2)
					return false;
			}

			int f = row + hasCol;
			if (f > 0 && f < 2 * d.max) {
				b[f - 1] = b[f - 1] + 1;
				if (b[row + hasCol - 1] >= 2)
					return false;
			}
		}
		return true;
	}

	boolean term(Data d) {
		if (d.index == d.max)
			return true;
		else
			return false;
	}

	public List<Data> getResult() {
		return result;
	}

	public int getBackCount() {
		return backCount;
	}

	public int getResultCount() {
		return resultCount;
	}
}
