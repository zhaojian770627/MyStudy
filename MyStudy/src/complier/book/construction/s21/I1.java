package complier.book.construction.s21;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class I1 {

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("S1 comiler written by ...");

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
		outFile.println("; from S1 compiler written by ...");

		// ������ɱ������Ķ���
		I1SymTab st = new I1SymTab();
		I1TokenMgr tm = new I1TokenMgr(inFile, outFile, debug);
		I1CodeGen cg =new I1CodeGen(outFile, st);
		I1Parser parser = new I1Parser(st, tm, cg);
		
		// �﷨�����ͷ���
		try{
			parser.parse();
		}
		catch(RuntimeException e){
			e.printStackTrace();
			System.err.println(e.getMessage());
			outFile.println(e.getMessage());
			outFile.close();
			System.exit(1);
		}
		
		outFile.close();
	}

}
