package complier.book.construction.s10;

import java.util.Scanner;

public class Fig1010TokenMgr implements Fig1010Constants {
	private Scanner inFile;
	private char currentChar;
	private int currentColumnNumber;
	private int currentLineNumber;
	private int lineLength;
	private String inputLine;	// ���һ��ݔ��
	private Token token;		// ���һ���ʷ���
	private StringBuffer buffer;	// ���쵥�ʷ���ӳ��

	public Fig1010TokenMgr(Scanner inFile) {
		this.inFile=inFile;
		currentChar='\n';
		currentColumnNumber=0;
		currentLineNumber=0;
		buffer=new StringBuffer();
	}

	public Token getNextToken()
	{
		// �����հ�
		while (Character.isWhitespace(currentChar))
			getNextChar();
		
		// ���ɷ��ظ��﷨�������ĵ��ʷ���
		token=new Token();
		token.next=null;
		
		// ��ŵ��ʷ��ſ�ʼλ��
		token.beginLine=currentLineNumber;
		token.beginColumn=currentColumnNumber;
		
		// ���EOF
		if (currentChar == EOF) {
			token.image = "<EOF>";
			token.endLine = currentLineNumber;
			token.endColumn = currentColumnNumber;
			token.kind = EOF;
		}
		// ���unsigned int
		else if (Character.isDigit(currentChar)) {
			buffer.setLength(0);	// �ó���Ϊ0
			do						// ����unsighed int
			{
				// ����currentChar �� buffer
				buffer.append(currentChar);
				
				// �洢���ʷ��Ž���λ��
				// ����getNextChar()֮ǰ��������׼��
				token.endLine=currentLineNumber;
				token.endColumn=currentColumnNumber;
				getNextChar();
			}while(Character.isDigit(currentChar));
			token.image=buffer.toString();
			token.kind=UNSIGNED;
		}else // �����ַ����ʷ���
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
		
		// ���ʷ��ŵ��ټ���Ϊע�ͳ���������ļ�
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
