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
