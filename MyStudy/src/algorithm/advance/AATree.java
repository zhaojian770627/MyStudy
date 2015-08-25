package algorithm.advance;

/**
 * 
 * @author zhaojian AATree
 * @param <AnyType>
 */
public class AATree<AnyType extends Comparable<? super AnyType>> {

        /**
         * Construct the tree
         */
        public AATree() {
                nullNode = new AANode<AnyType>(null, null, null);
                nullNode.left = nullNode.right = nullNode;
                nullNode.level = 0;
                root = nullNode;
        }

        /**
         * Skew primitive for AA-trees.
         * 
         * @param t
         *            the node that roots the tree
         * @return the new root after the rotation.
         */
        private AANode<AnyType> skew(AANode<AnyType> t) {
                if (t.left.level == t.level)
                        t = rotateWithLeftChild(t);
                return t;
        }

        /**
         * Split primitive for AA_trees
         * 
         * @param t
         *            the node that roots the tree
         * @return the new root after the rotation
         */
        private AANode<AnyType> split(AANode<AnyType> t) {
                if (t.right.right.level == t.level) {
                        t = rotateWithRightChild(t);
                        t.level++;
                }
                return t;
        }

        public void insert(AnyType x) {
                root = insert(x, root);
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
        private AANode<AnyType> insert(AnyType x, AANode<AnyType> t) {
                if (t == nullNode)
                        return new AANode<AnyType>(x, nullNode, nullNode);

                int compareResult = x.compareTo(t.element);

                if (compareResult < 0)
                        t.left = insert(x, t.left);
                else if (compareResult > 0)
                        t.right = insert(x, t.right);
                else
                        return t;

                t = skew(t);
                t = split(t);
                return t;
        }

        /**
         * Internal method to remove from a subtree.
         * 
         * @param x
         *            the item to remove.
         * @param t
         *            the node that roots the subtree.
         * @return the new root of the subtree
         */
        private AANode<AnyType> remove(AnyType x, AANode<AnyType> t) {
                if (t != nullNode) {
                        // Step 1:Search down the tree and set lastNode and deletedNode
                        lastNode = t;
                        if (x.compareTo(t.element) < 0)
                                t.left = remove(x, t.left);
                        else {
                                deletedNode = t;
                                t.right = remove(x, t.right);
                        }

                        // Step 2:If at the bottom of the tree and x is present,we remove
                        if (t == lastNode) {
                                if (deletedNode == nullNode
                                                || x.compareTo(deletedNode.element) != 0)
                                        return t; // Item not found;do nothing

                                deletedNode.element = t.element;
                                t = t.right;
                        }
                        // Step 3:Otherwise,we are not at the bottom;rebalance
                        else if (t.left.level < t.level - 1 || t.right.level < t.level - 1) {
                                if (t.right.level > --t.level)
                                        t.right.level = t.level;
                                t = skew(t);
                                t.right = skew(t.right);
                                t.right.right = skew(t.right.right);
                                t = split(t);
                                t.right = split(t.right);
                        }
                }
                return t;
        }

        /**
         * Rotate binary tree node with left child.
         */
        private AANode<AnyType> rotateWithLeftChild(AANode<AnyType> k2) {
                AANode<AnyType> k1 = k2.left;
                k2.left = k1.right;
                k1.right = k2;
                return k1;
        }

        /**
         * Rotate binary tree node with right child.
         */
        private AANode<AnyType> rotateWithRightChild(AANode<AnyType> k1) {
                AANode<AnyType> k2 = k1.right;
                k1.right = k2.left;
                k2.left = k1;
                return k2;
        }

        private static class AANode<AnyType> {
                AANode(AnyType theElement, AANode<AnyType> lt, AANode<AnyType> rt) {
                        element = theElement;
                        left = lt;
                        right = rt;
                        level = 1;
                }

                AnyType element; // The data in the node
                AANode<AnyType> left; // Left child
                AANode<AnyType> right; // Right child
                int level;
        }

        private AANode<AnyType> root;
        private AANode<AnyType> nullNode;

        private AANode<AnyType> lastNode;
        private AANode<AnyType> deletedNode;

        /**
         * @param args
         */
        public static void main(String[] args) {
                AATree<Integer> aaTree = new AATree<Integer>();
                aaTree.insert(10);
                aaTree.insert(11);
        }

}