package complier.book.construction.s10;

import complier.book.construction.Fig0612.ArgsTokenMgr;

/**
 * ��Ӧ�����з����ķ��ĵݹ��½��﷨������/������
 * void S()	      							ѡ�񼯺�
 * {
 * 		"b" C() "d"							{"b"}
 * }
 * 
 * void C():{}
 * {
 * 		"c" {System.out.println('c'); C()}	{"c"}
 * 		|
 * 		{}									{"d"}
 * }
 * @author zhaojianc
 *
 */
public class Fig1001 {

	public static void main(String[] args) {
		ArgsTokenMgr tm=new ArgsTokenMgr(args);
		Fig1001Parser parser=new Fig1001Parser(tm);
		try{
			parser.parse();
		}catch(RuntimeException e)
		{
			System.err.println();
			System.err.println(e.getMessage());
			System.exit(1);
		}
	}

}
