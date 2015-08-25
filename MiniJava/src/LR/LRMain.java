package LR;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * LR分析的例子
 * @author zhaojian
 *
 */
public class LRMain {

	String[][] PTable = new String[][] {
			{ "ID", "NUM", "PRINT", ";", ",", "+", ":=", "(", ")", "$", "S",
					"E", "L" },
			// ----------------------------------------------------------------
			/* 1 */{ "s4", "", "s7", "", "", "", "", "", "", "", "g2", "", "" },
			/* 2 */{ "", "", "", "s3", "", "", "", "", "", "a", "", "", "" },
			/* 3 */{ "s4", "", "s7", "", "", "", "", "", "", "", "g5", "", "" },
			/* 4 */{ "", "", "", "", "", "", "s6", "", "", "", "", "", "" },
			/* 5 */{ "", "", "", "r1", "r1", "", "", "", "", "r1", "", "", "" },
			/* 6 */{ "s20", "s10", "", "", "", "", "", "s8", "", "", "", "g11",
					"" },
			/* 7 */{ "", "", "", "", "", "", "", "s9", "", "", "", "", "" },
			/* 8 */{ "s4", "", "s7", "", "", "", "", "", "", "", "g12", "", "" },
			/* 9 */{ "s20", "s10", "", "", "", "", "", "s8", "", "", "", "g15",
					"g14" },
			/* 10 */{ "", "", "", "r5", "r5", "r5", "", "", "r5", "r5", "", "",
					"" },
			/* 11 */{ "", "", "", "r2", "r2", "s16", "", "", "", "r2", "", "",
					"" },
			/* 12 */{ "", "", "", "r3", "s18", "", "", "", "", "", "", "", "" },
			/* 13 */{ "", "", "", "r3", "r3", "", "", "", "", "r3", "", "", "" },
			/* 14 */{ "", "", "", "", "s19", "", "", "", "s13", "", "", "", "" },
			/* 15 */{ "", "", "", "", "r8", "", "", "", "r8", "", "", "", "" },
			/* 16 */{ "s20", "s10", "", "", "", "", "", "s8", "", "", "",
					"g17", "" },
			/* 17 */{ "", "", "", "r6", "r6", "s16", "", "", "r6", "r6", "",
					"", "" },
			/* 18 */{ "s20", "s10", "", "", "", "", "", "s8", "", "", "",
					"g21", "" },
			/* 19 */{ "s20", "s10", "", "", "", "", "", "s8", "", "", "",
					"g23", "" },
			/* 20 */{ "", "", "", "r4", "r4", "r4", "", "", "r4", "r4", "", "",
					"" },
			/* 21 */{ "", "", "", "", "", "", "", "", "s22", "", "", "", "" },
			/* 22 */{ "", "", "", "r7", "r7", "r7", "", "", "r7", "r7", "", "",
					"" },
			/* 23 */{ "", "", "", "", "r9", "s16", "", "", "r9", "", "", "", "" }, };
	// 非终结符
	Symbol S = new Symbol(1, "S", 10);
	Symbol ID = new Symbol(1, "ID", 0);
	Symbol E = new Symbol(1, "E", 11);
	Symbol L = new Symbol(1, "L", 12);

	// 终结符
	Symbol PRINT = new Symbol(0, "PRINT", 2);
	Symbol SETVALUE = new Symbol(0, ":=", 6);
	Symbol LEFTBRACE = new Symbol(0, "(", 7);
	Symbol RIGHTBRACE = new Symbol(0, ")", 8);
	Symbol SEMI = new Symbol(0, ";", 3);
	Symbol COMMA = new Symbol(0, ",", 4);
	Symbol PLUS = new Symbol(0, "+", 5);
	Symbol NUM = new Symbol(0, "NUM", 1);
	Symbol DOLLARS = new Symbol(0, "$", 9);

	// 产生式
	Map<Integer, Production> pdm = new HashMap<Integer, Production>();

	Map<Integer, Symbol> tableindex = new HashMap<Integer, Symbol>();

	Stack<Symbol> stackSymbols = new Stack<Symbol>();
	Stack<Integer> stkState = new Stack<Integer>();

