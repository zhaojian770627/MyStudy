//package Translate;
package seven.visitor;

import seven.Frame.Frame;
import seven.Tree.Stm;

public class ProcFrag extends Frag {
	public Stm body;
	public Frame frame;

	public ProcFrag(Stm b, Frame f) {
		body = b;
		frame = f;
	}
}