package complier.book.construction.s10;

public interface Fig1010Constants {
	// 标示单词符号类别的整数
	public int EOF=0;
	public int UNSIGNED=1;
	public int PLUS=2;
	public int MINUS=3;
	public int TIMES=4;
	public int DIVIDE=5;
	public int ERROR=6;
	
	// tokenImage 为每类单词符号提供字符串映像
	String[] tokenImage={
		"<EOF>",
		"<UNSIGNED>",
		"\"+\"",
		"\"-\"",
		"\"*\"",
		"\"/\"",
		"<ERROR>"
	};
}
