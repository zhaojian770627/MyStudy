package complier.book.construction.s16;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class S5 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("S5 comiler written by zhaojianc");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// 要调试单词符号管理器，设置为true
		boolean debug = false;

		if (args[0].equalsIgnoreCase("-debug_token_manager"))
			debug = true;

		// 构造输入和输出文件名
		String inFileName = args[args.length - 1] + ".s";
		String outFileName = args[args.length - 1] + ".a";

		// 构造文件对象
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(outFileName);

		// 标识编译器/作者到输出文件
		outFile.println("; from S5 compiler written by zhaojianc");

		// 构造组成编译器的对象
		S5SymTab st = new S5SymTab();
		S5TokenMgr tm = new S5TokenMgr(inFile, outFile, debug);
		S5CodeGen cg = new S5CodeGen(outFile, st);
		S5Parser parser = new S5Parser(st, tm, cg);

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
