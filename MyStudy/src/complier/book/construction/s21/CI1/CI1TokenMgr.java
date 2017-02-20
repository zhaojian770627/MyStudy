package complier.book.construction.s21.CI1;

import java.io.PrintWriter;
import java.util.Scanner;

import complier.book.construction.s10.Token;

public class CI1TokenMgr implements CI1Constants {
	private Scanner inFile;
	private boolean debug;
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private String inputLine; // ���һ��ݔ��
	private Token token; // ���һ���ʷ���
	private StringBuffer buffer; // ���쵥�ʷ���ӳ��

	public CI1TokenMgr(Scanner inFile, boolean debug) {
		this.inFile = inFile;
		this.debug = debug;
		currentChar = '\n';
		currentColumnNumber = 0;
		currentLineNumber = 0;
		buffer = new StringBuffer();
	}

	public Token getNextToken() {
		// �����հ�
		while (Character.isWhitespace(currentChar))
			getNextChar();

		// ���ɷ��ظ��﷨�������ĵ��ʷ���
		token = new Token();
		token.next = null;

		// ��ŵ��ʷ��ſ�ʼλ��
		token.beginLine = currentLineNumber;
		token.beginColumn = currentColumnNumber;

		// ���EOF
		if (currentChar == EOF) {
			token.image = "<EOF>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EOF;
		}
		// ���unsigned int
		else if (Character.isDigit(currentChar)) {
			buffer.setLength(0); // �ó���Ϊ0
			do // ����unsighed int
			{
				// ����currentChar �� buffer
				buffer.append(currentChar);

				// �洢���ʷ��Ž���λ��
				// ����getNextChar()֮ǰ��������׼��
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isDigit(currentChar));
			token.image = buffer.toString();
			token.kind = UNSIGNED;
		} else if (Character.isLetter(currentChar)) // ����ʶ��
		{
			buffer.setLength(0); // clear buffer
			do {
				buffer.append(currentChar);
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isLetterOrDigit(currentChar));

			// ����buffer��Ϊ�ַ�����token.image
			token.image = buffer.toString();

			// ����Ƿ�ؼ���
			if (KeyWord.getKeyWord(token.image) != null)
				token.kind = KeyWord.getKeyWord(token.image).value;
			else // ���ǹؼ��֣����������ID
				token.kind = ID;
		} else if (currentChar == '"') // �����ַ���
		{
			buffer.setLength(0); // clear buffer
			int escChar = 0;
			do {
				buffer.append(currentChar);
				getNextCharInStr();

				if (currentChar == '\n' || currentChar == '\r') {
					token.kind = ERROR;
					token.image = "\n or \r";
					token.endLine = currentLineNumber;
					token.endColumn = currentColumnNumber;
					return token;
				}

				if (currentChar == '"' && escChar % 2 == 0)
					break;

				if (currentChar == '\\') {
					escChar++;
					continue;
				} else if (currentChar != '"') {
					escChar = 0;
					continue;
				} else if (currentChar == '"' && escChar % 2 != 0) {
					escChar = 0;
					continue;
				}
			} while (true);

			buffer.append(currentChar);
			token.image = buffer.toString();
			token.kind = STRING;
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;

			getNextChar();
		} else
		// �����ַ����ʷ���
		{
			switch (currentChar) {
			case '=':
				token.kind = ASSIGN;
				break;
			case ';':
				token.kind = SEMICOLON;
				break;
			case '(':
				token.kind = LEFTPAREN;
				break;
			case ')':
				token.kind = RIGHTPAREN;
				break;
			case '{':
				token.kind = LEFTBRACE;
				break;
			case '}':
				token.kind = RIGHTBRACE;
				break;
			case '+':
				token.kind = PLUS;
				break;
			case '-':
				token.kind = MINUS;
				break;
			case '*':
				token.kind = TIMES;
				break;
			case '/':
				token.kind = DIVIDE;
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

		// ���ʷ��ŵ��ټ���Ϊע�ͳ���������ļ�
		if (debug)
			System.out.printf(";kd=%3d bL=%3d bC=%3d eL=%3d eC=%3d im=%s%n", token.kind, token.beginLine,
					token.beginColumn, token.endLine, token.endColumn, token.image);

		return token;
	}

	private void getNextChar() {
		if (currentChar == EOF)
			return;

		if (currentChar == '\n') {
			if (inFile.hasNextLine()) {
				inputLine = inFile.nextLine(); // ��ȡ��һ��
				inputLine = inputLine + "\n";
				currentColumnNumber = 0;
				currentLineNumber++;
			} else {
				currentChar = EOF;
				return;
			}
		}

		currentChar = inputLine.charAt(currentColumnNumber++);

		if (currentChar == '/' && inputLine.charAt(currentColumnNumber) == '/')
			currentChar = '\n';
	}

	private void getNextCharInStr() {
		if (currentChar == EOF)
			return;

		if (currentChar == '\n') {
			if (inFile.hasNextLine()) {
				inputLine = inFile.nextLine(); // ��ȡ��һ��
				// ���ԭ����Ϊע��
				inputLine = inputLine + "\n";
				currentColumnNumber = 0;
				currentLineNumber++;
			} else {
				currentChar = EOF;
				return;
			}
		}

		currentChar = inputLine.charAt(currentColumnNumber++);
	}
}
