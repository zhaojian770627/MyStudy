package complier.book.construction.s18.g2;

import complier.book.construction.s10.Token;

public class G2CodeGen implements G2Constants {

	public NFAState make(int op, Token t) {
		// s is new start state; a is new accept state
		NFAState s, a;
		switch (op) {
		case CHAR:
			s = new NFAState();
			a = new NFAState();
			s.arrow1 = a; // 使s指向A
			s.label1 = t.image.charAt(0);
			s.acceptState = a; // 设置a是接受状态
			return s;
		case PERIOD: // 先暂时这样-----------------
			s = new NFAState();
			a = new NFAState();
			s.arrow1 = a; // 使s指向A
			s.label1 = t.image.charAt(0);
			s.acceptState = a; // 设置a是接受状态
			return s;
		default:
			throw new RuntimeException("Bad call of make");
		}
	}

	public NFAState make(int op, NFAState p) {
		// s is new start state; a is new accept state
		NFAState s, a;
		switch (op) {
		case STAR:
			s = new NFAState();
			a = new NFAState();
			s.arrow1 = p;
			s.label1 = 0;
			s.arrow2 = a;
			s.label1 = 0;

			p.acceptState.arrow1 = s;
			p.acceptState.label1 = 0;

			s.acceptState = a;
			return s;
		default:
			throw new RuntimeException("Bad call of make");
		}
	}

	public NFAState make(int op, NFAState p, NFAState q) {
		// s is new start state; a is new accept state
		NFAState s, a;
		switch (op) {
		case OR:
			s = new NFAState();
			a = new NFAState();

			s.arrow1 = p;
			s.label1 = 0;

			s.arrow2 = q;
			s.label1 = 0;

			p.acceptState.arrow1 = a;
			q.acceptState.arrow1 = a;

			s.acceptState = a;

			return s;
		case CONCAT:
			p.acceptState.arrow1 = q;
			return p;
		default:
			throw new RuntimeException("Bad call of make");
		}
	}

}
