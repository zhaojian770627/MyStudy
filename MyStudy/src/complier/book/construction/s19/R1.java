package complier.book.construction.s19;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class R1 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("S1 comiler written by zhaojian770627@163.com");

		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		// Ҫ���Ե��ʷ��Ź�����������Ϊtrue
		boolean debug = false;

		// �������������ļ���
		String inFileName = args[0] + ".s";
		String outFileName = args[0] + ".a";

		// �����ļ�����
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(outFileName);

		// ��ʶ������/���ߵ�����ļ�
		outFile.println("; from S1 compiler written by zhaojian770627@163.com");
		outFile.println("!register");

		// ������ɱ������Ķ���
		R1SymTab st = new R1SymTab();
		R1TokenMgr tm = new R1TokenMgr(inFile, outFile, debug);
		R1CodeGen cg = new R1CodeGen(outFile, st);
		R1Parser parser = new R1Parser(st, tm, cg);

		// �﷨�����ͷ���
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
