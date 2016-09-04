package complier.book.construction.s9;

import complier.book.construction.Fig0612.ArgsTokenMgr;

/*
 * µÝ¹éÏÂ½µ·ÖÎöÆ÷
 * 
 * 1) S-> BD	{b,c}
 * 2) B-> bB	{b}
 * 3) B-> c		{c}
 * 4) D->de		{d}
 */
public class Fig0901 {

	public static void main(String[] args) {
		ArgsTokenMgr tm=new ArgsTokenMgr(args);
		Fig0901Parser parser=new Fig0901Parser(tm);
		
		try
		{
			parser.parse();
		}catch(RuntimeException e)
		{
			System.err.println(e.getMessage());
			System.err.println("reject");
			System.exit(1);
		}
	}

}
