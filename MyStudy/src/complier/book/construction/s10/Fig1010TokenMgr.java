package complier.book.construction.s10;

import java.util.Scanner;

public class Fig1010TokenMgr implements Fig1010Constants {
	private Scanner inFile;
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private int lineLength;
	private String inputLine;	// 存放一行輸入
	private Token token;		// 存放一单词符号
	private StringBuffer buffer;	// 构造单词符号映像

	public Fig1010TokenMgr(Scanner inFile) {
		this.inFile=inFile;
		currentChar='\n';
		currentColumnNumber=0;
		currentLineNumber=0;
		buffer=new StringBuffer();
	}

	public Token getNextToken()
	{
		// 跳过空白
		while (Character.isWhitespace(currentChar))
			getNextChar();
		
		// 生成返回给语法分析器的单词符号
		token=new Token();
		token.next=null;
		
		// 存放单词符号开始位置
		token.beginLine=currentLineNumber;
		token.beginColumn=currentColumnNumber;
		
		// 检查EOF
		if (currentChar == EOF) {
			token.image = "<EOF>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EOF;
		}
		// 检测unsigned int
		else if (Character.isDigit(currentChar)) {
			buffer.setLength(0);	// 置长度为0
			do						// 处理unsighed int
			{
				// 连接currentChar 到 buffer
				buffer.append(currentChar);
				
				// 存储单词符号结束位置
				// 调用getNextChar()之前必须做的准备
				token.endLine=currentLineNumber;
				token.endColumn=currentColumnNumber;
				getNextChar();
			}while(Character.isDigit(currentChar));
			token.image=buffer.toString();
			token.kind=UNSIGNED;
		}else // 处理单字符单词符号
		{
			switch(currentChar)
			{
			case '+':
				token.kind=PLUS;
				break;
			case '-':
				token.kind=MINUS;
				break;
			case '*':
				token.kind=TIMES;
				break;
			case '/':
				token.kind=DIVIDE;
				break;
			default:
				token.kind=ERROR;
				break;
			}
			
			token.image=Character.toString(currentChar);
		
			token.endLine=currentLineNumber;
			token.endColumn=currentColumnNumber;
			
			getNextChar();
		}
		
		// 单词符号的踪迹作为注释出现在输出文件
		System.out.printf("kd=%3d bL=%3d bC=%3d eL=%3d eC=%3d im=%s%n",
				token.kind,token.beginLine,token.beginColumn,
				token.endLine,token.endColumn,token.image);
		
		return token;
	}
	
	private void getNextChar()
	{
		if(currentChar==EOF)
			return;
		
		if(currentChar=='\n')
		{
			if(inFile.hasNextLine())
			{
				inputLine=inFile.nextLine();
				inputLine=inputLine+"\n";
				currentColumnNumber=0;
				currentLineNumber++;
			}else
			{
				currentChar=EOF;
				return;
			}
		}
		
		currentChar=inputLine.charAt(currentColumnNumber++);
	}
}
