package complier.book.construction.s12;

public interface S1Constants {
	// ��ʶ���ʷ�����������
	int EOF = 0;
	int PRINT=1;
	int PRINTLN = 2;
	int READINT = 3;
	int WHILE= 4;
	int UNSIGNED = 5;
	int ID = 6;
	int ASSIGN = 7;
	int SEMICOLON = 8;
	int LEFTPAREN = 9;
	int RIGHTPAREN = 10;
	int LEFTBRACE = 11;
	int RIGHTBRACE = 12;
	int PLUS = 13;
	int MINUS = 14;
	int TIMES = 15;
	int DIVIDE = 16;
	int STRING = 17;
	int ERROR = 18;

	// tokenImage Ϊÿ�����ʷ����ṩ�ַ���
	String[] tokenImage = { 
			"<EOF>",
			"\"print\"",
			"\"println\"",
			"\"readint\"",
			"\"while\"",
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
