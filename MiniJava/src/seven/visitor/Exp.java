package seven.visitor;

import seven.Temp.Label;
import seven.Tree.EXP;
import seven.Tree.Stm;

public abstract class Exp {
	abstract EXP unEx();

	abstract Stm unNx();

	abstract Stm unCx(Label t, Label f);
}