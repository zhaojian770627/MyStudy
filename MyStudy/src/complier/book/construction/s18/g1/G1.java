package complier.book.construction.s18.g1;

import java.io.FileNotFoundException;

public class G1 {

	public static void main(String[] args) throws FileNotFoundException {
		if (args.length != 1) {
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}

		boolean debug = true;

		// ������ɱ������Ķ���
		// G1TokenMgr tm = new G1TokenMgr(args[0]);
		String regx = "b|c*";
		G1TokenMgr tm = new G1TokenMgr(regx, debug);
		G1Parser parser = new G1Parser(tm);

		// �﷨�����ͷ���
		try {
			parser.parse();
			System.out.println("OK");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
