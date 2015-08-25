package seven.Symbol;

public class TestTable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Table<String> table = new Table<String>();
		Symbol x = Symbol.symbol("x");
		Symbol y = Symbol.symbol("y");
		Symbol z = Symbol.symbol("z");
		table.put(x, "x0");
		table.put(y, "y0");
		table.put(z, "z0");
		table.beginScope();
		table.put(x, "x1");
		table.put(y, "y1");
		table.beginScope();
		table.put(x, "x2");
		table.put(y, "y2");
		printValue(table, x);
		printValue(table, y);
		printValue(table, z);
		table.endScope();
		printValue(table, x);
		printValue(table, y);
		printValue(table, z);
		table.endScope();
		printValue(table, x);
		printValue(table, y);
		printValue(table, z);
	}

	static void printValue(Table<String> table, Symbol s) {
		System.out.println(table.get(s).toString());
	}
}
