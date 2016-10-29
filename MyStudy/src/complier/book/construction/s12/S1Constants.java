package complier.book.construction.s12;

public interface S1Constants {
	// 标识单词符号类别的整数
	int EOF = 0;
	int PRINT=1;
	int PRINTLN = 2;
	int READINT = 3;
	int UNSIGNED = 4;
	int ID = 5;
	int ASSIGN = 6;
	int SEMICOLON = 7;
	int LEFTPAREN = 8;
	int RIGHTPAREN = 9;
	int LEFTBRACE = 10;
	int RIGHTBRACE = 11;
	int PLUS = 12;
	int MINUS = 13;
	int TIMES = 14;
	int DIVIDE = 15;
	int ERROR = 16;

	// tokenImage 为每个单词符号提供字符串
	String[] tokenImage = { 
			"<EOF>",
			"\"print\"",
			"\"println\"",
			"\"readint\"",
			"<UNSIGNED>",
			"<ID>", 
			"\"=\"",
			"\";\"",
			"\"(\"",
			"\")\"",
			"\"{\"",
			"\"}\"",
			"\"+\"",
			"\"-\"",
			"\"*\"",
			"\"/\"",
			"<ERROR>"
			};
}
