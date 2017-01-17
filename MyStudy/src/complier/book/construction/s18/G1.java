package complier.book.construction.s18;

import java.io.FileNotFoundException;

public class G1 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("S5 comiler written by zhaojianc");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// 构造组成编译器的对象
		G1TokenMgr tm = new G1TokenMgr(args[0]);
		G1Parser parser = new G1Parser(tm);

		// 语法分析和翻译
		try {
			parser.parse();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
