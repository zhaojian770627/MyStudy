package complier.book.construction.s21.CI4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CI4 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("I1 comiler written by zhaojian770627@163.com");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// 要调试单词符号管理器，设置为true
		boolean debug = false;

		// 构造输入和输出文件名
		String inFileName = args[0] + ".s";

		// 构造文件对象
		Scanner inFile = new Scanner(new File(inFileName));

		// 构造组成编译器的对象
		CI4SymTab st = new CI4SymTab();
		CI4TokenMgr tm = new CI4TokenMgr(inFile, debug);
		CI4CodeGen cg = new CI4CodeGen();
		CI4Parser parser = new CI4Parser(st, tm, cg);

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
