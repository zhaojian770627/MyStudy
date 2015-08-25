package seven.Assem;

import java.util.List;

import seven.Temp.Label;
import seven.Temp.Temp;

public class OPER extends Instr {
	public OPER(String a, Temp[] d, Temp[] s, List<Label> j) {
		assem = a;
		use = s;
		def = d;
		jumps = j;
	}
}