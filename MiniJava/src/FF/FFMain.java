package FF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FFMain {
	List<Production> pds = new ArrayList<Production>();

	// 符号声明
	// 非终结符
	Symbol X = new Symbol(1, "X");
	Symbol Y = new Symbol(1, "Y");
	Symbol Z = new Symbol(1, "Z");

	// 终结符
	Symbol a = new Symbol(0, "a");
	Symbol c = new Symbol(0, "c");
	Symbol d = new Symbol(0, "d");

	Map<String, Boolean> nullmap = new HashMap<String, Boolean>();
	Map<String, Map<String, Symbol>> firstMap = new HashMap<String, Map<String, Symbol>>();
	Map<String, Map<String, Symbol>> followMap = new HashMap<String, Map<String, Symbol>>();

	public void init() {
		// 产生式
		// Z-->d
		pds.add(new Production(Z, new Symbol[] { d }));
		// Y-->
		pds.add(new Production(Y, new Symbol[] {}));
		// X-->Y
		pds.add(new Production(X, new Symbol[] { Y }));
		// Z->XYZ
		pds.add(new Production(Z, new Symbol[] { X, Y, Z }));
		// Y-->c
		pds.add(new Production(Y, new Symbol[] { c }));
		// X-->a
		pds.add(new Production(X, new Symbol[] { a }));

		firstMap.put(X.getId(), new HashMap<String, Symbol>());
		firstMap.put(Y.getId(), new HashMap<String, Symbol>());
		firstMap.put(Z.getId(), new HashMap<String, Symbol>());

		followMap.put(X.getId(), new HashMap<String, Symbol>());
		followMap.put(Y.getId(), new HashMap<String, Symbol>());
		followMap.put(Z.getId(), new HashMap<String, Symbol>());

		firstMap.put(a.getId(), new HashMap<String, Symbol>());
		firstMap.put(c.getId(), new HashMap<String, Symbol>());
		firstMap.put(d.getId(), new HashMap<String, Symbol>());

		followMap.put(a.getId(), new HashMap<String, Symbol>());
		followMap.put(c.getId(), new HashMap<String, Symbol>());
		followMap.put(d.getId(), new HashMap<String, Symbol>());

		firstMap.get(a.getId()).put(a.getId(), a);
		firstMap.get(c.getId()).put(c.getId(), c);
		firstMap.get(d.getId()).put(d.getId(), d);

		nullmap.put(X.id, false);
		nullmap.put(Y.id, false);
		nullmap.put(Z.id, false);

	}

	public void solve() {
		Boolean repeat = false;
		int count = 0;
		int n1, n2;
		do {
			count++;
			System.out.println("迭代:" + count);
			repeat = false;
			if (count == 3)
				System.out.println("");

			for (Production p : pds) {

				System.out.print(p.getLeft().getId() + "-->");
				for (Symbol s : p.getRight())
					System.out.print(s.getId());
				System.out.println();

				// ------------------------------------
				if (p.getRight().size() == 0) {
					n1 = nullmap.size();
					nullmap.put(p.getLeft().getId(), true);
					n2 = nullmap.size();
					if (n1 != n2)
						repeat = true;
					continue;
				} else {
					boolean isnullable = true;
					for (Symbol s : p.getRight()) {
						if (s.getType() == 0) {
							isnullable = false;
							break;
						} else {
							if (nullmap.get(s.id))
								continue;
							else {
								isnullable = false;
								break;
							}
						}
					}
					if (isnullable) {
						n1 = nullmap.size();
						nullmap.put(p.getLeft().getId(), true);
						n2 = nullmap.size();
						if (n1 != n2)
							repeat = true;
					}
				}
				// --------------------------------------------
				for (int i = 0; i < p.getRight().size(); i++) {
					if (i == 0
							&& !p.getLeft().getId().equals(p.getRight().get(i))) {
						n1 = firstMap.get(p.getLeft().getId()).size();
						firstMap.get(p.getLeft().getId()).putAll(
								firstMap.get(p.getRight().get(0).getId()));
						n2 = firstMap.get(p.getLeft().getId()).size();
						if (n1 != n2)
							repeat = true;

					} else {
						boolean isnullable = true;
						for (int k = 0; k < i - 1; k++) {
							Symbol s = p.getRight().get(k);
							if (s.getType() == 0) {
								isnullable = false;
								break;
							} else {
								if (nullmap.get(s.id))
									continue;
								else {
									isnullable = false;
									break;
								}
							}
						}
						if (isnullable
								&& !p.getLeft().getId()
										.equals(p.getRight().get(i))) {
							n1 = firstMap.get(p.getLeft().getId()).size();
							firstMap.get(p.getLeft().getId()).putAll(
									firstMap.get(p.getRight().get(i).getId()));
							n2 = firstMap.get(p.getLeft().getId()).size();
							if (n1 != n2)
								repeat = true;
						}
					}
					// ----------------------------------------------

					if (i == p.getRight().size() - 1
							&& !p.getRight().get(i).getId()
									.equals(p.getLeft().getId())) {
						n1 = followMap.get(p.getRight().get(i).getId()).size();
						followMap.get(p.getRight().get(i).getId()).putAll(
								followMap.get(p.getLeft().getId()));
						n2 = followMap.get(p.getRight().get(i).getId()).size();
						if (n1 != n2)
							repeat = true;
					} else {
						boolean isnullable = true;
						for (int k = i + 1; k < p.getRight().size(); k++) {
							Symbol s = p.getRight().get(k);
							if (s.getType() == 0) {
								isnullable = false;
								break;
							} else {
								if (nullmap.get(s.id))
									continue;
								else {
									isnullable = false;
									break;
								}
							}
						}

						if (isnullable
								&& !p.getRight().get(i).getId()
										.equals(p.getLeft().getId())) {
							n1 = followMap.get(p.getRight().get(i).getId())
									.size();
							followMap.get(p.getRight().get(i).getId()).putAll(
									followMap.get(p.getLeft().getId()));
							n2 = followMap.get(p.getRight().get(i).getId())
									.size();
							if (n1 != n2)
								repeat = true;
						}
					}
					// ----------------------------------------------------------

					for (int j = i + 1; j < p.getRight().size(); j++) {
						if (i == 0 && j == 2 && count == 3
								&& p.getLeft().getId().equals("Z"))
							System.out.println("123");
						// --------------------------------------------------
						if (i + 1 == j) {
							n1 = followMap.get(p.getRight().get(i).getId())
									.size();
							followMap.get(p.getRight().get(i).getId()).putAll(
									firstMap.get(p.getRight().get(j).getId()));
							n2 = followMap.get(p.getRight().get(i).getId())
									.size();
							if (n1 != n2)
								repeat = true;
						} else {
							boolean isnullable = true;
							for (int k = i + 1; k <= j - 1; k++) {
								Symbol s = p.getRight().get(k);
								if (s.getType() == 0) {
									isnullable = false;
									break;
								} else {
									if (nullmap.get(s.id))
										continue;
									else {
										isnullable = false;
										break;
									}
								}
							}

							if (isnullable) {
								n1 = followMap.get(p.getRight().get(i).getId())
										.size();
								followMap.get(p.getRight().get(i).getId())
										.putAll(firstMap.get(p.getRight()
												.get(j).getId()));
								n2 = followMap.get(p.getRight().get(i).getId())
										.size();

								if (n1 != n2)
									repeat = true;
							}
						}
					}
				}

			}
			print();
		} while (repeat);
	}

	private void print() {
		System.out
				.println("|---------------------------------------------------|");
		System.out
				.println("|     |nullable|    FIRST         |      FOLLOW     |");
		System.out
				.println("|---------------------------------------------------|");
		System.out.println("|  X  |" + nullmap.get(X.getId()) + " | "
				+ lst2String(firstMap.get(X.getId())) + "|"
				+ lst2String(followMap.get(X.getId())) + "|");
		System.out
				.println("|---------------------------------------------------|");
		System.out.println("|  Y  |" + nullmap.get(Y.getId()) + " | "
				+ lst2String(firstMap.get(Y.getId())) + "|"
				+ lst2String(followMap.get(Y.getId())) + "|");
		System.out
				.println("|---------------------------------------------------|");
		System.out.println("|  Z  |" + nullmap.get(Z.getId()) + " | "
				+ lst2String(firstMap.get(Z.getId())) + "|"
				+ lst2String(followMap.get(Z.getId())) + "|");
		System.out
				.println("|---------------------------------------------------|");
	}

	private String lst2String(Map<String, Symbol> ls) {
		String r = "";
		for (Entry<String, Symbol> s : ls.entrySet()) {
			r = r + "," + s.getKey();
		}
		if (r.length() > 1)
			r = r.substring(1);
		return r;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FFMain m = new FFMain();
		m.init();
		m.solve();
		m.print();
	}

}
