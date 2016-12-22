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

		// Ҫ���Ե��ʷ��Ź�����������Ϊtrue
		boolean debug = false;

		if (args[0].equalsIgnoreCase("-debug_token_manager"))
			debug = true;

		// �������������ļ���
		String inFileName = args[args.length - 1] + ".s";
		String outFileName = args[args.length - 1] + ".a";

		// �����ļ�����
		Scanner inFile = new Scanner(new File(inFileName));
		PrintWriter outFile = new PrintWriter(outFileName);

		// ��ʶ������/���ߵ�����ļ�
		outFile.println("; from S5 compiler written by zhaojianc");

		// ������ɱ������Ķ���
		S5SymTab st = new S5SymTab();
		S5TokenMgr tm = new S5TokenMgr(inFile, outFile, debug);
		S5CodeGen cg = new S5CodeGen(outFile, st);
		S5Parser parser = new S5Parser(st, tm, cg);

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
