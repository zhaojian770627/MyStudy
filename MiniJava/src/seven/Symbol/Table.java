package seven.Symbol;

import java.util.HashMap;

class Binder<V> {
	V value;
	Symbol prevtop;
	Binder<V> tail;

	Binder(V v, Symbol p, Binder<V> t) {
		value = v;
		prevtop = p;
		tail = t;
	}
}

/**
 * The Table class is similar to java.util.Dictionary, except that each key must
 * be a Symbol and there is a scope mechanism.
 */

public class Table<V> {
	private HashMap<Symbol, Binder<V>> dict = new HashMap<Symbol, Binder<V>>();
	private Symbol top;
	private Binder<V> marks;

	public Table() {
	}

	/**
	 * Gets the object associated with the specified symbol in the Table.
	 */
	public V get(Symbol key) {
		Binder<V> e = dict.get(key);
		if (e == null)
			return null;
		else
			return e.value;
	}

	/**
	 * Puts the specified value into the Table, bound to the specified Symbol.
	 */
	public void put(Symbol key, V value) {
		dict.put(key, new Binder<V>(value, top, dict.get(key)));
		top = key;
	}

	/**
	 * Remembers the current state of the Table.
	 */
	public void beginScope() {
		marks = new Binder<V>(null, top, marks);
		top = null;
	}

	/**
	 * Restores the table to what it was at the most recent beginScope that has
	 * not already been ended.
	 */
	public void endScope() {
		while (top != null) {
			Binder<V> e = dict.get(top);
			if (e.tail != null)
				dict.put(top, e.tail);
			else
				dict.remove(top);
			top = e.prevtop;
		}
		top = marks.prevtop;
		marks = marks.tail;
	}

	/**
	 * Returns an enumeration of the Table's symbols.
	 */
	public java.util.Set keys() {
		return dict.keySet();
	}
}