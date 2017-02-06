package complier.book.construction.s19.R2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class R2 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("R2 comiler written by zhaojian770627@163.com");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// 要调试单词符号管理器，设置为true
		boolean debug = false;

		// 构造输入和输出文件名
		String inFileName = args[0] + ".s";
		String outFileName = args[0] + ".a";

		// 构造文件对象
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(outFileName);

		// 标识编译器/作者到输出文件
		outFile.println("; from R2 compiler written by zhaojian770627@163.com");
		outFile.println("!register");

		// 构造组成编译器的对象
		R2SymTab st = new R2SymTab();
		R2TokenMgr tm = new R2TokenMgr(inFile, outFile, debug);
		R2CodeGen cg = new R2CodeGen(outFile, st);
		R2Parser parser = new R2Parser(st, tm, cg);

		// 语法分析和翻译
		try {
			parser.parse();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			outFile.println(e.getMessage());
			outFile.close();
			System.exit(1);
		}

		outFile.close();
	}

}
