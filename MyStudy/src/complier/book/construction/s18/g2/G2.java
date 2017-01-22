package complier.book.construction.s18.g2;

import java.io.FileNotFoundException;

public class G2 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		boolean debug = true;

		// 构造组成编译器的对象
		// G1TokenMgr tm = new G1TokenMgr(args[0]);
		String regx = "b|c*";
		G2TokenMgr tm = new G2TokenMgr(regx, debug);
		G2Parser parser = new G2Parser(tm);

		// 语法分析和翻译
		try {
			parser.parse();
			System.out.println("OK");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
