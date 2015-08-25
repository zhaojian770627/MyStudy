package seven.types;

import java.util.Vector;
//import pgtree.*;
import seven.Temp.*;

public class MJMethod extends MJType {
	private Vector parameters = new Vector();
	private Vector locals = new Vector();
	private String name;
	private MJType ret_type;
	private MJObj m_class;

	public MJMethod(String v_name, MJType r_type, MJObj p_class) {
		name = v_name;
		ret_type = r_type;
		m_class = p_class;
	}

	public String InsertParam(String p_name, MJType p_type) {
		boolean error = Repeated(p_name);
		if (error)
			return "Parameter double declaration " + p_name + " in method "
					+ name;
		Variable v = new Variable(p_name, p_type);
		parameters.addElement(v);
		return null;
	}

	public String InsertLocal(String p_name, MJType p_type) {
		boolean error = Repeated(p_name);
		if (error)
			return "Local variable double declaration " + p_name
					+ " in method " + name;
		Variable v = new Variable(p_name, p_type, new Temp());
		locals.addElement(v);
		return null;
	}

	public int GetArgsNumber() {
		return parameters.size();
	}

	public void Print() {
		if (name.equals("main"))
			return;
		int sz = parameters.size();
		String type_name = ret_type.GetType();
		System.out.println("             " + name + "   Return Type: "
				+ type_name);
		System.out.println("         Parameters:");
		for (int i = 0; i < sz; i++) {
			Variable param = (Variable) parameters.elementAt(i);
			param.Print();
		}
		System.out.println(" ");
	}

	public void CheckParamList(ParamList list_p) {
		int sz1 = parameters.size();
		Vector p_list = list_p.GetParamList();
		int sz2 = p_list.size();
		if (sz1 != sz2) {
			System.out
					.println("Error: number of parameters does not match in method "
							+ name);
			return;
		}

		for (int i = 0; i < sz1; i++) {
			MJType ptype1 = (MJType) p_list.elementAt(i);
			Variable v = (Variable) parameters.elementAt(i);
			MJType ptype2 = v.SearchType("");
			String p_name = v.GetName();
			if (!ptype2.CheckType(ptype1)) {
				System.out.println("Error: Imcompatible types for parameter "
						+ p_name);
			}
		}
	}

	public String GetType() {
		return "";
	}

	public String GetName() {
		return name;
	}

	public boolean Repeated(String p_name) {
		int sz = parameters.size();
		for (int i = 0; i < sz; i++) {
			String param = ((Variable) parameters.elementAt(i)).GetName();
			if (p_name.equals(param))
				return true;
		}
		sz = locals.size();
		for (int i = 0; i < sz; i++) {
			String param = ((Variable) locals.elementAt(i)).GetName();
			if (p_name.equals(param))
				return true;
		}
		return false;
	}

	public MJType SearchType(String p_name) {
		int sz = parameters.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) parameters.elementAt(i);
			String param = p.GetName();
			if (p_name.equals(param))
				return (p.SearchType(p_name));
		}
		sz = locals.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) locals.elementAt(i);
			String param = p.GetName();
			if (p_name.equals(param))
				return (p.SearchType(p_name));
		}
		return m_class.SearchType(p_name);
	}

	public String SetArgs(MJClasses all_classes) {
		String error_msg = "";
		boolean has_error = false;
		int sz = parameters.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) parameters.elementAt(i);
			MJType p_type = p.SearchType("");
			if (p_type instanceof Name) {
				String n = ((Name) p_type).GetName();
				MJType new_type = all_classes.GetClass(n);
				if (new_type == null) {
					has_error = true;
					error_msg = error_msg + " Undeclared class " + n;
				} else
					p.SetType(new_type);
			}
		}
		sz = locals.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) locals.elementAt(i);
			MJType p_type = p.SearchType("");
			if (p_type instanceof Name) {
				String n = ((Name) p_type).GetName();
				MJType new_type = all_classes.GetClass(n);
				if (new_type == null) {
					has_error = true;
					error_msg = error_msg + " Undeclared class " + n;
				} else
					p.SetType(new_type);
			}
		}
		if (has_error)
			return error_msg;
		return null;
	}

	public String SetRetType(MJClasses all_classes) {
		String error_msg = "";
		boolean has_error = false;
		if (ret_type instanceof Name) {
			String n = ((Name) ret_type).GetName();
			MJType new_type = all_classes.GetClass(n);
			if (new_type == null) {
				has_error = true;
				error_msg = error_msg + " Undeclared class " + n;
			} else
				ret_type = new_type;
		}
		if (has_error)
			return error_msg;
		return null;
	}

	public SimpleExp SearchIdName(String p_name) {
		int sz = parameters.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) parameters.elementAt(i);
			String param = p.GetName();
			if (p_name.equals(param))
				return (new Temp(i + 1));
		}
		sz = locals.size();
		for (int i = 0; i < sz; i++) {
			Variable p = (Variable) locals.elementAt(i);
			String param = p.GetName();
			if (p_name.equals(param))
				return p.GetTemp();
		}
		return m_class.SearchIdName(p_name);
	}

	public MJType GetClass() {
		return m_class;
	}

	public MJType GetRetType() {
		return ret_type;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof MJMethod))
			return false;
		else
			return true;
	}

	public MJType SearchClass(String name) {
		return m_class.SearchClass(name);
	}

}
