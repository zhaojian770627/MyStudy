package complier.book.construction.s18;

import complier.book.construction.s10.Token;

public class G1TokenMgr implements G1Constants {
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private String inputLine; // 存放一行輸入
	private Token token; // 存放一单词符号
	private StringBuffer buffer; // 构造单词符号映像

	private int inputLength;

	public G1TokenMgr(String regex) {
		inputLine = regex;
		inputLength = inputLine.length();
		currentColumnNumber = 0;
		currentLineNumber = 0;
		buffer = new StringBuffer();
	}

	public Token getNextToken() {
		getNextChar();

		// 生成返回给语法分析器的单词符号
		token = new Token();
		token.next = null;

		// 存放单词符号开始位置
		token.beginLine = currentLineNumber;
		token.beginColumn = currentColumnNumber;

		// 检查EOF
		if (currentChar == EORE) {
			token.image = "<EORE>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EORE;
		} else if (Character.isLetter(currentChar)) // 检测标识符
		{
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.image = String.valueOf(currentChar);
			token.kind = CHAR;
			getNextChar();
		} else if (currentChar == '\\') {
			getNextChar();
			if (currentChar == EORE) {
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				token.image = "<ERROR>";
				token.kind = ERROR;
			} else {
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				token.image = String.valueOf(currentChar);
				token.kind = CHAR;
				getNextChar();
			}
		} else
		// 处理单字符单词符号
		{
			switch (currentChar) {
			case '.':
				token.kind = PERIOD;
				break;
			case '(':
				token.kind = LEFTPAREN;
				break;
			case ')':
				token.kind = RIGHTPAREN;
				break;
			case '|':
				token.kind = OR;
				break;
			case '*':
				token.kind = START;
				break;
			default:
				token.kind = ERROR;
				break;
			}

			token.image = Character.toString(currentChar);
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;

			getNextChar();
		}

		return token;
	}

	private void getNextChar() {
		if (currentChar == EORE)
			return;

		if (currentColumnNumber == inputLength) {
			currentChar = EORE;
			return;
		}

		currentChar = inputLine.charAt(currentColumnNumber++);
	}
}
