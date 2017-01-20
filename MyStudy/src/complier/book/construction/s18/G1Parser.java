package complier.book.construction.s18;

import complier.book.construction.s10.Token;

public class G1Parser implements G1Constants {
	private G1TokenMgr tm;
	private Token currentToken;
	private Token previousToken;

	public G1Parser(G1TokenMgr tm) {
		this.tm = tm;

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
		return new RuntimeException("Encountered \"" + currentToken.image + "\" on line " + currentToken.beginLine
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
		expr();
	}

	private void expr() {
		term();
		termList();
		consume(EORE);
	}

	private void term() {
		factor();
		factorList();
	}

	private void factorList() {
		switch (currentToken.kind) {
		case EORE:
		case RIGHTPAREN:
			;
			break;
		default:
			factor();
			factorList();
		}
	}

	private void factor() {
		switch (currentToken.kind) {
		case CHAR:
			consume(CHAR);
			factorTail();
			break;
		case PERIOD:
			consume(PERIOD);
			factorTail();
			break;
		case LEFTPAREN:
			consume(LEFTPAREN);
			expr();
			consume(RIGHTPAREN);
			factorTail();
			break;
		default:
			throw genEx("Expecting \")\", or \"<EORE>\"");
		}
	}

	private void factorTail() {
		switch (currentToken.kind) {
		case STAR:
			consume(STAR);
			factorList();
			break;
		case EORE:
		case RIGHTPAREN:
		case CHAR:
		case PERIOD:
			;
			break;
		default:
			throw genEx("Expecting \")\", or \"<EORE>\"");
		}
	}

	private void termList() {
		switch (currentToken.kind) {
		case OR:
			consume(OR);
			term();
			termList();
			break;
		case RIGHTPAREN:
		case EORE:
			;
			break;
		default:
			throw genEx("Expecting \"|\",\")\", or \"<EORE>\"");
		}
	}
}
