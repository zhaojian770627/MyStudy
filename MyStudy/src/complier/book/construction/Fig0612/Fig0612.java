package complier.book.construction.Fig0612;

/*
 * ���϶���ջʽ�﷨������
 * 1) S->bScA
 * 2) S->cbd
 * 3) A->bcA
 * 4) A->d
 */
public class Fig0612 {

	public static void main(String[] args) {
		// �������ʷ��Ź�����
		ArgsTokenMgr tm=new ArgsTokenMgr(new String[]{"bcbdcd"});
		// �����﷨�����������ݸ������ʷ��Ź�����
		Fig0612Parser parser=new Fig0612Parser(tm);
		// �����﷨����
		parser.parse();
	}

}
