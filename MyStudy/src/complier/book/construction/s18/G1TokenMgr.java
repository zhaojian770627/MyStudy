package complier.book.construction.s18;

import complier.book.construction.s10.Token;

public class G1TokenMgr implements G1Constants {
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private String inputLine; // ���һ��ݔ��
	private Token token; // ���һ���ʷ���
	private StringBuffer buffer; // ���쵥�ʷ���ӳ��

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

		// ���ɷ��ظ��﷨�������ĵ��ʷ���
		token = new Token();
		token.next = null;

		// ��ŵ��ʷ��ſ�ʼλ��
		token.beginLine = currentLineNumber;
		token.beginColumn = currentColumnNumber;

		// ���EOF
		if (currentChar == EORE) {
			token.image = "<EORE>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EORE;
		} else if (Character.isLetter(currentChar)) // ����ʶ��
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
		// �����ַ����ʷ���
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
