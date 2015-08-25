package parse;

public class MyParse {
	// 缓冲区
	String buf;
	char[] aryBuf;
	String token;
	String type;
	int syn, p, m = 0, n, row, sum = 0;
	String[] rwtab = { "IF", "THEN", "ELSE", "BEGIN", "END", "PRINT", "SEMI",
			"NUM", "EQ", "KEYWORD" };
	char EOF = '@';
	boolean isEOF = false;
	
	MyParse(String source) {
		p = 0;
		buf = source;
		aryBuf = buf.toCharArray();
	}

	void getToken() {
		token = "";
		syn = 0;

		
		char ch = aryBuf[p++];
		// $ 代表结束
		while (ch == ' ' || ch == EOF) {
			if (ch == EOF) {
				isEOF = true;
				token += EOF;
				return;
			}
			ch = aryBuf[p++];
		}
		
		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {
			while ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')
					|| (ch >= 'A' && ch <= 'Z')) {
				token += ch;
				ch = aryBuf[p++];
			}
			p--;
			syn = 10;
			for (n = 0; n < 6; n++)
				// 将识别出来的字符和已定义的标示符作比较，
				if (token.equals(rwtab[n])) {
					syn = n + 1;
					break;
				}
		} else if ((ch >= '0' && ch <= '9')) {
			sum = 0;
			while ((ch >= '0' && ch <= '9')) {
				sum = sum * 10 + ch - '0';
				ch = aryBuf[p++];
			}
			p--;
			syn = 8;
			token = "NUM";
			if (sum > 32767)
				syn = -1;
		} else {
			switch (ch) {
			case '=':
				token += ch;
				syn = 9;
				break;
			case ';':
				token += ch;
				syn = 7;
				break;
			default:
				syn = -1;
				break;
			}
		}

	}

	void parse() throws Exception {
		getToken();
		S();
		System.out.println("GOOD");
	}

	private void S() throws Exception {
		switch (syn) {
		case 1:
			match("IF");
			E();
			match("THEN");
			S();
			match("ELSE");
			S();
			break;
		case 4:
			match("BEGIN");
			S();
			L();
			break;
		case 6:
			match("PRINT");
			E();
			break;
		case 0:
			break;
		default:
			error();
		}

	}

	private void L() throws Exception {
		switch (syn) {
		case 5:
			match("END");
			break;
		case 7:
			match("SEMI");
			S();
			L();
			break;
		default:
			error();
		}

	}

	private void E() throws Exception {
		match("NUM");
		match("=");
		match("NUM");

	}

	private void match(String tok) throws Exception {
		if (token.equals(tok))
			advance();
		else
			error();
	}

	private void error() throws Exception {
		throw new Exception("Parse error");
	}

	private void advance() {
		getToken();
	}
}
