//package Translate;
package seven.visitor;

import seven.Temp.Label;
import seven.Tree.CJUMP;
import seven.Tree.CONST;
import seven.Tree.EXP;
import seven.Tree.JUMP;
import seven.Tree.Stm;

public class Ex extends Exp {
	EXP exp;

	Ex(EXP e) {
		exp = e;
	}

	EXP unEx() {
		return exp;
	}

	Stm unNx() {
		return new EXP(exp);
	}

	Stm unCx(Label t, Label f) {
		// if the exp is a constant, emit JUMP statement.
		if (exp instanceof CONST) {
			CONST c = (CONST) exp;
			if (c.value == 0)
				return new JUMP(f);
			else
				return new JUMP(t);
		}
		return new CJUMP(CJUMP.NE, exp, new CONST(0), t, f);
	}
}