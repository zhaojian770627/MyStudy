package algorithm.graph;

import java.util.List;

public class Dijkstra {
	private static final int inf = Integer.MAX_VALUE;// 表示两个点之间无法直接连通

	private static final int NOT_A_VERTEX = -1;

	int[][] W = { { 0, 2, inf, 1, inf, inf, inf },
			{ inf, 0, inf, 3, 10, inf, inf }, { 4, inf, 0, inf, inf, 5, inf },
			{ inf, inf, 2, 0, 2, 8, 4 }, { inf, inf, inf, inf, 0, inf, 6 },
			{ inf, inf, inf, inf, inf, 0, inf },
			{ inf, inf, inf, inf, inf, 1, 0 } };

	class Vertex {
		public List adj;
		public boolean known;
		public int dist;
		public Vertex path;
		public int no;

		public Vertex() {

		}

		@Override
		public String toString() {
			return "[" + no + "]";
		}

	}

	public void printPath(Vertex v) {
		if (v.path != null) {
			printPath(v.path);
			System.out.print(" to ");
		}
		System.out.print(v);
	}

	public void dijkstra() {
		Vertex[] vs = new Vertex[7];
		for (int i = 0; i < 7; i++) {
			Vertex v = new Vertex();
			v.dist = inf;
			v.known = false;
			v.no = i;
			vs[i] = v;
		}

		vs[0].dist = 0;
		for (;;) {
			// 寻找距离最短的点，为提高效率可用优先者树
			int vn = 0;
			int min = inf;
			for (int i = 0; i < 7; i++) {
				if (vs[i].known == false) {
					int dis = vs[i].dist;
					if (dis < min) {
						min = dis;
						vn = i;
					}
				}
			}

			// 节点全部搜到
			if (min == inf)
				break;

			vs[vn].known = true;

			// 寻找vn的邻接点
			for (int vw = 0; vw < 7; vw++) {
				if (vw == vn)
					continue;
				// 未知
				if (!vs[vw].known && W[vn][vw] != inf) {
					if (vs[vn].dist + W[vn][vw] < vs[vw].dist) {
						vs[vw].dist = vs[vn].dist + W[vn][vw];
						vs[vw].path = vs[vn];
					}
				}
			}
		}
		for (int i = 0; i < 7; i++) {
			System.out.println(i + ":");
			printPath(vs[i]);
			System.out.println();
		}
	}

	/**
	 * Compute all-shortest paths. W[][] contains the adjacency matrix with
	 * a[i][j] presumed to be zero. d[] contains the values of the shortest
	 * path. Vertices are numbered starting at 0; all arrays have equal
	 * dimension. A negative cycle exists if d[i][j] is set to a negative value.
	 * Actual path can be computed using path[][]. NOT_A_VERTEX is -1
	 */
	public void allPairs() {
		int d[][] = new int[7][7];
		int path[][] = new int[7][7];
		int n = W.length;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				d[i][j] = W[i][j];
				path[i][j] = NOT_A_VERTEX;
			}
		for (int k = 0; k < n; k++)
			// Consider each vertex as an intermediate
			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					if (d[i][k] != inf && d[k][j] != inf
							&& d[i][k] + d[k][j] < d[i][j]) {
						// Update shortest path
						d[i][j] = d[i][k] + d[k][j];
						path[i][j] = k;
					}

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++) {
				System.out.println((i+1) + "->" + (j+1) + " via:" + (path[i][j]+1)
						+ " dis:" + d[i][j]);
			}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Dijkstra dks = new Dijkstra();
		dks.dijkstra();
		dks.allPairs();
	}

}
