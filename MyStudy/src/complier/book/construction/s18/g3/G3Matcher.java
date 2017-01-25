package complier.book.construction.s18.g3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class G3Matcher {
	ArrayList<NFAState> currentStates;
	ArrayList<NFAState> nextStates;
	NFAState startState;
	Scanner inFile;

	public G3Matcher(Scanner inFile, NFAState startState) {
		this.startState = startState;
		this.inFile = inFile;

		currentStates = new ArrayList<>();
		nextStates = new ArrayList<>();
	}

	private boolean lambdaClosure() {
		boolean gotAccept = false;
		HashSet<NFAState> curStatesMap = new HashSet<NFAState>();
		curStatesMap.addAll(currentStates);
		// for (NFAState s : currentStates) {
		for (int i = 0; i < currentStates.size(); i++) {
			NFAState s = currentStates.get(i);
			if (startState.acceptState.equals(s)) {
				gotAccept = true;
				break;
			}

			if (s.arrow1 != null && s.label1 == 0 && !curStatesMap.contains(s.arrow1)) {
				currentStates.add(s.arrow1);
				curStatesMap.add(s.arrow1);
			}

			if (s.arrow2 != null && !curStatesMap.contains(s.arrow2)) {
				currentStates.add(s.arrow2);
				curStatesMap.add(s.arrow2);
			}
		}
		return gotAccept;
	}

	private void applyChar(char c) {
		nextStates.clear();
		HashSet<NFAState> nextStatesMap = new HashSet<NFAState>();
		for (NFAState s : currentStates) {
			if (s.arrow1 != null && !nextStatesMap.contains(s.arrow1) && (s.label1 == '.' || s.label1 == c)) {
				nextStatesMap.add(s.arrow1);
				nextStates.add(s.arrow1);
			}
		}

		ArrayList<NFAState> tmp = currentStates;
		currentStates = nextStates;
		nextStates = tmp;
	}

	public void match() {
		int startIndex, bufIndex;
		boolean gotAccept = false;
		while (inFile.hasNextLine()) {
			String buf = inFile.nextLine();
			// 处理buf中输入行
			for (startIndex = 0; startIndex < buf.length(); startIndex++) {
				currentStates.clear();
				currentStates.add(startState);
				bufIndex = startIndex;

				while (true) {
					gotAccept = lambdaClosure();
					if (gotAccept // Accept state entered
							|| bufIndex >= buf.length() // end substring
							|| currentStates.size() == 0) // trap state
						break;
					applyChar(buf.charAt(bufIndex++));
				}

				// display line if match occurred somewhere
				if (gotAccept) {
					System.out.println(buf);
					break; // go to next line
				}
			}
		} // end loop
	}
}
