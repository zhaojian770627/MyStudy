package complier.book.construction.s12;

import complier.book.construction.s10.Token;

public class S1Parser implements S1Constants {
	private S1SymTab st;
	private S1TokenMgr tm;
	private S1CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	public S1Parser(S1SymTab st, S1TokenMgr tm, S1CodeGen cg) {
		this.st = st;
		this.tm = tm;
		this.cg = cg;

		currentToken = tm.getNextToken();
		previousToken = null;
	}

	/**
	 * ����ͷ��ذ�����������Ϣ �ɵ�ǰ���ʷ��ŵ�ӳ�� λ�ú��������ʷ������
	 * 
	 * @param errorMessage
	 * @return
	 */
	private RuntimeException genEx(String errorMessage) {
		return new RuntimeException("Encountered \"" + currentToken.image + "\" on line" + currentToken.beginLine
				+ " column " + currentToken.beginColumn + System.getProperty("line.separator") + errorMessage);
	}

	/**
	 * ǰ��currentToken����һ�����ʷ���
	 */
	private void advance() {
		previousToken = currentToken;

		// �����һ�����ʷ����ڵ��ʷ��ű��У�ǰ�Ƶ��õ��ʷ���
		if (currentToken.next != null)
			currentToken = currentToken.next;
		else // ���򣬴ӵ��ʷ��Ź���mgr��ȡ��һ�����ʷ��ţ����ŵ����ʷ��ű���
			currentToken = currentToken.next = tm.getNextToken();
	}

	// getToken(i) ���ص�i�����ʷ���,���ڵ��ʷ�������ǰ��
	// getToken(0) ����previousToken
	// getToken(1) ����currentToken
	// getToken(2) ������һ�����ʷ���
	private Token getToken(int i) {
		if (i <= 0)
			return previousToken;

		Token t = currentToken;
		for (int j = 1; j < i; j++) { // loop to ith token
			// �����һ�������ڵ��ʷ��ű��У��ƶ�t���õ��ʷ���
			if (t.next != null)
				t = t.next;
			else // ���򣬴ӵ��ʷ��Ź�������ȡ�����ŵ����ʷ��ű���
				t = t.next = tm.getNextToken();
		}
		return t;
	}

	// �����ǰ���ʷ��ŵ�����ƥ�����������࣬
	// ��ô�����Ĳ�ǰ�Ƶ���һ�����ʷ���,�����׳�����
	private void consume(int expected) {
		if (currentToken.kind == expected)
			advance();
		else
			throw genEx("Expecting " + tokenImage[expected]);
	}

	public void parse() {
		program(); // program is start symbol for grammar
	}

	private void program() {
		statementList();
		cg.endCode();
		if (currentToken.kind != EOF)
			throw genEx("Expecting <EOF>"); // garbage at end?
	}

	private void statementList() {
		switch (currentToken.kind) {
		case ID:
		case PRINTLN:
			statement();
			statementList();
			break;
		case EOF:
			;
			break;
		default:
			throw genEx("Expecting statement or <EOF>");
		}
	}

	private void statement() {
		switch (currentToken.kind) {
		case ID:
			assignmentStatement();
			break;
		case PRINTLN:
			printlnStatement();
			break;
		default:
			throw genEx("Expecting statement");
		}
	}

	private void printlnStatement() {
		// TODO Auto-generated method stub

	}

	private void assignmentStatement() {
		// TODO Auto-generated method stub

	}

}
