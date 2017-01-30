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
	 * 构造和返回包含的例外信息 由当前单词符号的映像 位置和期望单词符号组成
	 * 
	 * @param errorMessage
	 * @return
	 */
	private RuntimeException genEx(String errorMessage) {
		return new RuntimeException("Encountered \"" + currentToken.image + "\" on line" + currentToken.beginLine
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
