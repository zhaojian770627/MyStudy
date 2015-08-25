//package Translate;
package seven.visitor;

import seven.Temp.Label;
import seven.Tree.EXP;
import seven.Tree.Stm;

public class Nx extends Exp {
	Stm stm;

	Nx(Stm s) {
		stm = s;
	}

	EXP unEx() {
		return null;
	}

	Stm unNx() {
		return stm;
	}

	Stm unCx(Label t, Label f) {
		return null;
	}
}