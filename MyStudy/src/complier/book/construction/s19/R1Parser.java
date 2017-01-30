package complier.book.construction.s19;

import complier.book.construction.s10.Token;

public class R1Parser implements R1Constants {
	private R1SymTab st;
	private R1TokenMgr tm;
	private R1CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	public R1Parser(R1SymTab st, R1TokenMgr tm, R1CodeGen cg) {
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
		int expVal;

		consume(PRINTLN);
		consume(LEFTPAREN);

		expVal = expr();
		cg.println(expVal);

		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private void assignmentStatement() {
		Token t;
		int left, expVal;

		t = currentToken;
		consume(ID);
		left = st.enter(t.image, "0", true);
		consume(ASSIGN);
		expVal = expr();
		cg.assign(left, expVal);
		consume(SEMICOLON);
	}

	private int expr() {
		int left, expVal;

		left = term();
		expVal = termList(left);
		return expVal;
	}

	private int term() {
		int left, termVal;

		left = factor();
		termVal = factorList(left);
		return termVal;
	}

	private int termList(int left) {
		int right, temp, expVal;
		switch (currentToken.kind) {
		case PLUS:
			consume(PLUS);
			right = term();
			temp = cg.add(left, right);
			expVal = termList(temp);
			return expVal;
		case RIGHTPAREN:
		case SEMICOLON:
			;
			return left;
		default:
			throw genEx("Expecting \"+\",\")\", or \";\"");
		}
	}

	private int factorList(int left) {
		int right, temp, termVal;
		switch (currentToken.kind) {
		case TIMES:
			consume(TIMES);
			right = factor();
			temp = cg.mult(left, right);
			termVal = factorList(temp);
			return termVal;
		case RIGHTPAREN:
		case SEMICOLON:
		case PLUS:
			;
			return left;
		default:
			throw genEx("Expecting op ,\")\", or \";\"");
		}
	}

	private int factor() {
		Token t;
		int index;

		switch (currentToken.kind) {
		case UNSIGNED:
			t = currentToken;
			consume(UNSIGNED);
			index = st.enter("@", t.image, true);
			return index;
		case PLUS:
			consume(PLUS);
			t = currentToken;
			consume(UNSIGNED);
			index = st.enter("@", t.image, true);
			return index;
		case MINUS:
			consume(MINUS);
			t = currentToken;
			consume(UNSIGNED);
			index = st.enter("@_", t.image, true);
			return index;
		case ID:
			t = currentToken;
			consume(ID);
			index = st.enter(t.image, "0", true);
			return index;
		case LEFTPAREN:
			consume(LEFTPAREN);
			index = expr();
			consume(RIGHTPAREN);
			return index;
		default:
			throw genEx("Expecting factor");
		}
	}
}
