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
	private String inputLine; // 存放一行輸入
	private Token token; // 存放一单词符号
	private StringBuffer buffer; // 构造单词符号映像

	public CI1TokenMgr(Scanner inFile, boolean debug) {
		this.inFile = inFile;
		this.debug = debug;
		currentChar = '\n';
		currentColumnNumber = 0;
		currentLineNumber = 0;
		buffer = new StringBuffer();
	}

	public Token getNextToken() {
		// 跳过空白
		while (Character.isWhitespace(currentChar))
			getNextChar();

		// 生成返回给语法分析器的单词符号
		token = new Token();
		token.next = null;

		// 存放单词符号开始位置
		token.beginLine = currentLineNumber;
		token.beginColumn = currentColumnNumber;

		// 检查EOF
		if (currentChar == EOF) {
			token.image = "<EOF>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EOF;
		}
		// 检测unsigned int
		else if (Character.isDigit(currentChar)) {
			buffer.setLength(0); // 置长度为0
			do // 处理unsighed int
			{
				// 连接currentChar 到 buffer
				buffer.append(currentChar);

				// 存储单词符号结束位置
				// 调用getNextChar()之前必须做的准备
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isDigit(currentChar));
			token.image = buffer.toString();
			token.kind = UNSIGNED;
		} else if (Character.isLetter(currentChar)) // 检测标识符
		{
			buffer.setLength(0); // clear buffer
			do {
				buffer.append(currentChar);
				token.endLine = currentLineNumber;
				token.endColumn = currentColumnNumber;
				getNextChar();
			} while (Character.isLetterOrDigit(currentChar));

			// 保存buffer作为字符串到token.image
			token.image = buffer.toString();

			// 检测是否关键字
			if (KeyWord.getKeyWord(token.image) != null)
				token.kind = KeyWord.getKeyWord(token.image).value;
			else // 不是关键字，因此种类是ID
				token.kind = ID;
		} else if (currentChar == '"') // 处理字符串
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
		// 处理单字符单词符号
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

		// 单词符号的踪迹作为注释出现在输出文件
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
				inputLine = inFile.nextLine(); // 获取下一行
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
				inputLine = inFile.nextLine(); // 获取下一行
				// 输出原行作为注释
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
