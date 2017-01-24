package complier.book.construction.s18.g2;

import java.io.FileNotFoundException;

public class G2 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		boolean debug = false;

		// 构造组成编译器的对象
		// G1TokenMgr tm = new G1TokenMgr(args[0]);
		String regx = "b|";
		G2TokenMgr tm = new G2TokenMgr(regx, debug);
		G2CodeGen cg = new G2CodeGen();
		G2Parser parser = new G2Parser(tm, cg);

		// 语法分析和翻译
		try {
			NFAState p = parser.parse();
			NFAState.displayNFA(p);
			System.out.println("OK");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
