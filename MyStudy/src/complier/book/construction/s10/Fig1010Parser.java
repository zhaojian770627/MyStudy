package complier.book.construction.s10;

public class Fig1010Parser implements Fig1010Constants {
	private Fig1010TokenMgr tm;
	private Token currentToken;
	private Token previousToken;
	
	public Fig1010Parser(Fig1010TokenMgr tm)
	{
		this.tm=tm;
		currentToken=tm.getNextToken();
		previousToken=null;
	}
	
	public void parse(){
		S();
		if(currentToken.kind!=EOF)
			throw genEx("Expecting <EOF>");
	}

	

	private RuntimeException genEx(String errorMessage) {
		return new RuntimeException("Encountered \"" + currentToken.image + "\" on line" + currentToken.beginLine
				+ " column " + currentToken.beginColumn + System.getProperty("line.separator") + errorMessage);
	}
	
	private void advance()
	{
		previousToken=currentToken;
		if(currentToken.next!=null)
			currentToken=currentToken.next;
		else
			currentToken=currentToken.next=tm.getNextToken();
	}
	
	// getToken(i) 返回第i个单词符号,不在单词符号流中前移
	// getToken(0) 返回previousToken
	// getToken(1) 返回currentToken
	// getToken(2) 返回下一个单词符号
	private Token getToken(int i)
	{
		if(i<=0)
			return previousToken;
		
		Token t=currentToken;
		for(int j=1;j<i;j++)
		{
			if(t.next!=null)
				t=t.next;
			else
				t=t.next=tm.getNextToken();
		}
		return t;
	}
	
	private void consume(int expected)
	{
		if(currentToken.kind==expected)
			advance();
		else
			throw genEx("Expecting "+tokenImage[expected]);
	}
	
	private void S()
	{
		int p;
		p=expr();
		System.out.println(p);
	}

	private int expr() {
		int p, q;
		Token t;
		switch (currentToken.kind) {
		case PLUS:
			consume(PLUS);
			p = expr();
			q = expr();
			return p + q;
		case MINUS:
			consume(MINUS);
			p = expr();
			q = expr();
			return p - q;
		case TIMES:
			consume(TIMES);
			p = expr();
			q = expr();
			return p * q;
		case DIVIDE:
			consume(DIVIDE);
			p = expr();
			q = expr();
			return p / q;
		case UNSIGNED:
			t = currentToken;
			consume(UNSIGNED);
			p = Integer.parseInt(t.image);
			return p;
		default:
			throw genEx("Expecting operator or " + tokenImage[UNSIGNED]);
		}
	}
	
	
}
