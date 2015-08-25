package longbook.lexer;

import java.io.IOException;
import java.util.Hashtable;

public class Lexer {
	public int line = 1;
	private char peek = ' ';
	private Hashtable words = new Hashtable();

	void reserve(Word t) {
		words.put(t.lexeme, t);
	}

	public Lexer() {
		reserve(new Word(Tag.TRUE, "true"));
		reserve(new Word(Tag.FALSE, "false"));
	}

	public Token scan() throws IOException {
		for (;; peek = (char) System.in.read()) {
			if (peek == ' ' || peek == 't')
				continue;
			else if (peek == '\n')
				line = line + 1;
			else
				break;
		}

		if (peek == '/') {
			peek = (char) System.in.read();
			if (peek == '/') {
				do {
					peek = (char) System.in.read();
				} while (peek != '\n');
			} else if (peek == '*') {
				do {
					do {
						peek = (char) System.in.read();
					} while (peek != '*');
					peek = (char) System.in.read();
				} while (peek != '/');
			}
			peek = (char) System.in.read();
		}

		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = 10 * v + Character.digit(peek, 10);
				peek = (char) System.in.read();
			} while (Character.isDigit(peek));
			return new Num(v);
		}

		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();
			do {
				b.append(peek);
				peek = (char) System.in.read();
			} while (Character.isLetterOrDigit(peek));
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null)
				return w;
			w = new Word(Tag.ID, s);
			words.put(s, w);
			return w;
		}
		Token t = new Token(peek);
		peek = ' ';
		return t;
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		Lexer lexer = new Lexer();
		lexer.scan();
		lexer.print();
	}

	private void print() {
		for (Object o : words.values()) {
			Word w = (Word) o;
			System.out.println(w.lexeme);
		}

	}

}
