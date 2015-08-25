package seven.types;

//import pgtree.*;
import java.util.Vector;
import seven.Temp.Temp;

public class Variable extends MJType {
	private String name;
	private MJType var_type;
	private Temp tmp;

	public Variable(String v_name, MJType v_type) {
		name = v_name;
		var_type = v_type;
		tmp = null;
	}

	public Variable(String v_name, MJType v_type, Temp t) {
		name = v_name;
		var_type = v_type;
		tmp = t;
	}

	public String GetName() {
		return name;
	}

	public Temp GetTemp() {
		return tmp;
	}

	public void Print() {
		String type_name = var_type.GetType();
		System.out.println("             " + name + "   Type: " + type_name);
	}

	public String GetType() {
		return ("Object" + name);
	}

	public MJType SearchType(String name) {
		return var_type;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof Variable))
			return false;
		else
			return true;
	}

	public void SetType(MJType new_type) {
		var_type = new_type;
	}

}