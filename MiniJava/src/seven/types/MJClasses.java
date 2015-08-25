package seven.types;

import java.util.Vector;

public class MJClasses extends MJType {
	private Vector mj_classes = new Vector();

	public String InsertClass(MJObj v_class) {
		String class_name = v_class.GetClassName();
		if (Repeated(class_name))
			return "Class double declaration " + class_name;
		mj_classes.addElement(v_class);
		return null;
	}

	public void Print() {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj cl = (MJObj) mj_classes.elementAt(i);
			cl.Print();
		}
	}

	public String GetType() {
		return "";
	}

	public boolean Repeated(String class_name) {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			String c_name = ((MJObj) mj_classes.elementAt(i)).GetClassName();
			if (c_name.equals(class_name))
				return true;
		}
		return false;
	}

	public String SetExtensions() {
		boolean has_error = false;
		String error_msg = "";
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_class = (MJObj) mj_classes.elementAt(i);
			String c_name = mj_class.GetClassName();
			String class_ext_name = mj_class.GetExtenName();
			if (class_ext_name != null) {
				if (c_name.equals(class_ext_name)) {
					error_msg = error_msg
							+ "\n       Class self extension in class "
							+ c_name;
					has_error = true;
				} else {
					MJObj ext_class = GetClass(class_ext_name);
					if (ext_class == null) {
						error_msg = error_msg
								+ "\n       Extend class does not exist "
								+ class_ext_name;
						has_error = true;
					} else
						mj_class.SetExtend(ext_class);
				}
			}

		}
		if (has_error)
			return error_msg;
		else
			return null;
	}

	public String SetFields() {
		boolean has_error = false;
		String error_msg = "";
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_class = (MJObj) mj_classes.elementAt(i);
			String aux = mj_class.SetFields(this);
			if (aux != null) {
				error_msg = error_msg + aux;
				has_error = true;
			}
		}
		if (has_error)
			return error_msg;
		return null;
	}

	public String SetMetArgs() {
		boolean has_error = false;
		String error_msg = "";
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_class = (MJObj) mj_classes.elementAt(i);
			String aux = mj_class.SetMethods(this);
			if (aux != null) {
				error_msg = error_msg + aux;
				has_error = true;
			}
		}
		if (has_error)
			return error_msg;
		return null;
	}

	public MJObj GetClass(String class_name) {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_c = (MJObj) mj_classes.elementAt(i);
			String c_name = mj_c.GetClassName();
			if (c_name.equals(class_name))
				return mj_c;
		}
		return null;
	}

	public MJType SearchType(String class_name) {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_c = (MJObj) mj_classes.elementAt(i);
			String c_name = mj_c.GetClassName();
			if (c_name.equals(class_name))
				return mj_c;
		}
		return null;
	}

	public boolean CheckType(MJType n_type) {
		if (!(n_type instanceof MJClasses))
			return false;
		else
			return true;
	}

	public void SetNames() {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_c = (MJObj) mj_classes.elementAt(i);
			mj_c.SortedFields();
			mj_c.SortedMethods();
		}
	}

	public void PrintNames() {
		int sz = mj_classes.size();
		for (int i = 0; i < sz; i++) {
			MJObj mj_c = (MJObj) mj_classes.elementAt(i);
			mj_c.PrintSortedFields();
		}
		for (int i = 0; i < sz; i++) {
			MJObj mj_c = (MJObj) mj_classes.elementAt(i);
			mj_c.PrintSortedMethods();
		}
		System.out.println();
	}
}