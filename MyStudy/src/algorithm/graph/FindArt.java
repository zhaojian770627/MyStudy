package algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * ����
 * 
 * 1 ��ͼ����һ���㿪ʼ��ִ����������������ڶ��㱻����ʱ�����Ǳ�ţ����ΪNum(v)
 * 2 ����������������������ϵ�ÿһ������v,��������͵Ķ��㣬��֮ΪLow(v)
 * 	����
 * 		Num(v)
 * 		���б����(v,w)�е����Num(w)
 * 		�������б�(v,w)�е����Low(w)
 * 	����С��
 * @author zhaojian
 *
 */
public class FindArt {
	int counter = 0;
	VexNode[] vns;

	class ArcNode {
		// �����ߵĶ���ı��
		int adjvex;

		// Ȩ��
		int weight;

		// ������
		ArcNode nextArc;

		public ArcNode(int adjvex, int weight) {
			this.adjvex = adjvex;
			this.weight = weight;
		}
	}

	// ����
	class VexNode {
		// ������
		int vertex;
		String name;

		// ָ���һ���ߵ�ָ�룬Ϊ����List��ʾ��,����������
		List<ArcNode> arcs;

		// �����㷨�������ݽṹ
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
		// ����0�Ͷ���1 3����
		vns[0].arcs.add(new ArcNode(1, 0));
		vns[0].arcs.add(new ArcNode(3, 0));

		// ����1�Ͷ���0 2����
		vns[1].arcs.add(new ArcNode(0, 0));
		vns[1].arcs.add(new ArcNode(2, 0));

		// ����2�Ͷ���1 3 6����
		vns[2].arcs.add(new ArcNode(1, 0));
		vns[2].arcs.add(new ArcNode(3, 0));
		vns[2].arcs.add(new ArcNode(6, 0));

		// ����3�Ͷ���4 5����
		vns[3].arcs.add(new ArcNode(0, 0));
		vns[3].arcs.add(new ArcNode(2, 0));
		vns[3].arcs.add(new ArcNode(4, 0));
		vns[3].arcs.add(new ArcNode(5, 0));

		// ����4�Ͷ���3 5����
		vns[4].arcs.add(new ArcNode(3, 0));
		vns[4].arcs.add(new ArcNode(5, 0));

		// ����5�Ͷ���3 4����
		vns[5].arcs.add(new ArcNode(3, 0));
		vns[5].arcs.add(new ArcNode(4, 0));

		// ����5�Ͷ���2����
		vns[6].arcs.add(new ArcNode(2, 0));

	}

	// ����
	void findArt1() {
		// ��ʼ��
		init();

		// ��ȱ���,��0�ڵ㿪ʼ
		assignNum(vns[0]);
		assignLow(vns[0]);
		System.out.println("�]�м���Ը����жϣ�����Ϊ���ʱ���������ж���һ���Ķ���");
		// for (int i = 0; i < vns.length; i++) {
		// System.out.print(i + ": parent:");// + " num:" + vns[i].parent ==
		// // null ? "��"
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
	 * һ��������ʵ��
	 * @param v
	 */
	void findArt(VexNode v) {
		v.visited = true;
		v.low = v.num = counter++;	// Rule 1
		for (ArcNode arc : v.arcs) {
			VexNode w=vns[arc.adjvex];
			if(!w.visited)	// Forward edge ǰ���
			{
				w.parent=v;
				findArt(w);
				if(w.low>=v.num)
					System.out.println(v.vertex+" is an articulation point");
				v.low=Math.min(v.low, w.low);	// Rule 3  ����3
			}else if(v.parent!=w)	// Back edge �����
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
