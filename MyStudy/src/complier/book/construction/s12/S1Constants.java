package complier.book.construction.s12;

public interface S1Constants {
	// ��ʶ���ʷ�����������
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

	// tokenImage Ϊÿ�����ʷ����ṩ�ַ���
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
