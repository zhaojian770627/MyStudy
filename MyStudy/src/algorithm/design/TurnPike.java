package algorithm.design;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Correct Program
 * @author zhaojian
 *
 */
public class TurnPike {
	class IntegerComparator implements Comparator<Integer> {
		@Override
		public int compare(Integer i1, Integer i2) {
			return i2 - i1;
		}
	}

	int[] x = new int[10];

	boolean execute() {
		int[] a = { 1, 2, 2, 2, 3, 3, 3, 4, 5, 5, 5, 6, 7, 8, 10 };
		Queue<Integer> d = new PriorityQueue<Integer>(14,
				new IntegerComparator());
		int n = 6;
		for (int i : a) {
			d.add(i);
		}

		x[1] = 0;
		x[n] = d.poll();
		x[n - 1] = d.poll();
		if (d.contains(x[n] - x[n - 1])) {
			d.remove(x[n] - x[n - 1]);
			return place(x, d, n, 2, n - 2);
		} else
			return false;
	}

	/**
	 * Backtracking algorithm to place the points x[left] ... x[right].
	 * x[1]...x[left-1] and x[right+1]...x[n] already tentatively placed. If
	 * place returns true,then x[left]...x[right] will have values.
	 * 
	 * @param x
	 * @param d
	 * @param n
	 * @param left
	 * @param right
	 * @return
	 */
	private boolean place(int[] x, Queue<Integer> d, int n, int left, int right) {
		int dmax;
		boolean found = false;
		if (d.isEmpty())
			return true;
		if (right < left)
			return false;
		dmax = d.peek();
		int j = 1;
		// Check if setting x[right]=dmax is feasible

		List<Integer> tmpDis = new ArrayList<Integer>();
		boolean isPlaced = true;
		while (j >= 1 && j <= n) {
			if (!((j >= 1 && j < left) || (j > right && j <= n))) {
				j++;
				continue;
			}
			int dis = Math.abs(x[j] - dmax);
			if (!d.contains(dis)) {
				isPlaced = false;
				break;
			}
			tmpDis.add(dis);
			d.remove(dis);
			j++;
		}

		if (!isPlaced) {
			for (int i : tmpDis)
				d.add(i);
			return false;
		}
		x[right] = dmax;
		found = place(x, d, n, left, right - 1);
		if (!found) {
			for (int i : tmpDis)
				d.add(i);
		}

		// If first attemp failed,try to see if setting
		// x[left]=x[n]-dmax is feasible.
		tmpDis.clear();
		isPlaced = true;
		j=1;
		if (!found) {
			while (j >= 1 && j <= n) {
				if (!((j >= 1 && j < left) || (j > right && j <= n))) {
					j++;
					continue;
				}
				int dis = Math.abs(x[n] - dmax - x[j]);
				if (!d.contains(dis)) {
					isPlaced = false;
					break;
				}
				tmpDis.add(dis);
				d.remove(dis);
				j++;
			}
			if (!isPlaced) {
				for (int i : tmpDis)
					d.add(i);
				return false;
			}

			x[left] = x[n] - dmax;
			found = place(x, d, n, left + 1, right);
			if (!found)
				for (int i : tmpDis)
					d.add(i);

		}
		return found;
	}

	private void printX() {
		for (int i=1;i<=6;i++) {
			System.out.println(x[i]);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TurnPike tp = new TurnPike();
		boolean b = tp.execute();
		System.out.println(b);
		tp.printX();
	}

}
