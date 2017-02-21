package complier.book.construction.s21.CI4;

import complier.book.construction.s10.Token;

public class CI4Parser implements CI4Constants {
	private CI4SymTab st;
	private CI4TokenMgr tm;
	private CI4CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	public CI4Parser(CI4SymTab st, CI4TokenMgr tm, CI4CodeGen cg) {
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
		cg.makevtab(st.getSize());
		cg.emit(HALT);
		cg.interpret();
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
		consume(PRINTLN);
		consume(LEFTPAREN);
		expr();
		cg.emit(PRINTLN);
		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private void assignmentStatement() {
		Token t;

		t = currentToken;
		consume(ID);
		int index = st.enter(t.image);
		consume(ASSIGN);
		expr();
		cg.emit(ASSIGN);
		cg.emit(index);
		consume(SEMICOLON);
	}

	private void expr() {
		term();
		termList();
	}

	private void term() {
		factor();
		factorList();
	}

	private void termList() {
		switch (currentToken.kind) {
		case PLUS:
			consume(PLUS);
			term();
			cg.emit(PLUS);
			termList();
			break;
		case RIGHTPAREN:
		case SEMICOLON:
			;
			break;
		default:
			throw genEx("Expecting \"+\",\")\", or \";\"");
		}
	}

	private void factorList() {
		switch (currentToken.kind) {
		case TIMES:
			consume(TIMES);
			factor();
			cg.emit(TIMES);
			factorList();
		case RIGHTPAREN:
		case SEMICOLON:
		case PLUS:
			;
			break;
		default:
			throw genEx("Expecting op ,\")\", or \";\"");
		}
	}

	private void factor() {
		Token t;
		switch (currentToken.kind) {
		case UNSIGNED:
			t = currentToken;
			consume(UNSIGNED);
			cg.emit(PUSHCONSTANT);
			cg.emit(Integer.parseInt(t.image));
			break;
		case PLUS:
			consume(PLUS);
			t = currentToken;
			consume(UNSIGNED);
			cg.emit(PUSHCONSTANT);
			cg.emit(Integer.parseInt(t.image));
			break;
		case MINUS:
			consume(MINUS);
			t = currentToken;
			consume(UNSIGNED);
			cg.emit(PUSHCONSTANT);
			cg.emit(Integer.parseInt("-" + t.image));
			break;
		case ID:
			t = currentToken;
			consume(ID);
			int index = st.enter(t.image);
			cg.emit(PUSH);
			cg.emit(index);
			break;
		case LEFTPAREN:
			consume(LEFTPAREN);
			expr();
			consume(RIGHTPAREN);
			break;
		default:
			throw genEx("Expecting factor");
		}
	}
}
