package complier.book.construction.Fig0612;

/*
 * 自上而下栈式语法分析器
 * 1) S->bScA
 * 2) S->cbd
 * 3) A->bcA
 * 4) A->d
 */
public class Fig0612 {

	public static void main(String[] args) {
		// 创建单词符号管理器
		ArgsTokenMgr tm=new ArgsTokenMgr(new String[]{"bcbdcd"});
		// 创建语法分析器，传递给它单词符号管理器
		Fig0612Parser parser=new Fig0612Parser(tm);
		// 进行语法分析
		parser.parse();
	}

}
