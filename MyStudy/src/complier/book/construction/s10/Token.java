package complier.book.construction.s10;

public class Token {
	// ��ʶ���ʷ�����������
	public int kind;
	
	// Դ�����е��ʷ��ŵ�λ��
	public int beginLine,beginColumn,endLine,endColumn;
	
	// ��ɵ��ʷ��ŵ��ַ���
	public String image;
	
	// ���ӵ���һ�����ʷ��Ŷ���
	public Token next;
}
