package complier.book.construction.s12;

public interface S1Constants {
	// 标识单词符号类别的整数
	int EOF = 0;
	int PRINT = 1;
	int PRINTLN = 2;
	int READINT = 3;
	int WHILE = 4;
	int IF = 5;
	int ELSE = 6;
	int UNSIGNED = 7;
	int ID = 8;
	int ASSIGN = 9;
	int SEMICOLON = 10;
	int LEFTPAREN = 11;
	int RIGHTPAREN = 12;
	int LEFTBRACE = 13;
	int RIGHTBRACE = 14;
	int PLUS = 15;
	int MINUS = 16;
	int TIMES = 17;
	int DIVIDE = 18;
	int STRING = 19;
	int ERROR = 20;

	// tokenImage 为每个单词符号提供字符串
	String[] tokenImage = { 
			"<EOF>",
			"\"print\"",
			"\"println\"",
			"\"readint\"",
			"\"while\"",
			"\"if\"",
			"\"else\"",
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
			"<STRING>",
			"<ERROR>"
			};
}
