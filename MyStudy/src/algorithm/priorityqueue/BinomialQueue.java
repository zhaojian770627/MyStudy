package algorithm.priorityqueue;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * ¶þÏî¶ÓÁÐ
 * 
 * @author zhaojian
 * 
 * @param <AnyType>
 */
public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {

	private static final int DEFAULT_TREES = 1;
	private int currentSize; // # items in priority queue
	private BinNode<AnyType>[] theTrees; // An array of tree roots

	private static class BinNode<AnyType> {
		// Constructors
		BinNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinNode(AnyType theElement, BinNode<AnyType> lt, BinNode<AnyType> nt) {
			element = theElement;
			leftChild = lt;
			nextSibling = nt;
		}

		AnyType element; // The data in the node
		BinNode<AnyType> leftChild; // Left child
		BinNode<AnyType> nextSibling; // Right child

		@Override
		public String toString() {
			return element.toString();
		}
	}

	/**
	 * Construct the binomial queue.
	 */
	public BinomialQueue() {
		theTrees = new BinNode[DEFAULT_TREES];
		makeEmpty();
	}

	/**
	 * Construct with a single item.
	 */
	public BinomialQueue(AnyType item) {
		currentSize = 1;
		theTrees = new BinNode[1];
		theTrees[0] = new BinNode<AnyType>(item, null, null);
	}

	private void expandTheTrees(int newNumTrees) {
		BinNode<AnyType>[] old = theTrees;
		int oldNumTrees = theTrees.length;

		theTrees = new BinNode[newNumTrees];
		for (int i = 0; i < oldNumTrees; i++)
			theTrees[i] = old[i];
		for (int i = oldNumTrees; i < newNumTrees; i++)
			theTrees[i] = null;
	}

	/**
	 * Insert into the priority queue, maintaining heap order. This
	 * implementation is not optimized for O(1) performance.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(AnyType x) {
		merge(new BinomialQueue<AnyType>(x));
	}

	/**
	 * Merge rhs into the priority queue. rhs becomes empty. rhs must be
	 * different from this.
	 * 
	 * @param rhs
	 *            the other binomial queue.
	 */
	public void merge(BinomialQueue<AnyType> rhs) {
		if (this == rhs) // Avoid aliasing problems
			return;

		currentSize += rhs.currentSize;

		if (currentSize > capacity()) {
			int newNumTrees = Math.max(theTrees.length, rhs.theTrees.length) + 1;
			expandTheTrees(newNumTrees);
		}

		BinNode<AnyType> carry = null;
		for (int i = 0, j = 1; j <= currentSize; i++, j *= 2) {
			BinNode<AnyType> t1 = theTrees[i];
			BinNode<AnyType> t2 = i < rhs.theTrees.length ? rhs.theTrees[i]
					: null;

			int whichCase = t1 == null ? 0 : 1;
			whichCase += t2 == null ? 0 : 2;
			whichCase += carry == null ? 0 : 4;

			switch (whichCase) {
			case 0: /* No trees */
			case 1: /* Only this */
				break;
			case 2: /* Only rhs */
				theTrees[i] = t2;
				rhs.theTrees[i] = null;
				break;
			case 4: /* Only carry */
				theTrees[i] = carry;
				carry = null;
				break;
			case 3: /* this and rhs */
				carry = combineTrees(t1, t2);
				theTrees[i] = rhs.theTrees[i] = null;
				break;
			case 5: /* this and carry */
				carry = combineTrees(t1, carry);
				theTrees[i] = null;
				break;
			case 6: /* rhs and carry */
				carry = combineTrees(t2, carry);
				rhs.theTrees[i] = null;
				break;
			case 7: /* All three */
				theTrees[i] = carry;
				carry = combineTrees(t1, t2);
				rhs.theTrees[i] = null;
				break;
			}
		}

		for (int k = 0; k < rhs.theTrees.length; k++)
			rhs.theTrees[k] = null;
		rhs.currentSize = 0;
	}

	/**
	 * Return the result of merging equal-sized t1 and t2.
	 */
	private BinNode<AnyType> combineTrees(BinNode<AnyType> t1,
			BinNode<AnyType> t2) {
		if (t1.element.compareTo(t2.element) > 0)
			return combineTrees(t2, t1);
		t2.nextSibling = t1.leftChild;
		t1.leftChild = t2;
		return t1;
	}

	/**
	 * Find the smallest item in the priority queue.
	 * 
	 * @return the smallest item, or throw UnderflowException if empty.
	 */
	public AnyType findMin() {
		if (isEmpty())
			return null;

		return theTrees[findMinIndex()].element;
	}

	/**
	 * Find index of tree containing the smallest item in the priority queue.
	 * The priority queue must not be empty.
	 * 
	 * @return the index of tree containing the smallest item.
	 */
	private int findMinIndex() {
		int i;
		int minIndex;

		for (i = 0; theTrees[i] == null; i++)
			;

		for (minIndex = i; i < theTrees.length; i++)
			if (theTrees[i] != null
					&& theTrees[i].element
							.compareTo(theTrees[minIndex].element) < 0)
				minIndex = i;

		return minIndex;
	}

	/**
	 * Remove the smallest item from the priority queue.
	 * 
	 * @return the smallest item, or throw UnderflowException if empty.
	 */
	public AnyType deleteMin() {
		if (isEmpty())
			return null;

		int minIndex = findMinIndex();
		AnyType minItem = theTrees[minIndex].element;

		BinNode<AnyType> deletedTree = theTrees[minIndex].leftChild;

		// Construct H''
		BinomialQueue<AnyType> deletedQueue = new BinomialQueue<AnyType>();
		deletedQueue.expandTheTrees(minIndex + 1);

		deletedQueue.currentSize = (1 << minIndex) - 1;
		for (int j = minIndex - 1; j >= 0; j--) {
			deletedQueue.theTrees[j] = deletedTree;
			deletedTree = deletedTree.nextSibling;
			deletedQueue.theTrees[j].nextSibling = null;
		}

		// Construct H'
		theTrees[minIndex] = null;
		currentSize -= deletedQueue.currentSize + 1;

		merge(deletedQueue);

		return minItem;
	}

	/**
	 * Make the priority queue logically empty.
	 */
	public void makeEmpty() {
		currentSize = 0;
		for (int i = 0; i < theTrees.length; i++)
			theTrees[i] = null;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	/**
	 * Return the capacity.
	 */
	private int capacity() {
		return (1 << theTrees.length) - 1;
	}

	public int getCurrentSize() {
		return currentSize;
	}
	/***************************************************************************/
	public DefaultMutableTreeNode createTreeNode() {
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Queue");
		for (int i = 0; i < theTrees.length; i++) {
			BinNode<AnyType> t = theTrees[i];
			DefaultMutableTreeNode node;
			if (t == null)
				node = new DefaultMutableTreeNode("¿Õ");
			else {
				node = new DefaultMutableTreeNode(t);
				createChildNode(node, t);
			}
			rootNode.add(node);
		}
		return rootNode;
	}

	private void createChildNode(DefaultMutableTreeNode parent,
			BinNode<AnyType> t) {
		DefaultMutableTreeNode leftNode;
		if (t.leftChild == null)
			leftNode = new DefaultMutableTreeNode("×óÊ÷¿Õ");
		else {
			leftNode = new DefaultMutableTreeNode("×óÊ÷ "
					+ t.leftChild.element.toString());
			createChildNode(leftNode, t.leftChild);
		}

		DefaultMutableTreeNode rightNode;
		if (t.nextSibling == null)
			rightNode = new DefaultMutableTreeNode("ÐÖµÜ¿Õ");
		else {
			rightNode = new DefaultMutableTreeNode("ÐÖµÜ "
					+ t.nextSibling.element.toString());
			createChildNode(rightNode, t.nextSibling);
		}

		parent.add(leftNode);
		parent.add(rightNode);
	}
}
