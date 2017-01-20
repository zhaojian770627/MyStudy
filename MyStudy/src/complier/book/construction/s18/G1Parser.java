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
	 * 构造和返回包含的例外信息 由当前单词符号的映像 位置和期望单词符号组成
	 * 
	 * @param errorMessage
	 * @return
	 */
	private RuntimeException genEx(String errorMessage) {
		return new RuntimeException("Encountered \"" + currentToken.image + "\" on line " + currentToken.beginLine
				+ " column " + currentToken.beginColumn + System.getProperty("line.separator") + errorMessage);
	}

	/**
	 * 前移currentToken到下一个单词符号
	 */
	private void advance() {
		previousToken = currentToken;

		// 如果下一个单词符号在单词符号表中，前移到该单词符号
		if (currentToken.next != null)
			currentToken = currentToken.next;
		else // 否则，从单词符号管理mgr获取下一个单词符号，并放到单词符号表中
			currentToken = currentToken.next = tm.getNextToken();
	}

	// getToken(i) 返回第i个单词符号,不在单词符号流中前移
	// getToken(0) 返回previousToken
	// getToken(1) 返回currentToken
	// getToken(2) 返回下一个单词符号
	private Token getToken(int i) {
		if (i <= 0)
			return previousToken;

		Token t = currentToken;
		for (int j = 1; j < i; j++) { // loop to ith token
			// 如果下一个单词在单词符号表中，移动t到该单词符号
			if (t.next != null)
				t = t.next;
			else // 否则，从单词符号管理器获取，并放到单词符号表中
				t = t.next = tm.getNextToken();
		}
		return t;
	}

	// 如果当前单词符号的种类匹配期望的种类，
	// 那么，消耗并前移到下一个单词符号,否则，抛出例外
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
