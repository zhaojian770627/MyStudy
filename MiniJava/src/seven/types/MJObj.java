package seven.types;

import java.util.Vector;
//import pgtree.* ;
import seven.Temp.*;

public class MJObj extends MJType {
	private String name;
	private Vector fields = new Vector();
	private Vector methods = new Vector();
	private MJObj class_extends;
	private String extend_name;
	private MJClasses all_classes;
	private String[] f_names;
	private String[] m_names;
	private String[] l_names;

	public MJObj(String v_name, MJClasses all) {
		name = v_name;
		all_classes = all;
		extend_name = null;
		f_names = null;
		m_names = null;
		l_names = null;
	}

	public MJObj(String v_name, String ext_name, MJClasses all) {
		name = v_name;
		extend_name = ext_name;
		all_classes = all;
		f_names = null;
		m_names = null;
		l_names = null;
	}

	public String GetClassName() {
		return name;
	}

	public String GetExtenName() {
		return extend_name;
	}

	public void SetExtend(MJObj ext) {
		class_extends = ext;
	}

	public String InsertField(String f_name, MJType f_type) {
		boolean error = Repeated_Field(f_name);
		if (error)
			return "Class variable double declaration " + f_name + " in class "
					+ name;
		Variable v = new Variable(f_name, f_type);
		fields.addElement(v);
		return null;
	}

	public String InsertMethod(MJMethod mtd) {
		String m_name = mtd.GetName();
		boolean error = Repeated_Method(m_name);
		if (error)
			return "Class method double declaration " + m_name + " in class "
					+ name;
		methods.addElement(mtd);
		return null;
	}

	public void Print() {
		int fsz = fields.size();
		int msz = methods.size();
		System.out.println("Class: " + name + " Extends: " + extend_name);
		System.out.println("    Class Variables:");
		for (int i = 0; i < fsz; i++) {
			Variable var = (Variable) fields.elementAt(i);
			var.Print();
		}
		System.out.println("\n    Methods:");
		for (int i = 0; i < msz; i++) {
			MJMethod meth = (MJMethod) methods.elementAt(i);
			meth.Print();
		}
		System.out.println(" ");
	}

	public String GetType() {
		return ("Object" + name);
	}

	public String SetMethods(MJClasses all_classes) {
		String error_msg = "";
		boolean has_error = false;
		int msz = methods.size();
		for (int i = 0; i < msz; i++) {
			String aux1;
			String aux2;
			MJMethod meth = (MJMethod) methods.elementAt(i);
			aux1 = meth.SetArgs(all_classes);
			if (aux1 != null) {
				error_msg = error_msg + aux1;
				has_error = true;
			}
			aux2 = meth.SetRetType(all_classes);
			if (aux2 != null) {
				error_msg = error_msg + aux2;
				has_error = true;
			}
		}
		if (has_error)
			return error_msg;
		return null;
	}

	public String SetFields(MJClasses all_classes) {
		String error_msg = "";
		boolean has_error = false;
		int sz = fields.size();
		for (int i = 0; i < sz; i++) {
			Variable v = (Variable) fields.elementAt(i);
			MJType v_type = v.SearchType("");
			if (v_type instanceof Name) {
				String n = ((Name) v_type).GetName();
				MJType new_type = all_classes.GetClass(n);
				if (new_type == null) {
					has_error = true;
					error_msg = error_msg + " Undeclared class " + n;
				} else
					v.SetType(new_type);
			}
		}
		if (has_error)
			return error_msg;
		return null;
	}

	// This method was inserted to fill the table to be used to
	// allocate heap space during int. code generation
	public String[] SortedFields() {
		int sz = fields.size();
		String[] aux1;
		String[] aux2 = new String[sz];
		for (int i = 0; i < sz; i++) {
			Variable f_var = (Variable) fields.elementAt(i);
			aux2[i] = f_var.GetName();
		}
		if (f_names != null)
			return f_names;
		if (extend_name != null) {
			aux1 = class_extends.SortedFields();
			f_names = Merge(aux1, aux2);
		} else
			f_names = aux2;
		return f_names;
	}

	// This method was inserted to fill the table to be used to
	// allocate heap space during int. code generation
	public String[] SortedMethods() {
		int sz = methods.size();
		String[] aux1_n;
		String[] aux1_l;
		String[] aux2_n = new String[sz];
		String[] aux2_l = new String[sz];
		for (int i = 0; i < sz; i++) {
			aux2_n[i] = ((MJMethod) methods.elementAt(i)).GetName();
			aux2_l[i] = name + "_" + aux2_n[i];
		}
		if (m_names != null)
			return m_names;
		if (extend_name != null) {
			aux1_n = class_extends.SortedMethods();
			aux1_l = class_extends.GetMetLabels();
			m_names = Merge(aux1_n, aux2_n);
			l_names = Merge(aux1_n, aux2_n, aux1_l, aux2_l);
		} else {
			m_names = aux2_n;
			l_names = aux2_l;
		}
		return m_names;
	}

	public String[] GetMetLabels() {
		int sz = l_names.length;
		String[] aux = new String[sz];
		for (int i = 0; i < sz; i++)
			aux[i] = l_names[i];
		return aux;
	}

	public int GetAllocSizeF() {
		return f_names.length;
	}

