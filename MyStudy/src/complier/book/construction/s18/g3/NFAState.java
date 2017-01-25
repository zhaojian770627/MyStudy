package complier.book.construction.s18.g3;

import java.util.ArrayList;

class NFAState {
	public NFAState arrow1;
	public char label1;
	public NFAState arrow2; // arrow2 always lambda
	public NFAState acceptState;

	// -----------------------------------------
	public NFAState() {
		arrow1 = arrow2 = acceptState = null;
		label1 = 0; // zero represents lambda
	}

	// -----------------------------------------
	public static void displayNFA(NFAState startState) {
		ArrayList<NFAState> states = new ArrayList<NFAState>();
		NFAState s;

		// add all the states to ArrayList
		states.add(startState);
		for (int i = 0; i < states.size(); i++) {
			s = states.get(i);
			if (s.arrow1 != null && !states.contains(s.arrow1))
				states.add(s.arrow1);
			if (s.arrow2 != null && !states.contains(s.arrow2))
				states.add(s.arrow2);
		}

		// display all the states in the ArrayList using
		// index in the ArrayList as the state number
		String arrow1 = null, arrow2 = null;
		for (int i = 0; i < states.size(); i++) {
			s = states.get(i);

			if (s.arrow1 == null) {
				arrow1 = "";
			} else {
				arrow1 = "" + states.indexOf(s.arrow1);
				if (s.label1 == 0)
					arrow1 = arrow1 + "/lambda";
				else
					arrow1 = arrow1 + "/" + Character.toString(s.label1);
			}

			if (s.arrow2 == null)
				arrow2 = "";
			else
				arrow2 = "" + states.indexOf(s.arrow2) + "/lambda";

			if (i == 0)
				System.out.printf("%3d: %-10s   %-10s    acceptState=%d%n", i, arrow1, arrow2,
						states.indexOf(startState.acceptState));
			else
				System.out.printf("%3d: %-10s   %-10s%n", i, arrow1, arrow2);
		}
	}
}
