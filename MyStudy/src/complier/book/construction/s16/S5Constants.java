package complier.book.construction.s16;

public interface S5Constants {
	int LOCAL = 0;
	int GLOBALVARIABLE = 1;
	int EXTERNVARIABLE = 2;
	int FUNCTIONDEFINITION = 3;
	int FUNCTIONCALL = 4;
	
	// 标识单词符号类别的整数
	int EOF = 0;
	int VOID = 1;
	int PRINT = 2;
	int PRINTLN = 3;
	int READINT = 4;
	int WHILE = 5;
	int DO = 6;
	int IF = 7;
	int ELSE = 8;
	int UNSIGNED = 9;
	int ID = 10;
	int EXTERN =11;
	int INT = 12;
	int ASSIGN = 13;
	int SEMICOLON = 14;
	int LEFTPAREN = 15;
	int RIGHTPAREN = 16;
	int LEFTBRACE = 17;
	int RIGHTBRACE = 18;
	int COMMA = 19;
	int PLUS = 20;
	int MINUS = 21;
	int TIMES = 22;
	int DIVIDE = 23;
	int STRING = 24;
	int ERROR = 25;

	// tokenImage 为每个单词符号提供字符串
	String[] tokenImage = { 
			"<EOF>",
			"\"print\"",
			"\"println\"",
			"\"readint\"",
			"\"while\"",
			"\"do\"",
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
			"\",\"",
			"\"+\"",
			"\"-\"",
			"\"*\"",
			"\"/\"",
			"<STRING>",
			"<ERROR>"
			};
}
