package parse;

public class TestParse {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		//String a = "IF 2=2 THEN BEGIN PRINT 2=2 END ELSE BEGIN PRINT 1=1 END@";
		String a = "BEGIN PRINT 1=1 END@";
		MyParse p=new MyParse(a);
		p.parse();
	}

}
