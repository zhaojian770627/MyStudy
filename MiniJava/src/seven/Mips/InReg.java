package seven.Mips;

import seven.Frame.Access;
import seven.Temp.Temp;
import seven.Tree.EXP;
import seven.Tree.TEMP;

public class InReg extends Access {
	Temp temp;

	InReg(Temp t) {
		temp = t;
	}

	public EXP EXP(EXP fp) {
		return new TEMP(temp);
	}

	public String toString() {
		return temp.toString();
	}
}