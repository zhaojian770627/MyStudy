package complier.book.construction.s9;

import complier.book.construction.Fig0612.ArgsTokenMgr;

public class Fig0901Parser {

	private ArgsTokenMgr tm;
	private char currentToken;

	public Fig0901Parser(ArgsTokenMgr tm) {
		this.tm=tm;
		advance();
	}

	private void advance() {
		currentToken=tm.getNextToken();
	}
	
	private void consume(char expected){
		if(currentToken==expected)
			advance();
		else
			throw new RuntimeException("Expecting \""+expected+"\"");
	}

	public void parse() {
		S();
		if(currentToken!='#')	// β�������yԇ
			throw new RuntimeException("Expecting end of input");
	}

	private void S() {
		B();	//ʹ�� S-��BD
		D();
	}

	private void B() {
		switch (currentToken) {
		case 'b': // ʹ�� B-��bB
			consume('b');
			B();
			break;
		case 'c':
			consume('c'); // ʹ�� B->c
			break;
		default:
			throw new RuntimeException("Expecting \"b\" or \"c\"");
		}
	}
	
	private void D() {
		consume('d');	// ʹ�� D->de
		consume('e'); 
	}
}
