package complier.book.construction.s12;

import complier.book.construction.s10.Token;

public class S2Parser implements S1Constants {
	private S1SymTab st;
	private S1TokenMgr tm;
	private S1CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	public S2Parser(S1SymTab st, S1TokenMgr tm, S1CodeGen cg) {
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
		//case RIGHTBRACE:
		case EOF:
			return;
		default:
			statement();
			statementList();
		}
	}

	private void statement() {
		try {
			switch (currentToken.kind) {
			case ID:
				assignmentStatement();
				break;
			case PRINT:
				printStatement();
				break;
			case PRINTLN:
				printlnStatement();
				break;
			case READINT:
				readintStatement();
				break;
			case WHILE:
				whileStatement();
				break;
			case DO:
				doWhileStatement();
				break;
			case IF:
				ifStatement();
				break;
			case SEMICOLON:
				nullStatement();
				break;
			case LEFTBRACE:
				compoundStatement();
				break;
			default:
				throw genEx("Expecting statement");
			}
		} catch (RuntimeException e) {
			System.err.println(e.getMessage());
			cg.emitString(";" + e.getMessage());

			// 错误修复
			while (currentToken.kind != SEMICOLON && currentToken.kind != EOF)
				advance();

			if (currentToken.kind != EOF)
				advance();
		}
	}

	private void ifStatement() {
		String label1;
		consume(IF);
		consume(LEFTPAREN);
		expr();
		consume(RIGHTPAREN);
		label1 = cg.getLabel();
		cg.emitInstruction("jz", label1);
		statement();
		elsePart(label1);
	}

	private void elsePart(String label1) {
		String label2;
		label2 = cg.getLabel();
		switch (currentToken.kind) {
		case ELSE:
			consume(ELSE);
			cg.emitInstruction("ja", label2);
			cg.emitLabel(label1);
			statement();
			cg.emitLabel(label2);
			break;
		default:
			cg.emitInstruction(label1);
			break;
		}
	}

	private void readintStatement() {
		consume(READINT);
		consume(LEFTPAREN);
		Token t;
		t = currentToken;
		consume(ID);
		st.enter(t.image);
		cg.emitInstruction("pc", t.image);
		cg.emitInstruction("din");
		cg.emitInstruction("stav");
		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private void compoundList() {
		if (currentToken.kind == RIGHTBRACE)
			return;
		statement();
		compoundList();
	}

	private void compoundStatement() {
		consume(LEFTBRACE);
		// statementList();
		compoundList();
		consume(RIGHTBRACE);
	}

	private void nullStatement() {
		consume(SEMICOLON);
	}

	private void printStatement() {
		consume(PRINT);
		consume(LEFTPAREN);

		printArg();

		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private void printlnStatement() {
		consume(PRINTLN);
		consume(LEFTPAREN);

		switch (currentToken.kind) // {"(","+","-",<UNSIGNED>,<ID>}
		{
		case LEFTPAREN:
		case PLUS:
		case MINUS:
		case UNSIGNED:
		case ID:
		case STRING:
			printArg();
			break;
		case RIGHTPAREN:
			;
			break;
		default:
			throw genEx("Expecting \"(\",\"+\",\"-\",\"<UNSIGNED>\",\"<ID>\",\"<STRING>\"");
		}

		cg.emitInstruction("pc", "'\\n'");
		cg.emitInstruction("aout");

		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private void printArg() {
		switch (currentToken.kind) // {"(","+","-",<UNSIGNED>,<ID>}
		{
		case LEFTPAREN:
		case PLUS:
		case MINUS:
		case UNSIGNED:
		case ID:
			expr();
			cg.emitInstruction("dout");
			break;
		case STRING:
			Token t = currentToken;
			consume(STRING);
			String label = cg.getLabel();
			cg.emitInstruction("pc", label);
			cg.emitInstruction("sout");
			cg.emitdw("^" + label, t.image);
			break;
		default:
			throw genEx("Expecting \"(\",\"+\",\"-\",\"<UNSIGNED>\",\"<ID>\"");
		}
	}

	private void assignmentStatement() {
		Token t;

		t = currentToken;
		consume(ID);
		st.enter(t.image, "0");
		cg.emitInstruction("pc", t.image);
		consume(ASSIGN);

		assignmentTail();

		cg.emitInstruction("stav");
		consume(SEMICOLON);
	}

	private void assignmentTail() {

		if (getToken(1).kind == ID && getToken(2).kind == ASSIGN) {
			Token t;
			t = currentToken;
			st.enter(t.image);
			cg.emitInstruction("pc", t.image);

			consume(ID);
			consume(ASSIGN);
			assignmentTail();

			cg.emitInstruction("dupe");
			cg.emitInstruction("rot");
			cg.emitInstruction("stav");
		} else {
			expr();
		}
	}

	void whileStatement() {
		String label1, label2;

		consume(WHILE);

		label1 = cg.getLabel();
		cg.emitLabel(label1);

		consume(LEFTPAREN);
		expr();
		consume(RIGHTPAREN);

		label2 = cg.getLabel();
		cg.emitInstruction("jz", label2);
		statement();
		cg.emitInstruction("ja", label1);
		cg.emitLabel(label2);
	}

	private void doWhileStatement() {
		consume(DO);
		String label1 = cg.getLabel();
		cg.emitLabel(label1);
		statement();
		consume(WHILE);
		consume(LEFTPAREN);
		expr();
		consume(RIGHTPAREN);
		cg.emitInstruction("jnz", label1);
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
			cg.emitInstruction("add");
			termList();
			break;
		case MINUS:
			consume(MINUS);
			term();
			cg.emitInstruction("sub");
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
			cg.emitInstruction("mult");
			factorList();
			break;
		case DIVIDE:
			consume(DIVIDE);
			factor();
			cg.emitInstruction("div");
			factorList();
			break;
		case RIGHTPAREN:
		case SEMICOLON:
		case PLUS:
		case MINUS:
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

			// 范围检测
			if (t.image.length() > 5 || Integer.parseInt(t.image) > 32767)
				throw genEx("Excepting integer (-32768 to 32767)");

			consume(UNSIGNED);
			cg.emitInstruction("pwc", t.image);
			break;
		case PLUS:
			consume(PLUS);
			factor();
			break;
		case MINUS:
			consume(MINUS);
			switch (currentToken.kind) {
			case UNSIGNED:
				t = currentToken;

				// 范围检测
				if (t.image.length() > 5 || Integer.parseInt(t.image) > 32768)
					throw genEx("Excepting integer (-32768");

				consume(UNSIGNED);
				cg.emitInstruction("pwc", "-" + t.image);
				break;
			case ID:
				t = currentToken;
				consume(ID);
				st.enter(t.image);
				cg.emitInstruction("p", t.image);
				cg.emitInstruction("neg");
				break;
			case LEFTPAREN:
				consume(LEFTPAREN);
				expr();
				consume(RIGHTPAREN);
				cg.emitInstruction("neg");
				break;
			case PLUS:
				consume(PLUS);
				factor();
				cg.emitInstruction("neg");
				break;
			case MINUS:
				consume(MINUS);
				factor();
				break;
			default:
				throw genEx("Expecting factor MINUS");
			}
			break;
		case ID:
			t = currentToken;
			consume(ID);
			st.enter(t.image);
			cg.emitInstruction("p", t.image);
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
