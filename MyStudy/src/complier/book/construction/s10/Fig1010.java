package complier.book.construction.s10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Fig1010 {

	public static void main(String[] args) throws FileNotFoundException {
		if(args.length!=1)
		{
			System.err.println("Wrong number cmd line args");
			System.exit(1);
		}
		
		Scanner inFile=new Scanner(new File(args[0]));
		Fig1010TokenMgr tm=new Fig1010TokenMgr(inFile);
		Fig1010Parser parser=new Fig1010Parser(tm);
		
		try{
			parser.parse();
		}catch(RuntimeException e)
		{
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