	// 当前状态
	int state = 1;

	MyParse myP;

	void init() {
		pdm.put(1, new Production(S, new Symbol[] { S, SEMI, S }));
		pdm.put(2, new Production(S, new Symbol[] { ID, SETVALUE, E }));
		pdm.put(3, new Production(S, new Symbol[] { PRINT, LEFTBRACE, L,
				RIGHTBRACE }));
		pdm.put(4, new Production(E, new Symbol[] { ID }));
		pdm.put(5, new Production(E, new Symbol[] { NUM }));
		pdm.put(6, new Production(E, new Symbol[] { E, PLUS, E }));
		pdm.put(7, new Production(E, new Symbol[] { LEFTBRACE, S, COMMA, E,
				RIGHTBRACE }));
		pdm.put(8, new Production(L, new Symbol[] { E }));
		pdm.put(9, new Production(L, new Symbol[] { L, COMMA, E }));

		tableindex.put(0, ID);
		tableindex.put(1, NUM);
		tableindex.put(2, PRINT);
		tableindex.put(3, SEMI);
		tableindex.put(4, COMMA);
		tableindex.put(5, PLUS);
		tableindex.put(6, SETVALUE);
		tableindex.put(7, LEFTBRACE);
		tableindex.put(8, RIGHTBRACE);
		tableindex.put(9, DOLLARS);
		tableindex.put(10, S);
		tableindex.put(11, E);
		tableindex.put(12, L);

		state = 1;

		myP = new MyParse("a:=7;b:=c+(d:=5+6,d);PRINT(5)$@");
		//myP = new MyParse("PRINT(5)$@");
		//myP = new MyParse("a:=7;b:=12$@");
	}

	void LR() throws Exception {
		int n;
		int count = 0;
		myP.getNextToken();
		Symbol cs = tableindex.get(myP.getSyn()).clone();
		String g = PTable[1][myP.getSyn()];
		char d = g.charAt(0);
		while (true) {
			switch (d) {
			case 's':
				stackSymbols.push(cs);
				n = Integer.parseInt(g.substring(1));
				stkState.push(n);
				myP.getNextToken();
				cs = tableindex.get(myP.getSyn()).clone();
				g = PTable[stkState.peek()][myP.getSyn()];
				if (g == "")
					error();
				d = g.charAt(0);
				break;
			case 'g':
				n = Integer.parseInt(g.substring(1));
				d = g.charAt(0);
				stackSymbols.push(cs);
				stkState.push(n);
				cs = tableindex.get(myP.getSyn()).clone();
				g = PTable[stkState.peek()][myP.getSyn()];
				if (g == "")
					error();
				d = g.charAt(0);
				break;
			case 'r':
				n = Integer.parseInt(g.substring(1));
				Production p = pdm.get(n);
				for (int i = 0; i < p.getRight().size(); i++) {
					stackSymbols.pop();
					stkState.pop();
				}
				cs = tableindex.get(p.getLeft().getTypeID()).clone();
				if (stkState.size() == 0)
					state = 1;
				else
					state = stkState.peek();
				g = PTable[state][p.getLeft().getTypeID()];
				if (g == "")
					error();
				d = g.charAt(0);
				break;
			case 'a':
				System.out.print("OK");
				return;
			default:
				error();
			}

			int i = 0;
			System.out.print(count + ":");
			for (Symbol s : stackSymbols) {
				int state = stkState.get(i++);
				System.out.print(s.getId() + '-' + state + "  ");
			}
			if (count == 31)
				System.out.println("");
			count++;
			System.out.println();
		}

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		LRMain main = new LRMain();
		main.init();
		main.parse();

		// main.printPtable();

	}

	private void parse() throws Exception {
		LR();
	}

	private void printPtable() {
		for (int i = 0; i < 24; i++) {
			System.out
					.println("------------------------------------------------------------------------------------------------------------");
			System.out.printf("%2d |", i);
			for (int j = 0; j < 13; j++)
				System.out.printf("%6s |", PTable[i][j]);
			System.out.println();
		}
	}

	private void error() throws Exception {
		throw new Exception("Parse error");
	}
}
