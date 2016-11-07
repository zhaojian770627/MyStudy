package complier.book.construction.s12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class S2 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("S3 comiler written by zhaojianc");

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
		outFile.println("; from S3 compiler written by zhaojianc");

		// ������ɱ������Ķ���
		S1SymTab st = new S1SymTab();
		S1TokenMgr tm = new S1TokenMgr(inFile, outFile, debug);
		S1CodeGen cg = new S1CodeGen(outFile, st);
		S2Parser parser = new S2Parser(st, tm, cg);

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
