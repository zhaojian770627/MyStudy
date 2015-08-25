package LR;

public class MyParse {
	// 缓冲区
	String buf;
	char[] aryBuf;
	// 结束符
	char EOF = '@';
	boolean isEOF = false;
	// 类型号
	int syn;
	// 取得的字符
	String token;

	public String getToken() {
		return token;
	}

	// 0 'ID',1 'NUM',2 'PRINT',3 ';',4 ',',5 '+',6 ':='
	// 7 '(', 8 ')',9 '$'

	int p, m = 0, n, row, sum = 0;

	MyParse(String source) {
		p = 0;
		buf = source;
		isEOF = false;
		aryBuf = buf.toCharArray();
	}

	void getNextToken() {
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
			// ID
			syn = 0;
			if (token.equals("PRINT"))
				syn = 2;

		} else if ((ch >= '0' && ch <= '9')) {
			sum = 0;
			while ((ch >= '0' && ch <= '9')) {
				sum = sum * 10 + ch - '0';
				token += ch;
				ch = aryBuf[p++];
			}
			p--;
			// NUM
			syn = 1;
			if (sum > 32767)
				syn = -1;
		} else {
			switch (ch) {
			case ';':
				token += ch;
				syn = 3;
				break;
			case ',':
				token += ch;
				syn = 4;
				break;
			case '+':
				token += ch;
				syn = 5;
				break;
			case ':':
				token += ch;
				ch = aryBuf[p++];
				if (ch == '=') {
					syn = 6;
					token += ch;
				} else {
					p--;
					syn = -1;
				}
				break;
			case '(':
				token += ch;
				syn = 7;
				break;
			case ')':
				token += ch;
				syn = 8;
				break;
			case '$':
				token += ch;
				syn = 9;
				break;
			default:
				syn = -1;
				break;
			}
		}

	}

	private void error() throws Exception {
		throw new Exception("Parse error");
	}

	public int getSyn() {
		return syn;
	}

	public boolean isEOF() {
		return isEOF;
	}
}
