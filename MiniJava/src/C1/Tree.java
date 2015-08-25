package C1;

public class Tree {
	Tree left;
	int key;
	Tree right;

	Tree(Tree l, int k, Tree r) {
		left = l;
		key = k;
		right = r;
	}

	Tree insert(int key, Tree t) {
		if (t == null)
			return new Tree(null, key, null);
		else if (key < t.key)
			return new Tree(insert(key, t.left), t.key, t.right);
		else if (key > t.key)
			return new Tree(t.left, t.key, insert(key, t.right));
		else
			return new Tree(t.left, key, t.right);
	}

	boolean search(int key, Tree t) {
		if (t == null)
			return false;

		if (t.key == key)
			return true;
		else if (key < t.key)
			return search(key, t.left);
		else
			return search(key, t.right);
	}
}
