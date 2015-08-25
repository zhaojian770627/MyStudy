//package Translate;
package seven.visitor;

import seven.Temp.Label;

public class RelCx extends Cx {
	int op;
	seven.Tree.EXP left, right;

	RelCx(int o, seven.Tree.EXP l, seven.Tree.EXP r) {
		op = o;
		left = l;
		right = r;
	}

	seven.Tree.Stm unCx(Label t, Label f) {
		return new seven.Tree.CJUMP(op, left, right, t, f);
	}
}