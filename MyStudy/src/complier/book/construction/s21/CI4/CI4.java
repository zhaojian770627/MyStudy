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

		// Ҫ���Ե��ʷ��Ź�����������Ϊtrue
		boolean debug = false;

		// �������������ļ���
		String inFileName = args[0] + ".s";

		// �����ļ�����
		Scanner inFile = new Scanner(new File(inFileName));

		// ������ɱ������Ķ���
		CI4SymTab st = new CI4SymTab();
		CI4TokenMgr tm = new CI4TokenMgr(inFile, debug);
		CI4CodeGen cg = new CI4CodeGen();
		CI4Parser parser = new CI4Parser(st, tm, cg);

		// �﷨�����ͷ���
		try {
			parser.parse();
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
