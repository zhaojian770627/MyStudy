package javacc.d.jtb.visitor;
import javacc.d.jtb.syntaxtree.ArrayType;
import javacc.d.jtb.syntaxtree.BooleanType;
import javacc.d.jtb.syntaxtree.ClassDeclaration;
import javacc.d.jtb.syntaxtree.ClassExtendsDeclaration;
import javacc.d.jtb.syntaxtree.FormalParameter;
import javacc.d.jtb.syntaxtree.Identifier;
import javacc.d.jtb.syntaxtree.IntegerType;
import javacc.d.jtb.syntaxtree.MainClass;
import javacc.d.jtb.syntaxtree.MethodDeclaration;
import javacc.d.jtb.syntaxtree.Type;
import javacc.d.jtb.syntaxtree.VarDeclaration;

public class TableBuilderVisitor extends GJNoArguDepthFirst<String>  {
	private static final String INT = "0";
	private static final String BOOLEAN = "1";
	private static final String INTARRAY = "2";

	private static final String TYPEERROR = "Type error";
	private static final boolean VERBOSE = false;

	private SymbolTable symboltable;
	private Class currentClass;
	private Method currentMethod;

	public TableBuilderVisitor() {
		symboltable = new SymbolTable();
		currentClass = null;
		currentMethod = null;
	}

	public SymbolTable getTable() {
		return symboltable;
	}

	/**
	* f0 -> "class"
	* f1 -> Identifier()
	* f2 -> "{"
	* f3 -> "public"
	* f4 -> "static"
	* f5 -> "void"
	* f6 -> "main"
	* f7 -> "("
	* f8 -> "String"
	* f9 -> "["
	* f10 -> "]"
	* f11 -> Identifier()
	* f12 -> ")"
	* f13 -> "{"
	* f14 -> PrintStatement()
	* f15 -> "}"
	* f16 -> "}"
	*/
	public String visit(MainClass n) {
		String id;

		n.f0.accept(this);
		id = n.f1.accept(this);

		if(!symboltable.addClass(id, null)) {
			System.out.println(TYPEERROR);
			if(VERBOSE) {
				System.out.println("Main class already defined");
			}
			System.exit(0);
		}

		currentClass = symboltable.getClass(id);

		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);
		n.f13.accept(this);
		n.f14.accept(this);
		n.f15.accept(this);
		n.f16.accept(this);

		currentClass = null;
		return null;
	}

	/**
	* f0 -> "class"
	* f1 -> Identifier()
	* f2 -> "{"
	* f3 -> ( VarDeclaration() )*
	* f4 -> ( MethodDeclaration() )*
	* f5 -> "}"
	*/
	public String visit(ClassDeclaration n) {
		String id;

		n.f0.accept(this);
		id = n.f1.accept(this);

		if(!symboltable.addClass(id, null)) {
			System.out.println(TYPEERROR);
			if(VERBOSE) {
				System.out.println("Class " + id + " already defined");
			}
			System.exit(0);
		}

		currentClass = symboltable.getClass(id);

		n.f2.accept(this);
		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);

		currentClass = null;
		return null;
	}

	/**
	* f0 -> "class"
	* f1 -> Identifier()
	* f2 -> "extends"
	* f3 -> Identifier()
	* f4 -> "{"
	* f5 -> ( VarDeclaration() )*
	* f6 -> ( MethodDeclaration() )*
	* f7 -> "}"
	*/
	public String visit(ClassExtendsDeclaration n) {
		String id;
		String parent;

		n.f0.accept(this);
		id = n.f1.accept(this);
		n.f2.accept(this);
		parent = n.f3.accept(this);

		if(!symboltable.addClass(id, parent)) {
			System.out.println(TYPEERROR);
			if(VERBOSE) {
				System.out.println("Class " + id + " already defined");
			}
			System.exit(0);
		}

		currentClass = symboltable.getClass(id);

		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);

		currentClass = null;
		return null;
	}

	/**
	* f0 -> Type()
	* f1 -> Identifier()
	* f2 -> ";"
	*/
	public String visit(VarDeclaration n) {
		String type = n.f0.accept(this);
		String id = n.f1.accept(this);

		if(currentMethod != null) {
			if(!currentMethod.addVar(id, type)) {
				System.out.println(TYPEERROR);
				if(VERBOSE) {
					System.out.println("Variable " + id + " already defined in method " + currentMethod.id());
				}
				System.exit(0);
			}
		}
		else {
			if(!currentClass.addVar(id, type)) {
				System.out.println(TYPEERROR);
				if(VERBOSE) {
					System.out.println("Variable " + id + " already defined in Class " + currentClass.id());
				}
				System.exit(0);
			}
		}

		n.f2.accept(this);
		return null;
	}

	/**
	* f0 -> "public"
	* f1 -> Type()
	* f2 -> Identifier()
	* f3 -> "("
	* f4 -> ( FormalParameterList() )?
	* f5 -> ")"
	* f6 -> "{"
	* f7 -> ( VarDeclaration() )*
	* f8 -> ( Statement() )*
	* f9 -> "return"
	* f10 -> Expression()
	* f11 -> ";"
	* f12 -> "}"
	*/
	public String visit(MethodDeclaration n) {
		String type;
		String id;

		n.f0.accept(this);

		type = n.f1.accept(this);
		id = n.f2.accept(this);

		if(!currentClass.addMethod(id, type)) {
			System.out.println(TYPEERROR);
			if(VERBOSE) {
				System.out.println("Method " + id + " already defined in class " + currentClass.id());
			}
			System.exit(0);
		}

		currentMethod = currentClass.getMethod(id);

		n.f3.accept(this);
		n.f4.accept(this);
		n.f5.accept(this);
		n.f6.accept(this);
		n.f7.accept(this);
		n.f8.accept(this);
		n.f9.accept(this);
		n.f10.accept(this);
		n.f11.accept(this);
		n.f12.accept(this);

		currentMethod = null;
		return null;
	}


	/**
	* f0 -> Type()
	* f1 -> Identifier()
	*/
	public String visit(FormalParameter n) {
		String type = n.f0.accept(this);
		String id = n.f1.accept(this);

		if(!currentMethod.addParameter(id, type)) {
			System.out.println(TYPEERROR);
			if(VERBOSE) {
				System.out.println("Parameter " + id + " already defined in method " + currentMethod.id());
			}
			System.exit(0);
		}
		return null;
	}

	/**
	* f0 -> ArrayType()
	*       | BooleanType()
	*       | IntegerType()
	*       | Identifier()
	*/
	public String visit(Type n) {
		return n.f0.accept(this);
	}

	/**
	* f0 -> "int"
	* f1 -> "["
	* f2 -> "]"
	*/
	public String visit(ArrayType n) {
		n.f0.accept(this);
		n.f1.accept(this);
		n.f2.accept(this);
		return INTARRAY;
	}

	/**
	* f0 -> "boolean"
	*/
	public String visit(BooleanType n) {
		n.f0.accept(this);
		return BOOLEAN;
	}

	/**
	* f0 -> "int"
	*/
	public String visit(IntegerType n) {
		n.f0.accept(this);
		return INT;
	}

	/**
	* f0 -> <IDENTIFIER>
	*/
	public String visit(Identifier n) {
		n.f0.accept(this);
		return n.f0.toString(); 
        }
}