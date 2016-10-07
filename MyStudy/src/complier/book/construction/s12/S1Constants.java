package complier.book.construction.s12;

public interface S1Constants {
	// 标识单词符号类别的整数
	int EOF = 0;
	int PRINTLN = 1;
	int UNSIGNED = 2;
	int ID = 3;
	int ASSIGN = 4;
	int SEMICOLON = 5;
	int LEFTPAREN = 6;
	int RIGHTPAREN = 7;
	int PLUS = 8;
	int MINUS = 9;
	int TIMES = 10;
	int ERROR = 11;

	// tokenImage 为每个单词符号提供字符串
	String[] tokenImage = { 
			"<EOF>",
			"\"println\"",
			"<UNSIGNED>",
			"<ID>", 
			"\"=\"",
			"\";\"",
			"\"(\"",
			"\")\"",
			"\"+\"",
			"\"-\"",
			"\"*\"",
			"<ERROR>"
			};
}
