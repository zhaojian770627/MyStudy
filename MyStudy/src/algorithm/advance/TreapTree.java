package algorithm.advance;

import java.util.Random;

/**
 * 
 * @author zhaojian
 *
 * @param <AnyType>
 */
public class TreapTree<AnyType extends Comparable<? super AnyType>> {
	public TreapTree() {

	}

	private static class TreapNode<AnyType> {
		// Constructors
		private AnyType element;
		private TreapNode<AnyType> left;
		private TreapNode<AnyType> right;
		private int priority;

		private static Random randomObj = new Random();

		public TreapNode(AnyType theElement) {
			this(theElement, null, null);
		}

		public TreapNode(AnyType theElement, TreapNode<AnyType> lt,
				TreapNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
			priority = randomObj.hashCode();
		}
	}

	/**
	 * Rotate binary tree node with left child.
	 */
	private TreapNode<AnyType> rotateWithLeftChild(TreapNode<AnyType> k2) {
		TreapNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		return k1;
	}

	/**
	 * Rotate binary tree node with right child.
	 */
	private TreapNode<AnyType> rotateWithRightChild(TreapNode<AnyType> k1) {
		TreapNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		return k2;
	}

	/**
	 * Internal method to insert into a subtree
	 * 
	 * @param x
	 *            the item to insert
	 * @param t
	 *            the node that roots the subtree
	 * @return the new root of the subtree
	 */
	private TreapNode<AnyType> insert(AnyType x, TreapNode<AnyType> t) {
		if (t == nullNode)
			return new TreapNode<AnyType>(x, nullNode, nullNode);
		
		int compareResult=x.compareTo(t.element);
		
		if(compareResult<0){
			t.left=insert(x,t.left);
			if(t.left.priority<t.priority)
				t=rotateWithLeftChild(t);
		}else if(compareResult>0)
		{
			t.right=insert(x, t.right);
			if(t.right.priority<t.priority)
				t=rotateWithRightChild(t);
		}
		
		// Otherwise,it's a duplicate;do nothing
		return t;
	}
	
	private TreapNode<AnyType> remove(AnyType x,TreapNode<AnyType> t)
	{
		if(t!=nullNode)
		{
			int compareResult=x.compareTo(t.element);
			
			if(compareResult<0)
				t.left=remove(x, t.left);
			else if(compareResult>0)
				t.right=remove(x, t.right);
			else{
				// Match found
				if(t.left.priority<t.right.priority)
					t=rotateWithLeftChild(t);
				else
					t=rotateWithRightChild(t);
				
				if(t!=nullNode)	// Continue on down
					t=remove(x, t);
				else
					t.left=nullNode;
			}
		}
		return t;
	}

	private TreapNode<AnyType> nullNode;
}
