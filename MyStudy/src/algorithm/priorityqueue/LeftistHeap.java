package algorithm.priorityqueue;

/**
 * ×óÊ½¶Ñ
 * 
 * @author zhaojian
 * 
 * @param <AnyType>
 */
public class LeftistHeap<AnyType extends Comparable<? super AnyType>> {
	private LeftistNode<AnyType> root; // root

	public LeftistHeap() {
		root = null;
	}

	public static class LeftistNode<AnyType> {
		LeftistNode(AnyType theElement) {
			this(theElement, null, null);
		}

		LeftistNode(AnyType theElement, LeftistNode<AnyType> lt,
				LeftistNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
			npl = 0;
		}

		AnyType element; // The data in the node
		LeftistNode<AnyType> left; // Left child
		LeftistNode<AnyType> right; // Right child
		int npl; // null path length

		@Override
		public String toString() {
			return element.toString();
		}
	}

	public void insert(AnyType x) {
		root = merge(new LeftistNode<AnyType>(x), root);
	}

	public AnyType findMin() {
		if (isEmpty())
			throw null;
		return root.element;
	}

	public boolean isEmpty() {
		return root == null;
	}

	public void makeEmpty() {
		root = null;
	}

	public AnyType deleteMin() {
		if (isEmpty())
			return null;

		AnyType minItem = root.element;
		root = merge(root.left, root.right);

		return minItem;
	}

	public void merge(LeftistHeap<AnyType> rhs) {
		if (this == rhs) // Avoid aliasing problems
			return;

		root = merge(root, rhs.root);
		rhs.root = null;
	}

	private LeftistNode<AnyType> merge(LeftistNode<AnyType> h1,
			LeftistNode<AnyType> h2) {
		if (h1 == null)
			return h2;
		if (h2 == null)
			return h1;
		if (h1.element.compareTo(h2.element) < 0)
			return merge1(h1, h2);
		else
			return merge1(h2, h1);
	}

	private LeftistNode<AnyType> merge1(LeftistNode<AnyType> h1,
			LeftistNode<AnyType> h2) {
		if (h1.left == null) // Single node
			h1.left = h2; // Other fields in h1 already accurate
		else {
			h1.right = merge(h1.right, h2);
			if (h1.left.npl < h1.right.npl)
				swapChildren(h1);
			h1.npl = h1.right.npl + 1;
		}
		return h1;
	}

	private static <AnyType> void swapChildren(LeftistNode<AnyType> t) {
		LeftistNode<AnyType> tmp = t.left;
		t.left = t.right;
		t.right = tmp;
	}

	public VTreeNode createVTreeNode() {
		if (root == null)
			return null;
		VTreeNode vnode = new VTreeNode(root);
		vnode.setLeft(createVTreeNode(root.left));
		vnode.setRight(createVTreeNode(root.right));
		return vnode;
	}

	private VTreeNode createVTreeNode(LeftistNode<AnyType> node) {
		if (node == null)
			return null;
		VTreeNode vnode = new VTreeNode(node);
		vnode.setLeft(createVTreeNode(node.left));
		vnode.setRight(createVTreeNode(node.right));
		return vnode;
	}
}
