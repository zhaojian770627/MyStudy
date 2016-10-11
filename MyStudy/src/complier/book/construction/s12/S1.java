package complier.book.construction.s12;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class S1 {

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
		S1SymTab st = new S1SymTab();
		S1TokenMgr tm = new S1TokenMgr(inFile, outFile, debug);
		S1CodeGen cg =new S1CodeGen(outFile, st);
		S1Parser parser = new S1Parser(st, tm, cg);
		
		// �﷨�����ͷ���
		try{
			parser.parse();
		}
		catch(RuntimeException e){
			System.err.println(e.getMessage());
			outFile.println(e.getMessage());
			outFile.close();
			System.exit(1);
		}
		
		outFile.close();
	}

}
