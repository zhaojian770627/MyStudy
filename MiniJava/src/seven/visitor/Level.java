package seven.visitor;

import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;

import seven.Symbol.Symbol;

public class Level {
	public Level parent;
	seven.Frame.Frame frame; // not public!
	public List<Access> formals = new LinkedList<Access>();

	public Level(Level p, Symbol name, List<Boolean> escapes) {
		parent = p;
		frame = parent.frame.newFrame(name, escapes);
		for (Iterator<seven.Frame.Access> f = frame.formals.iterator(); f
				.hasNext();)
			formals.add(new Access(this, f.next()));
	}

	Level(seven.Frame.Frame f) {
		frame = f;
	}

	public seven.Temp.Label name() {
		return frame.name;
	}

	public Access allocLocal(boolean escape) {
		return new Access(this, frame.allocLocal(escape));
	}
}
