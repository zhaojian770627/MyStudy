package complier.book.construction.s21.CI1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CI1 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("I1 comiler written by zhaojian770627@163.com");

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

		// ������ɱ������Ķ���
		CI1SymTab st = new CI1SymTab();
		CI1TokenMgr tm = new CI1TokenMgr(inFile, outFile, debug);
		CI1CodeGen cg = new CI1CodeGen();
		CI1Parser parser = new CI1Parser(st, tm, cg);

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
