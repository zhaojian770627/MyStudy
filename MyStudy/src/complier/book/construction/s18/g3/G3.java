package complier.book.construction.s18.g3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class G3 {

	public static void main(String[] args) throws FileNotFoundException {
//		if (args.length != 1) {
//			System.err.println("Wrong number cmd line args");
//			System.exit(1);
//		}

		boolean debug = false;

		// 构造组成编译器的对象
		// G1TokenMgr tm = new G1TokenMgr(args[0]);
		String regx = "abc";
		String inFileName = "D:\\zj\\bookcode\\myprog\\a.txt";

		Scanner inFile = new Scanner(new File(inFileName));
		G3TokenMgr tm = new G3TokenMgr(regx, debug);
		G3CodeGen cg = new G3CodeGen();
		G3Parser parser = new G3Parser(tm, cg);

		// 语法分析和翻译
		try {
			NFAState p = parser.parse();
			NFAState.displayNFA(p);
			G3Matcher m = new G3Matcher(inFile, p);
			m.match();
			System.out.println("OK");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
