package complier.book.construction.s18;

public interface G1Constants {
    // ��ʶ���ʷ�����������
    int EORE=0;	 	// ������ʽ����
    int CHAR=1;
    int PERIOD=2;		// PERIOD ƥ�������ַ�
    int LEFTPAREN=3;
    int RIGHTPAREN=4;
    int OR=5;
    int STAR=6;
    int ERROR=7;

    int CONCAT=8;		// û�ж�Ӧ�ĵ��ʷ���

    // tokenImage Ϊÿ�����ʷ����ṩ�ַ���
    String[] tokenImage = { 
	"<EORE>",
	"<CHAR>",
	"\".\"",
	"\"(\"",
	"\")\"",
	"\"|\"",
	"\"*\"",
	"<ERROR>"
    };
}
