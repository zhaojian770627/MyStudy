package complier.book.construction.s10;

public interface Fig1010Constants {
	// ��ʾ���ʷ�����������
	public int EOF=0;
	public int UNSIGNED=1;
	public int PLUS=2;
	public int MINUS=3;
	public int TIMES=4;
	public int DIVIDE=5;
	public int ERROR=6;
	
	// tokenImage Ϊÿ�൥�ʷ����ṩ�ַ���ӳ��
	String[] tokenImage={
		"<EOF>",
		"<UNSIGNED>",
		"\"+\"",
		"\"-\"",
		"\"*\"",
		"\"/\"",
		"<ERROR>"
	};
}
