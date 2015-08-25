package seven.Assem;

import java.util.List;

import seven.Temp.Label;
import seven.Temp.Temp;
import seven.Temp.TempMap;

public abstract class Instr {
	public String assem;
	public Temp[] use;
	public Temp[] def;
	public List<Label> jumps;

	public void replaceUse(Temp olduse, Temp newuse) {
		if (use != null)
			for (int i = 0; i < use.length; i++)
				if (use[i] == olduse)
					use[i] = newuse;
	}

	public void replaceDef(Temp olddef, Temp newdef) {
		if (def != null)
			for (int i = 0; i < def.length; i++)
				if (def[i] == olddef)
					def[i] = newdef;
	};

	public String format(TempMap m) {
		StringBuffer s = new StringBuffer();
		int len = assem.length();
		for (int i = 0; i < len; i++)
			if (assem.charAt(i) == '`')
				switch (assem.charAt(++i)) {
				case 's': {
					int n = Character.digit(assem.charAt(++i), 10);
					s.append(m.tempMap(use[n]));
					break;
				}
				case 'd': {
					int n = Character.digit(assem.charAt(++i), 10);
					s.append(m.tempMap(def[n]));
					break;
				}
				case 'j': {
					int n = Character.digit(assem.charAt(++i), 10);
					s.append(jumps.get(n).toString());
					break;
				}
				case '`':
					s.append('`');
					break;
				default:
					throw new Error("bad Assem format:" + assem);
				}
			else
				s.append(assem.charAt(i));
		return s.toString();
	}
}