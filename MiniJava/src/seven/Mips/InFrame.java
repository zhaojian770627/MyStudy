package seven.Mips;

import seven.Frame.Access;
import seven.Tree.BINOP;
import seven.Tree.CONST;
import seven.Tree.EXP;
import seven.Tree.MEM;

public class InFrame extends Access {
	int offset;

	InFrame(int o) {
		offset = o;
	}

	public EXP EXP(EXP fp) {
		return new MEM(new BINOP(BINOP.PLUS, fp, new CONST(offset)));
	}

	public String toString() {
		Integer offset = new Integer(this.offset);
		return offset.toString();
	}
}