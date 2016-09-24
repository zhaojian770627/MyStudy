package complier.book.construction.s10;

import complier.book.construction.Fig0612.ArgsTokenMgr;

/**
 * 
 * @author zhaojianc
 *
 */
public class Fig1004 {

	public static void main(String[] args) {
		ArgsTokenMgr tm = new ArgsTokenMgr(args);
		Fig1004Parser parser = new Fig1004Parser(tm);
		try {
			parser.parse();
		} catch (RuntimeException e) {
			System.err.println();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
