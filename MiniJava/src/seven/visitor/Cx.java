//package Translate;
package seven.visitor;

import seven.Temp.Label;
import seven.Temp.Temp;
import seven.Tree.CONST;
import seven.Tree.ESEQ;
import seven.Tree.EXP;
import seven.Tree.LABEL;
import seven.Tree.MOVE;
import seven.Tree.SEQ;
import seven.Tree.Stm;
import seven.Tree.TEMP;

public abstract class Cx extends Exp {
	EXP unEx() {
		Temp r = new Temp();
		Label t = new Label();
		Label f = new Label();

		return new ESEQ(new SEQ(new MOVE(new TEMP(r), new CONST(1)), new SEQ(
				unCx(t, f), new SEQ(new LABEL(f), new SEQ(new MOVE(new TEMP(r),
						new CONST(0)), new LABEL(t))))), new TEMP(r));
	}

	abstract Stm unCx(Label t, Label f);

	Stm unNx() {
		Label join = new Label();

		return new SEQ(unCx(join, join), new LABEL(join));
	}
}
