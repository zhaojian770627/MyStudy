package algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查找强分支
 * 
 * 参看原图和逆序图
 * @author zhaojian
 * 
 */
public class FindBrance {
	VexNode[] vns;
	VexNode[] vnsR;
	int counter = 1;

	class ArcNode {
		// 下条边的顶点的编号
		VexNode toV;

		// 下条边
		ArcNode nextArc;

		public ArcNode(VexNode toV, int weight) {
			this.toV = toV;
		}
	}

	// 顶点
	class VexNode {
		String code;

		// 指向第一条边的指针，为简单用List表示边,不用链表了
		List<ArcNode> arcs;

		VexNode(String code) {
			this.code = code;
			arcs = new ArrayList<ArcNode>();
		}

		public void addArc(ArcNode an) {
			arcs.add(an);
		}

		// 本次算法所需数据结构
		boolean visited = false;
		int num = -1, low;
		VexNode parent;
	}

	void cpyAndReserve() {
		Map<String, VexNode> vexmap = new HashMap<String, VexNode>();
		VexNode A = new VexNode("A");
		VexNode B = new VexNode("B");
		VexNode C = new VexNode("C");
		VexNode D = new VexNode("D");
		VexNode E = new VexNode("E");
		VexNode F = new VexNode("F");
		VexNode G = new VexNode("G");
		VexNode H = new VexNode("H");
		VexNode I = new VexNode("I");
		VexNode J = new VexNode("J");
		vnsR = new VexNode[] { A, B, C, D, E, F, G, H, I, J };
		for (VexNode v : vnsR)
			vexmap.put(v.code, v);

		// 构建新图，并依序
		for (int i = 0; i < vns.length; i++) {
			vnsR[i].num = vns[i].num;
			for (ArcNode an : vns[i].arcs) {
				vexmap.get(an.toV.code).addArc(
						new ArcNode(vexmap.get(vns[i].code), 0));
			}
		}
	}

	void init() {
		VexNode A = new VexNode("A");
		VexNode B = new VexNode("B");
		VexNode C = new VexNode("C");
		VexNode D = new VexNode("D");
		VexNode E = new VexNode("E");
		VexNode F = new VexNode("F");
		VexNode G = new VexNode("G");
		VexNode H = new VexNode("H");
		VexNode I = new VexNode("I");
		VexNode J = new VexNode("J");
		vns = new VexNode[] { A, B, C, D, E, F, G, H, I, J };

		// A->B A->D
		A.addArc(new ArcNode(B, 0));
		A.addArc(new ArcNode(D, 0));

		// B->C B->F
		B.addArc(new ArcNode(C, 0));
		B.addArc(new ArcNode(F, 0));

		// C->A D E
		C.addArc(new ArcNode(A, 0));
		C.addArc(new ArcNode(D, 0));
		C.addArc(new ArcNode(E, 0));

		// D->E
		D.addArc(new ArcNode(E, 0));

		// F->C
		F.addArc(new ArcNode(C, 0));

		// G->F H
		G.addArc(new ArcNode(F, 0));
		G.addArc(new ArcNode(H, 0));

		// H->F J
		H.addArc(new ArcNode(F, 0));
		H.addArc(new ArcNode(J, 0));

		// I->H
		I.addArc(new ArcNode(H, 0));

		// J->i
		J.addArc(new ArcNode(I, 0));
	}

	void execute() {
		init();
		VexNode v = findUnVisited(vns);
		while (v != null) {
			dfs(v);
			v = findUnVisited(vns);
		}
		//printGraph(vns);
		cpyAndReserve();
		v = findMaxUnVisited(vnsR);
		while (v != null) {
			List<VexNode> lstV = new ArrayList<FindBrance.VexNode>();
			dfsBrance(v, lstV);
			for (VexNode vn : lstV) {
				System.out.print(vn.code + " ");
			}
			System.out.println();
			v = findMaxUnVisited(vnsR);
		}
	}

	private void printGraph(VexNode[] vns) {
		for (VexNode v : vns) {
			System.out.println(v.code + " " + v.num);
		}
	}

	// 寻找未访问的节点
	VexNode findUnVisited(VexNode[] vns) {
		for (VexNode v : vns) {
			if (!v.visited)
				return v;
		}
		return null;
	}

	// 寻找未访问的节点
	VexNode findMaxUnVisited(VexNode[] vns) {
		int max = 0;
		VexNode maxV = null;
		for (VexNode v : vns) {
			if (!v.visited) {
				if (v.num > max) {
					max = v.num;
					maxV = v;
				}
			}
		}
		return maxV;
	}

	private void dfs(VexNode v) {
		if (v == null)
			return;
		v.visited = true;
		for (ArcNode an : v.arcs) {
			if (!an.toV.visited)
				dfs(an.toV);
		}
		v.num = counter++;
	}

	private void dfsBrance(VexNode v, List<VexNode> lstV) {
		if (v == null)
			return;
		v.visited = true;
		lstV.add(v);
		for (ArcNode an : v.arcs) {
			if (!an.toV.visited)
				dfsBrance(an.toV, lstV);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FindBrance fb = new FindBrance();
		fb.execute();
	}

}
