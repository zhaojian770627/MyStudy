package complier.book.construction.s18.g3;

import complier.book.construction.s10.Token;

public class G3Parser implements G3Constants {
	private G3TokenMgr tm;
	private G3CodeGen cg;

	private Token currentToken;
	private Token previousToken;

	public G3Parser(G3TokenMgr tm, G3CodeGen cg) {
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

	public NFAState parse() {
		NFAState p;
		p = expr();
		consume(EORE);
		return p;
	}

	private NFAState expr() {
		NFAState p;

		p = term();
		p = termList(p);
		return p;
	}

	private NFAState term() {
		NFAState p;

		p = factor();
		p = factorList(p);
		return p;
	}

	private NFAState factorList(NFAState p) {
		switch (currentToken.kind) {
		case EORE:
		case RIGHTPAREN:
		case OR:
			;
			break;
		default:
			NFAState q;
			q = factor();
			p = cg.make(CONCAT, p, q);
			p = factorList(p);
		}
		return p;
	}

	private NFAState factor() {
		Token t;
		NFAState p;

		switch (currentToken.kind) {
		case CHAR:
			t = currentToken;
			consume(CHAR);
			p = cg.make(CHAR, t);
			p = factorTail(p);
			break;
		case PERIOD:
			t = currentToken;
			consume(PERIOD);
			p = cg.make(CHAR, t);
			p = factorTail(p);
			break;
		case LEFTPAREN:
			consume(LEFTPAREN);
			p = expr();
			consume(RIGHTPAREN);
			p = factorTail(p);
			break;
		default:
			throw genEx("Expecting \"<CHAR>\", \".\",or \"(\"");
		}
		return p;
	}

	private NFAState factorTail(NFAState p) {
		switch (currentToken.kind) {
		case STAR:
			consume(STAR);
			p = cg.make(STAR, p);
			p = factorTail(p);
			break;
		case EORE:
		case RIGHTPAREN:
		case CHAR:
		case PERIOD:
		case OR:
			;
			break;
		default:
			throw genEx("Expecting \")\", or \"<EORE>\"");
		}
		return p;
	}

	private NFAState termList(NFAState p) {
		NFAState q;
		switch (currentToken.kind) {
		case OR:
			consume(OR);
			q = term();
			p = cg.make(OR, p, q);
			p = termList(p);
			break;
		case RIGHTPAREN:
		case EORE:
			;
			break;
		default:
			throw genEx("Expecting \"|\",\")\", or \"<EORE>\"");
		}
		return p;
	}
}
