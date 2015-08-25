package LRGenerate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class LRGenerateMain {
	// 非终结符
	Symbol SS = new Symbol(1, "SS", 5);
	Symbol S = new Symbol(1, "S", 6);
	Symbol L = new Symbol(1, "L", 7);

	// 终结符
	Symbol LEFTBRACE = new Symbol(0, "(", 0);
	Symbol RIGHTBRACE = new Symbol(0, ")", 1);
	Symbol COMMA = new Symbol(0, ",", 2);
	Symbol DOLLARS = new Symbol(0, "$", 3);
	Symbol x = new Symbol(0, "x", 4);

	Symbol[] ss = new Symbol[] { LEFTBRACE, RIGHTBRACE, COMMA, DOLLARS, x, SS,
			S, L };

	// 产生式
	Map<Integer, Production> pdm = new HashMap<Integer, Production>();

	Production p0 = new Production(SS, new Symbol[] { S, DOLLARS }, 0);
	Production p1 = new Production(S,
			new Symbol[] { LEFTBRACE, L, RIGHTBRACE }, 1);
	Production p2 = new Production(S, new Symbol[] { x }, 2);
	Production p3 = new Production(L, new Symbol[] { S }, 3);
	Production p4 = new Production(L, new Symbol[] { L, COMMA, S }, 4);

	Production[] aryProductions = new Production[] { p0, p1, p2, p3, p4 };

	LRState Goto(LRState state, Symbol s) {
		List<LRProduction> lstrp = new ArrayList<LRProduction>();
		for (LRProduction p : state.getLstrp()) {
			if (p.getDotPos() != p.getProduction().getRight().size()
					&& p.getProduction().getRight().get(p.getDotPos()).getId()
							.equals(s.getId())) {
				int dotpos = p.getDotPos() + 1;
				LRProduction pt = new LRProduction(p.getProduction(), dotpos);
				lstrp.add(pt);
			}
		}
		LRState stateJ = new LRState();
		stateJ.setLstrp(lstrp);
		Closure(stateJ);
		return stateJ;
	}

	void Closure(LRState lrst) {
		Map<String, String> map = new HashMap<String, String>();
		Boolean repeat = false;
		for (LRProduction lp : lrst.getLstrp()) {
			map.put(lp.getKey(), lp.getKey());
		}

		do {
			repeat = false;
			int n1 = map.size();
			List<LRProduction> lstt = new ArrayList<LRProduction>();
			lstt.addAll(lrst.getLstrp());
			for (LRProduction lp : lrst.getLstrp()) {
				if (lp.getProduction().getRight().size() == lp.getDotPos())
					break;
				Symbol s = lp.getProduction().getRight().get(lp.getDotPos());
				for (Production p : aryProductions) {
					if (p.getLeft().getId().equals(s.getId())) {
						LRProduction lpt = new LRProduction(p, 0);
						if (!map.containsKey(lpt.getKey())) {
							map.put(lpt.getKey(), lpt.getKey());
							lstt.add(lpt);
						}
					}
				}
			}
			lrst.getLstrp().clear();
			lrst.getLstrp().addAll(lstt);
			int n2 = map.size();
			if (n1 != n2)
				repeat = true;
		} while (repeat);

		// --计算key
		String key = "";
		for (Entry<String, String> entry : map.entrySet()) {
			key = key + "@" + entry.getKey();
		}
		lrst.setKey(key);
	}

	void solve() {
		List<LRProduction> lstP = new ArrayList<LRProduction>();
		lstP.add(new LRProduction(p0, 0));

		LRState lrs = new LRState();
		lrs.setLstrp(lstP);

		Closure(lrs);
		for (LRProduction lp : lrs.getLstrp()) {
			System.out.println();
			lp.print();
		}

		System.out.println();
		System.out.println(lrs.getKey());

		LRState lrp = Goto(lrs, x);
		for (LRProduction lp : lrp.getLstrp()) {
			System.out.println();
			lp.print();
		}

		System.out.println();
		System.out.println(lrp.getKey());
	}

	void generate() {
		List<LRProduction> lstP = new ArrayList<LRProduction>();
		lstP.add(new LRProduction(p0, 0));

		LRState lrs = new LRState();
		lrs.setLstrp(lstP);
		Closure(lrs);

		// 状态集合
		Map<String, LRState> IMap = new HashMap<String, LRState>();
		Map<String, LRTranslate> EMap = new HashMap<String, LRTranslate>();
		// 状态对照表
		Map<String, Integer> IMapTable = new HashMap<String, Integer>();
		int stateIndex = 0;

		IMap.put(lrs.getKey(), lrs);
		IMapTable.put(lrs.getKey(), stateIndex++);
		boolean repeat = false;
		do {
			repeat = false;
			int n1 = IMap.size();
			Map<String, LRState> IMapT = new HashMap<String, LRState>();
			IMapT.putAll(IMap);
			for (Entry<String, LRState> entry : IMapT.entrySet()) {
				LRState lrst = entry.getValue();
				for (LRProduction lrp : lrst.getLstrp()) {
					if (lrp.getDotPos() != lrp.getProduction().getRight()
							.size()) {
						Symbol s = lrp.getProduction().getRight()
								.get(lrp.getDotPos());
						if (s.getId().equals("$"))
							break;

						LRState lrsJ = Goto(lrst, s);
						if (!IMap.containsKey(lrsJ.getKey())) {
							IMap.put(lrsJ.getKey(), lrsJ);
							IMapTable.put(lrsJ.getKey(), stateIndex++);
						}

						LRTranslate lr = new LRTranslate(lrst, s, lrsJ);
						if (!EMap.containsKey(lr.getkey()))
							EMap.put(lr.getkey(), lr);
					}
				}
			}
			int n2 = IMap.size();
			if (n1 != n2)
				repeat = true;
		} while (repeat);

		String[][] PTable = new String[IMapTable.size()][8];
		for (Entry<String, LRTranslate> entry : EMap.entrySet()) {
			LRTranslate tran = entry.getValue();
			// 终结符
			if (tran.getTranSymbol().getType() == 0) {
				PTable[IMapTable.get(tran.getSourceState().getKey())][tran
						.getTranSymbol().getTypeID()] = "s"
						+ IMapTable.get(tran.getTargetState().getKey());
			}
			// 终结符
			else if (tran.getTranSymbol().getType() == 1) {
				PTable[IMapTable.get(tran.getSourceState().getKey())][tran
						.getTranSymbol().getTypeID()] = "g"
						+ IMapTable.get(tran.getTargetState().getKey());
			}
		}

		for (Entry<String, LRState> entry : IMap.entrySet()) {
			LRState lrst = entry.getValue();
			for (LRProduction lrp : lrst.getLstrp()) {
				if (lrp.getDotPos() != lrp.getProduction().getRight().size()) {
					if (lrp.getProduction().getRight().get(lrp.getDotPos())
							.getId().equals(DOLLARS.getId())) {
						PTable[IMapTable.get(lrst.getKey())][DOLLARS
								.getTypeID()] = "a";
						break;
					}
				} else {
					for (int i = 0; i < 5; i++)
						PTable[IMapTable.get(lrst.getKey())][i] = "r"
								+ lrp.getProduction().getKey();
					break;
				}

			}
		}

		// 打印IMap
		// IMap
		for (Entry<String, LRState> entry : IMap.entrySet()) {
			LRState s = entry.getValue();
			System.out.println(IMapTable.get(s.getKey()) + ":" + s.getKey());
			for (LRProduction lp : s.getLstrp()) {
				lp.print();
				System.out.println();
			}
			System.out.println();
		}

		for (Entry<String, LRTranslate> entry : EMap.entrySet()) {
			LRTranslate t = entry.getValue();
			System.out.println(IMapTable.get(t.getSourceState().getKey())
					+ "--" + t.getTranSymbol().getId() + "-->"
					+ IMapTable.get(t.getTargetState().getKey()));
		}

		// --------------------打印转换表--------------------------------
		System.out.printf("%6s |", "State");
		for (Symbol s : ss)
			System.out.printf("%6s |", s.getId());
		System.out.println();
		for (int i = 0; i < PTable.length; i++) {
			System.out.printf("%6s |", i);
			for (int j = 0; j < PTable[i].length; j++) {
				String str = PTable[i][j];
				if (str == null)
					str = " ";
				System.out.printf("%6s |", str);
			}
			System.out.println();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LRGenerateMain main = new LRGenerateMain();
		main.generate();
	}

}
