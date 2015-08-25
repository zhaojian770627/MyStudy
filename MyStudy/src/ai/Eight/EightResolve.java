package ai.Eight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class EightResolve {
	// Open����ջʵ�֣��ɷ����ʵ���������
	Stack<Data> openStack = new Stack<Data>();
	Queue<Data> openQueue = new LinkedList<Data>();
	List<Data> openList = new ArrayList<Data>();

	Map<String, Data> closed = new HashMap<String, Data>();
	Map<String, Data> G = new HashMap<String, Data>();
	Data gloal;
	Data result;
	int maxDeep = 7;

	/**
	 * ��ȡ��������㷨
	 * 
	 * @param sd
	 *            �����
	 * @param gloal
	 *            Ŀ��
	 * @return
	 */
	public boolean AResolve(Data sd, Data gloal) {
		openList.clear();
		closed.clear();
		G.clear();

		G.put(sd.toString(), sd);
		openList.add(sd);
		this.gloal = gloal;
		sd.FT = FS(sd);
		do {
			if (openList.isEmpty()) {
				return false;
			}
			Data n = openList.get(0);
			if (Goal(n)) {
				result = n;
				return true;
			}
			closed.put(n.toString(), n);
			openList.remove(0);
			// ������
			if (n.deep >= maxDeep)
				continue;
			List<Data> MI = Expand(n);
			for (Data d : MI) {
				d.FT = FS(n, d);
				openList.add(d);

				// MK �ڵ�
				if (openList.contains(d)) {
					for (Data td : openList) {
						if (td.equals(d)) {
							if (FS(n, d) < td.FT) {
								td.parent.romoveChildren(td);
								td.parent = n;
								n.addChildren(td);
								td.FT = FS(n, d);
							}
						}
					}

					continue;
				}

				if (closed.containsKey(d.toString())) {
					Data td = closed.get(d.toString());
					if (FS(n, d) < td.FT) {
						td.parent.romoveChildren(td);
						td.parent = n;
						n.addChildren(td);
						td.FT = FS(n, d);

						openList.add(td);
						closed.remove(td);
					}
					continue;
				}
				openList.add(d);
				// ����MJ�ڵ㣬���½ڵ�
				G.put(d.toString(), d);
				n.addChildren(d);
			}
			Collections.sort(openList, new DataComparator());

		} while (true);
	}

	class DataComparator implements Comparator<Data> {

		@Override
		public int compare(Data o1, Data o2) {
			if (o1.FT < o2.FT)
				return -1;
			else if (o1.FT == o2.FT)
				return 0;
			else
				return 1;

		}
	}

	private int FS(Data n, Data d) {
		int gs = n.deep;
		int hs = 0;
		for (int i = 0; i < d.dim; i++)
			for (int j = 0; j < d.dim; j++)
				if (gloal.s[i][j] != d.s[i][j])
					hs++;
		int fs = gs + hs;
		return fs;
	}

	private int FS(Data sd) {
		int gs = sd.deep;
		int hs = 0;
		for (int i = 0; i < sd.dim; i++)
			for (int j = 0; j < sd.dim; j++)
				if (gloal.s[i][j] != sd.s[i][j])
					hs++;
		int fs = gs + hs;
		return fs;
	}

	/**
	 * ��ȡ��������㷨
	 * 
	 * @param sd
	 *            �����
	 * @param gloal
	 *            Ŀ��
	 * @return
	 */
	public boolean widthResolve(Data sd, Data gloal) {
		openQueue.clear();
		closed.clear();
		G.clear();

		G.put(sd.toString(), sd);
		openQueue.offer(sd);
		this.gloal = gloal;

		do {
			if (openQueue.isEmpty()) {
				return false;
			}
			Data n = openQueue.poll();
			if (Goal(n)) {
				result = n;
				return true;
			}
			closed.put(n.toString(), n);

			// ������
			if (n.deep >= maxDeep)
				continue;
			List<Data> MI = Expand(n);
			for (Data d : MI) {
				if (Goal(d)) {
					result = d;
					return true;
				}
				// MK �ڵ�
				if (openQueue.contains(d)) {
					// for (Data td : openQueue) {
					// if (td.equals(d)) {
					// if (d.deep < td.deep) {
					// td.parent.romoveChildren(td);
					// td.parent = n;
					// n.addChildren(td);
					// td.deep = n.deep + 1;
					// }
					// }
					// }
					// �޸�ָ��
					continue;
				}

				if (closed.containsKey(d.toString())) {
					// Data td = closed.get(d.toString());
					// if (d.deep < td.deep) {
					// td.parent.romoveChildren(td);
					// td.parent = n;
					// n.addChildren(td);
					// td.deep = n.deep + 1;
					//
					// modiChildDeep(td);
					// }
					continue;
				}

				// ����MJ�ڵ㣬���½ڵ�
				G.put(d.toString(), d);
				openQueue.offer(d);
				n.addChildren(d);
			}
		} while (true);
	}

	/**
	 * ��ȡ��������㷨
	 * 
	 * @param sd
	 *            �����
	 * @param gloal
	 *            Ŀ��
	 * @return
	 */
	public boolean deepResolve(Data sd, Data gloal) {
		openStack.clear();
		closed.clear();
		G.clear();

		G.put(sd.toString(), sd);
		openStack.push(sd);
		this.gloal = gloal;

		do {
			if (openStack.empty()) {
				return false;
			}
			Data n = openStack.pop();
			if (Goal(n)) {
				result = n;
				return true;
			}
			closed.put(n.toString(), n);

			// ������
			if (n.deep >= maxDeep)
				continue;
			List<Data> MI = Expand(n);
			for (Data d : MI) {

				// MK �ڵ�
				if (openStack.contains(d)) {
					for (Data td : openStack) {
						if (td.equals(d)) {
							if (d.deep < td.deep) {
								td.parent.romoveChildren(td);
								td.parent = n;
								n.addChildren(td);
								td.deep = n.deep + 1;
							}
						}
					}
					// �޸�ָ��
					continue;
				}

				if (closed.containsKey(d.toString())) {
					Data td = closed.get(d.toString());
					if (d.deep < td.deep) {
						td.parent.romoveChildren(td);
						td.parent = n;
						n.addChildren(td);
						td.deep = n.deep + 1;

						modiChildDeep(td);
					}
					continue;
				}

				// ����MJ�ڵ㣬���½ڵ�
				G.put(d.toString(), d);
				openStack.push(d);
				n.addChildren(d);
			}
		} while (true);
	}

	private void modiChildDeep(Data td) {
		for (Data c : td.children) {
			c.deep = td.deep + 1;
			modiChildDeep(c);
		}
	}

	private List<Data> Expand(Data n) {
		List<Data> mi = new ArrayList<Data>();

		// �������˳��
		List<Integer> lstOrder = new ArrayList<Integer>();
		for (int i = 0; i < 4;) {
			int order = (int) (Math.random() * 4);
			if (!lstOrder.contains(order)) {
				lstOrder.add(order);
				i++;
			}
		}

		for (Integer order : lstOrder) {
			switch (order) {
			case 0:
				// -------------
				// | 0 | 0 | X |
				// | 0 | 0 | X |
				// | 0 | 0 | X |
				// -------------
				// ���0���Ǵ��������У������Ҳ�Ľ���������չ��һ��
				if (n.c + 1 < n.dim) {
					Data n1 = n.Clone();
					int t = n1.s[n1.r][n1.c + 1];
					n1.s[n1.r][n1.c + 1] = n1._blank;
					n1.s[n1.r][n1.c] = t;
					n1.c = n1.c + 1;
					n1.deep = n.deep + 1;
					n1.parent = n;
					mi.add(n1);
				}
				break;
			case 1:
				// ���0���Ǵ��������У������Ҳ�Ľ���������չ��һ��
				// -------------
				// | X | 0 | 0 |
				// | X | 0 | 0 |
				// | X | 0 | 0 |
				// -------------
				if (n.c != 0) {
					Data n1 = n.Clone();
					int t = n1.s[n1.r][n1.c - 1];
					n1.s[n1.r][n1.c - 1] = n1._blank;
					n1.s[n1.r][n1.c] = t;
					n1.c = n1.c - 1;
					n1.deep = n.deep + 1;
					n1.parent = n;
					mi.add(n1);
				}
				break;
			case 2:
				// ���0���Ǵ����������У��������еĽ���������չ��һ��
				// -------------
				// | X | X | X |
				// | 0 | 0 | 0 |
				// | 0 | 0 | 0 |
				// -------------
				if (n.r != 0) {
					Data n1 = n.Clone();
					int t = n1.s[n1.r - 1][n1.c];
					n1.s[n1.r - 1][n1.c] = n1._blank;
					n1.s[n1.r][n1.c] = t;
					n1.r = n1.r - 1;
					n1.deep = n.deep + 1;
					n1.parent = n;
					mi.add(n1);
				}
				break;
			case 3:
				// ���0���Ǵ����������У��������еĽ���������չ��һ��
				// -------------
				// | 0 | 0 | 0 |
				// | 0 | 0 | 0 |
				// | X | X | X |
				// -------------
				if (n.r + 1 < n.dim) {
					Data n1 = n.Clone();
					int t = n1.s[n1.r + 1][n1.c];
					n1.s[n1.r + 1][n1.c] = n1._blank;
					n1.s[n1.r][n1.c] = t;
					n1.r = n1.r + 1;
					n1.deep = n.deep + 1;
					n1.parent = n;
					mi.add(n1);
				}
				break;
			}
		}
		return mi;
	}

	/**
	 * �ж��Ƿ�ΪĿ��
	 * 
	 * @param d
	 * @return
	 */
	private boolean Goal(Data d) {
		if (d.equals(gloal))
			return true;
		else
			return false;
	}

	public Data getResult() {
		return result;
	}

	public List<Data> getPath() {
		if (result == null)
			return null;
		List<Data> path = new ArrayList<Data>();
		path.add(result);
		Data parent = result.parent;
		while (parent != null) {
			path.add(parent);
			parent = parent.parent;
		}
		return path;
	}
}
