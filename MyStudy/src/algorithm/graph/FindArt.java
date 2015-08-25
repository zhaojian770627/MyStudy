package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * 求割点
 * 
 * 1 从图中任一顶点开始，执行深度优先搜索并在顶点被访问时给他们编号，编号为Num(v)
 * 2 对于深度优先搜索生成树上的每一个顶点v,计算编号最低的顶点，称之为Low(v)
 * 	规则：
 * 		Num(v)
 * 		所有背向边(v,w)中的最低Num(w)
 * 		树的所有边(v,w)中的最低Low(w)
 * 	的最小者
 * @author zhaojian
 *
 */
public class FindArt {
	int counter = 0;
	VexNode[] vns;

	class ArcNode {
		// 下条边的顶点的编号
		int adjvex;

		// 权重
		int weight;

		// 下条边
		ArcNode nextArc;

		public ArcNode(int adjvex, int weight) {
			this.adjvex = adjvex;
			this.weight = weight;
		}
	}

	// 顶点
	class VexNode {
		// 顶点编号
		int vertex;
		String name;

		// 指向第一条边的指针，为简单用List表示边,不用链表了
		List<ArcNode> arcs;

		// 本次算法所需数据结构
		boolean visited = false;
		int num = -1, low;
		VexNode parent;
	}

	void init() {
		vns = new VexNode[7];
		for (int i = 0; i < 7; i++) {
			VexNode v = new VexNode();
			v.vertex = i;
			v.arcs = new ArrayList<ArcNode>();
			vns[i] = v;
		}
		// 顶点0和顶点1 3相连
		vns[0].arcs.add(new ArcNode(1, 0));
		vns[0].arcs.add(new ArcNode(3, 0));

		// 顶点1和顶点0 2相连
		vns[1].arcs.add(new ArcNode(0, 0));
		vns[1].arcs.add(new ArcNode(2, 0));

		// 顶点2和顶点1 3 6相连
		vns[2].arcs.add(new ArcNode(1, 0));
		vns[2].arcs.add(new ArcNode(3, 0));
		vns[2].arcs.add(new ArcNode(6, 0));

		// 顶点3和顶点4 5相连
		vns[3].arcs.add(new ArcNode(0, 0));
		vns[3].arcs.add(new ArcNode(2, 0));
		vns[3].arcs.add(new ArcNode(4, 0));
		vns[3].arcs.add(new ArcNode(5, 0));

		// 顶点4和顶点3 5相连
		vns[4].arcs.add(new ArcNode(3, 0));
		vns[4].arcs.add(new ArcNode(5, 0));

		// 顶点5和顶点3 4相连
		vns[5].arcs.add(new ArcNode(3, 0));
		vns[5].arcs.add(new ArcNode(4, 0));

		// 顶点5和顶点2相连
		vns[6].arcs.add(new ArcNode(2, 0));

	}

	// 求割点
	void findArt1() {
		// 初始化
		init();

		// 深度遍历,从0节点开始
		assignNum(vns[0]);
		assignLow(vns[0]);
		System.out.println("沒有加入对根的判断，当根为割点时，仅当它有多于一个的儿子");
		// for (int i = 0; i < vns.length; i++) {
		// System.out.print(i + ": parent:");// + " num:" + vns[i].parent ==
		// // null ? "根"
		// // : vns[i].parent.num);
		// if (vns[i].parent != null) {
		// System.out.print(vns[i].parent.vertex);
		// System.out.print(" num:" + vns[i].num);
		// }
		// System.out.println();
		// }
	}

	void findArt2() {
		init();
		findArt(vns[0]);
	}

	/**
	 * 一个函数中实现
	 * @param v
	 */
	void findArt(VexNode v) {
		v.visited = true;
		v.low = v.num = counter++;	// Rule 1
		for (ArcNode arc : v.arcs) {
			VexNode w=vns[arc.adjvex];
			if(!w.visited)	// Forward edge 前向边
			{
				w.parent=v;
				findArt(w);
				if(w.low>=v.num)
					System.out.println(v.vertex+" is an articulation point");
				v.low=Math.min(v.low, w.low);	// Rule 3  规则3
			}else if(v.parent!=w)	// Back edge 背向边
				v.low=Math.min(v.low, w.num);
		}
	}

	/*
	 * Assign Num and compute parents
	 */
	void assignNum(VexNode v) {
		v.num = counter++;
		v.visited = true;
		for (ArcNode arc : v.arcs) {
			if (!vns[arc.adjvex].visited) {
				vns[arc.adjvex].parent = v;
				assignNum(vns[arc.adjvex]);
			}
		}
	}

	/*
	 * Assign low:also check for articulation points
	 */
	void assignLow(VexNode v) {
		v.low = v.num;	// Rule 1
		for (ArcNode arc : v.arcs) {
			VexNode w = vns[arc.adjvex];
			if (w.num > v.num) {	// Forward edge
				assignLow(w);
				if (w.low >= v.num)
					System.out.println(v.vertex + " is an articulation point");
				v.low = Math.min(v.low, w.low);
			} else if (v.parent != w)
				v.low = Math.min(v.low, w.num);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindArt fa = new FindArt();
		System.out.println("FindArt1");
		fa.findArt1();
		System.out.println("FindArt2");
		fa.findArt2();
	}

}