	public int GetAllocSizeM() {
		return m_names.length;
	}

	public String[] Merge(String[] s1, String s2[]) {
		int sz1 = s1.length;
		int sz2 = s2.length;
		int sz3 = sz2;
		int sz4 = 0;
		int tam = 0;
		String[] s3 = new String[sz3];
		String[] ret_s;
		for (int i = 0; i < sz2; i++)
			if (IsNotIn(s1, s2[i])) {
				s3[tam] = s2[i];
				tam = tam + 1;
			}
		sz4 = sz1 + tam;
		ret_s = new String[sz4];

		for (int i = 0; i < sz1; i++)
			ret_s[i] = s1[i];

		for (int i = 0; i < tam; i++)
			ret_s[i + sz1] = s3[i];

		return ret_s;
	}

	public String[] Merge(String[] s1, String s2[], String[] s1_l, String[] s2_l) {
		int sz1 = s1.length;
		int sz2 = s2.length;
		int sz3 = sz2;
		int sz4 = 0;
		int tam = 0;
		String[] s3 = new String[sz3];
		String[] ret_s;
		for (int i = 0; i < sz2; i++)
			if (IsNotIn(s1, s1_l, s2[i], s2_l[i])) {
				s3[tam] = s2_l[i];
				tam = tam + 1;
			}
		sz4 = sz1 + tam;
		ret_s = new String[sz4];

		for (int i = 0; i < sz1; i++)
			ret_s[i] = s1_l[i];

		for (int i = 0; i < tam; i++)
			ret_s[i + sz1] = s3[i];

		return ret_s;
	}

	public boolean IsNotIn(String[] s1, String s2) {
		int sz = s1.length;
		for (int i = 0; i < sz; i++)
			if (s2.equals(s1[i]))
				return false;
		return true;
	}

	public boolean IsNotIn(String[] s1, String[] s1_l, String s2, String s2_l) {
		int sz = s1.length;
		for (int i = 0; i < sz; i++)
			if (s2.equals(s1[i])) {
				s1_l[i] = s2_l;
				return false;
			}
		return true;
	}

	public SimpleExp SearchIdName(String id_name) {
		for (int i = 0; i < f_names.length; i++)
			if (f_names[i].equals(id_name))
				return new Offset(i + 1);
		System.out.println("Oh Oh --> MJobj.searchidnam error");
		return null;
	}

	public int SearchMetInd(String id_name) {
		for (int i = 0; i < m_names.length; i++)
			if (m_names[i].equals(id_name))
				return i;
		System.out.println("Oh Oh --> MJobj.searchMethid error");
		return 0;
	}

	public void PrintSortedFields() {
		System.out.print("\n" + name + ": ");
		for (int i = 0; i < f_names.length; i++)
			System.out.print(f_names[i] + " ");
	}

	public void PrintSortedMethods() {
		System.out.print("\n" + name + ": ");
		for (int i = 0; i < m_names.length; i++)
			System.out.print(m_names[i] + "(" + l_names[i] + ") ");
	}

	public boolean Repeated_Field(String field_name) {
		int sz = fields.size();
		for (int i = 0; i < sz; i++) {
			String fn_name = ((Variable) fields.elementAt(i)).GetName();
			if (fn_name.equals(field_name))
				return true;
		}
		return false;
	}

	public MJType SearchType(String field_name) {
		int sz = fields.size();
		for (int i = 0; i < sz; i++) {
			Variable f_var = (Variable) fields.elementAt(i);
			String fn_name = f_var.GetName();
			if (fn_name.equals(field_name))
				return (f_var.SearchType(fn_name));
		}
		if (extend_name != null)
			return class_extends.SearchType(field_name);
		return null;
	}

	public MJType SearchClass(String cn) {
		return all_classes.GetClass(cn);
	}

	public MJType SearchMethod(String method_name) {
		// System.out.println("ok");
		int sz = methods.size();
		// System.out.println("ok");
		for (int i = 0; i < sz; i++) {
			MJMethod mj_met = (MJMethod) methods.elementAt(i);
			String m_name = (mj_met).GetName();
			if (m_name.equals(method_name))
				return mj_met;
		}
		if (extend_name != null)
			return class_extends.SearchMethod(method_name);

		return null;
	}

	public boolean Repeated_Method(String method_name) {
		int sz = methods.size();
		for (int i = 0; i < sz; i++) {
			String m_name = ((MJMethod) methods.elementAt(i)).GetName();
			if (m_name.equals(method_name))
				return true;
		}
		return false;
	}

	public void Extentions(Vector v) {
		v.addElement(name);
		if (extend_name != null)
			class_extends.Extentions(v);
	}

	public boolean CheckType(MJType n_type) {
		int sz = 0;
		if (!(n_type instanceof MJObj))
			return false;
		String has_ext = ((MJObj) n_type).GetExtenName();
		Vector names = new Vector();

		if (has_ext != null)
			((MJObj) n_type).Extentions(names);
		else
			names.addElement(((MJObj) n_type).GetClassName());

		sz = names.size();
		for (int i = 0; i < sz; i++) {
			String c_name = (String) names.elementAt(i);
			if (c_name.equals(name))
				return true;
		}
		return false;
	}

}
