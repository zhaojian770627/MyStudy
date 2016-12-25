package complier.book.construction.s16;

import complier.book.construction.s10.Token;

public class S5Parser implements S5Constants {
	private S5SymTab st;
	private S5TokenMgr tm;
	private S5CodeGen cg;
	private Token currentToken;
	private Token previousToken;

	public S5Parser(S5SymTab st, S5TokenMgr tm, S5CodeGen cg) {
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
		programUnitList();
		cg.endCode();
		if (currentToken.kind != EOF)
			throw genEx("Expecting <EOF>"); // garbage at end?
	}

	private void programUnitList() {
		switch (currentToken.kind) {
		case EOF:
			return;
		default:
			programUnit();
			programUnitList();
		}
	}

	private void programUnit() {
		switch (currentToken.kind) {
		case EXTERN:
			externDeclaration();
			break;
		case INT:
			globalDeclaration();
			break;
		case VOID:
			functionDefinition();
		}
	}

	private void externDeclaration() {
		Token t;
		consume(EXTERN);
		consume(INT);
		t = currentToken;
		consume(ID);

		st.enter(t.image, 0, EXTERNVARIABLE);
		cg.emitInstruction("extern", t.image);

		while (currentToken.kind == COMMA) {
			consume(COMMA);
			t = currentToken;
			consume(ID);
			st.enter(t.image, 0, EXTERNVARIABLE);
			cg.emitInstruction("extern", t.image);
		}
		consume(SEMICOLON);
	}

	private void globalDeclaration() {
		consume(INT);
		global();
		while (currentToken.kind == COMMA) {
			consume(COMMA);
			global();
		}
		consume(SEMICOLON);
	}

	private void global() {
		Token t1, t2;
		String initVal;
		t1 = currentToken;
		consume(ID);
		cg.emitInstruction("public", t1.image);
		initVal = "0";
		if (currentToken.kind == ASSIGN) {
			consume(ASSIGN);
			initVal = "";
			if (currentToken.kind == PLUS)
				consume(PLUS);
			else if (currentToken.kind == MINUS) {
				consume(MINUS);
				initVal = "-";
			}
			t2 = currentToken;
			consume(UNSIGNED);
			initVal = initVal + t2.image;
		}
		st.enter(t1.image, 0, GLOBALVARIABLE);
		cg.emitdw(t1.image, initVal);
	}

	private void functionDefinition() {
		Token t;
		consume(VOID);
		t = currentToken;
		consume(ID);
		cg.emitString(";=========start of function " + t.image);
		st.enter(t.image, 0, FUNCTIONDEFINITION);
		cg.emitInstruction("public", t.image);
		cg.emitLabel(t.image);
		consume(LEFTPAREN);
		if (currentToken.kind == INT)
			parameterList();
		consume(RIGHTPAREN);
		consume(LEFTBRACE);
		cg.emitInstruction("esba");
		localDeclarations();
		statementList();
		consume(RIGHTBRACE);
		cg.emitInstruction("reba");
		cg.emitInstruction("ret");
		cg.emitString(";====================end of function " + t.image);
		st.localRemove();
	}

	private void parameterList() {
		Token t;
		int p;
		t = parameter();
		p = parameterR();
		st.enter(t.image, p, LOCAL);
	}

	private Token parameter() {
		Token t;
		consume(INT);
		t = currentToken;
		consume(ID);
		return t;
	}

	private int parameterR() {
		Token t;
		int p;
		if (currentToken.kind == COMMA) {
			consume(COMMA);
			t = parameter();
			p = parameterR();
			st.enter(t.image, p, LOCAL);
			return p + 1;
		}

		return 2;
	}

	private void localDeclarations() {
		int relativeAddress = -1;
		while (currentToken.kind == INT) {
			consume(INT);
			local(relativeAddress--);

			while (currentToken.kind == COMMA) {
				consume(COMMA);
				local(relativeAddress--);
			}
			consume(SEMICOLON);
		}
	}

	private void local(int relativeAddress) {
		Token t;
		String sign;
		t = currentToken;
		consume(ID);
		st.enter(t.image, relativeAddress, LOCAL);
		if (currentToken.kind == ASSIGN) {
			consume(ASSIGN);
			sign = "";
			if (currentToken.kind == PLUS)
				consume(PLUS);
			else if (currentToken.kind == MINUS) {
				consume(MINUS);
				sign = "-";
			}
			t = currentToken;
			consume(UNSIGNED);
			cg.emitInstruction("pwc", sign + t.image);
		} else
			cg.emitInstruction("asp", "-1");
	}

	private void statementList() {
		switch (currentToken.kind) {
		case RIGHTBRACE:
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
				if (getToken(2).kind == ASSIGN)
					assignmentStatement();
				else
					functionCall();
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

	private void functionCall() {
		Token t;
		int count;

		t = currentToken;
		st.enter(t.image, 0, FUNCTIONCALL);
		consume(ID);
		consume(LEFTPAREN);
		count = 0;
		// "(","+","-",<UNSIGNED>,<ID>
		if (currentToken.kind == LEFTPAREN || currentToken.kind == PLUS || currentToken.kind == MINUS
				|| currentToken.kind == ID || currentToken.kind == UNSIGNED)
			count = argumentList();
		cg.emitInstruction("call", t.image);
		if (count > 0)
			cg.emitInstruction("asp", Integer.toString(count));

		consume(RIGHTPAREN);
		consume(SEMICOLON);
	}

	private int argumentList() {
		int count;
		expr();
		count = 1;
		while (currentToken.kind == COMMA) {
			consume(COMMA);
			expr();
			count++;
		}
		return count;
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
			cg.emitLabel(label1);
			break;
		}
	}

	private void readintStatement() {
		Token t;
		int index;

		consume(READINT);
		consume(LEFTPAREN);
		t = currentToken;
		consume(ID);

		index = st.find(t.image);
		if (index == -1)
			throw genEx(t.image + " not find in systable!");
		cg.pushAddress(index);
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
		int index;

		t = currentToken;
		consume(ID);

		index = st.find(t.image);

		if (index == -1)
			throw genEx(t.image + " not find in systable!");

		cg.pushAddress(index);

		consume(ASSIGN);

		assignmentTail();

		cg.emitInstruction("stav");
		consume(SEMICOLON);
	}

	private void assignmentTail() {

		if (getToken(1).kind == ID && getToken(2).kind == ASSIGN) {
			Token t;
			int index;
			t = currentToken;
			consume(ID);

			index = st.find(t.image);
			if (index == -1)
				throw genEx(t.image + " not find in systable!");
			cg.pushAddress(index);

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
		case COMMA:
			;
			break;
		default:
			throw genEx("Expecting \"+\",\")\",\",\" or \";\"");
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
		case COMMA:
			;
			break;
		default:
			throw genEx("Expecting op ,\")\", or \";\"");
		}
	}

	private void factor() {
		Token t;
		int index;
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
				index = st.find(t.image);
				if (index == -1)
					throw genEx(t.image + " not find in systable!");
				cg.push(index);
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

			index = st.find(t.image);
			if (index == -1)
				throw genEx(t.image + " not find in systable!");
			cg.push(index);

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
