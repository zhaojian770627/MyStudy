package complier.book.construction.s12;

public interface S1Constants {
	// 标识单词符号类别的整数
	int EOF = 0;
	int PRINTLN = 1;
	int READINT = 2;
	int UNSIGNED = 3;
	int ID = 4;
	int ASSIGN = 5;
	int SEMICOLON = 6;
	int LEFTPAREN = 7;
	int RIGHTPAREN = 8;
	int LEFTBRACE = 9;
	int RIGTHBRACE = 10;
	int PLUS = 11;
	int MINUS = 12;
	int TIMES = 13;
	int DIVIDE = 14;
	int ERROR = 15;

	// tokenImage 为每个单词符号提供字符串
	String[] tokenImage = { 
			"<EOF>",
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
