package five;

public class HashT {
	final int SIZE = 256;
	Bucket table[] = new Bucket[SIZE];

	private int hash(String s) {
		int h = 0;
		for (int i = 0; i < s.length(); i++)
			h = h * 65599 + s.charAt(i);
		return h;
	}

	void insert(String s, Object b) {
		int index = hash(s) % SIZE;
		table[index] = new Bucket(s, b, table[index]);
	}

	Object lookup(String s) {
		int index = hash(s) % SIZE;
		for (Bucket b = table[index]; b != null; b = b.next)
			if (s.equals(b.key))
				return b.binding;
		return null;
	}

	void pop(String s) {
		int index = hash(s) % SIZE;
		table[index] = table[index].next;
	}
}
