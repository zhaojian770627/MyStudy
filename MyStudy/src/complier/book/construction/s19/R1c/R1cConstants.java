package complier.book.construction.s19.R1c;

public interface R1cConstants {
	// ��ʶ���ʷ�����������
	int EOF = 0;
	int PRINT = 1;
	int PRINTLN = 2;
	int READINT = 3;
	int WHILE = 4;
	int DO = 5;
	int IF = 6;
	int ELSE = 7;
	int UNSIGNED = 8;
	int ID = 9;
	int ASSIGN = 10;
	int SEMICOLON = 11;
	int LEFTPAREN = 12;
	int RIGHTPAREN = 13;
	int LEFTBRACE = 14;
	int RIGHTBRACE = 15;
	int PLUS = 16;
	int MINUS = 17;
	int TIMES = 18;
	int DIVIDE = 19;
	int STRING = 20;
	int ERROR = 21;

	// tokenImage Ϊÿ�����ʷ����ṩ�ַ���
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
			"\"+\"",
			"\"-\"",
			"\"*\"",
			"\"/\"",
			"<STRING>",
			"<ERROR>"
			};
}
