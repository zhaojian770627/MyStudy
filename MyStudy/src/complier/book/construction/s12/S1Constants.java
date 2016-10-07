package complier.book.construction.s12;

public interface S1Constants {
	// ��ʶ���ʷ�����������
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

	// tokenImage Ϊÿ�����ʷ����ṩ�ַ���
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
