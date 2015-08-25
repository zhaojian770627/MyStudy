package seven.Frame;

import java.util.List;

import seven.Assem.Instr;
import seven.Symbol.Symbol;
import seven.Temp.Label;
import seven.Temp.Temp;
import seven.Temp.TempMap;
import seven.Tree.EXP;
import seven.Tree.Stm;

public abstract class Frame implements TempMap {
	public Label name;
	public List<Access> formals;

	public abstract Frame newFrame(Symbol name, List<Boolean> formals);

	public abstract Access allocLocal(boolean escape);

	public abstract Temp FP();

	public abstract int wordSize();

	public abstract EXP externalCall(String func, List<EXP> args);

	public abstract Temp RV();

	public abstract String string(Label label, String value);

	public abstract Label badPtr();

	public abstract Label badSub();

	public abstract String tempMap(Temp temp);

	public abstract List<Instr> codegen(List<Stm> stms) throws Exception;

	public abstract void procEntryExit1(List<Stm> body);

	public abstract void procEntryExit2(List<Instr> body);

	public abstract void procEntryExit3(List<Instr> body);

	public abstract Temp[] registers();

	public abstract void spill(List<Instr> insns, Temp[] spills) throws Exception;

	public abstract String programTail(); // append to end of target code
}