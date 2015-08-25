package seven.Tree;

import seven.Temp.Temp;

public interface CodeVisitor {
	public void visit(SEQ n);

	public void visit(LABEL n);

	public void visit(JUMP n);

	public void visit(CJUMP n);

	public void visit(MOVE n);

	public void visit(EXP n);

	public Temp visitRT(BINOP n);

	public Temp visitRT(MEM n);

	public Temp visitRT(TEMP n);

	public Temp visitRT(ESEQ n);

	public Temp visitRT(NAME n);

	public Temp visitRT(CONST n);

	public Temp visitRT(CALL n);
}