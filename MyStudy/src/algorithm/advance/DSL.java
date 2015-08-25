package algorithm.advance;

import java.io.IOException;

public class DSL<AnyType extends Comparable<? super AnyType>> {
        /**
         * Construct the DSL
         * 
         * @param inf
         *            the largest Comparable
         */
        public DSL(AnyType inf) {
                infinity = inf;
                bottom = new SkipNode<AnyType>(null);
                bottom.right = bottom.down = bottom;
                tail = new SkipNode<AnyType>(infinity);
                tail.right = tail;
                header = new SkipNode<AnyType>(infinity, tail, bottom);
        }

        /**
         * Find an item in the DSL
         * 
         * @param x
         *            the item to search for
         * @return the true if not found
         */
        public boolean contains(AnyType x) {
                SkipNode<AnyType> current = header;
                bottom.element = x;
                for (;;) {
                        int compareResult = x.compareTo(current.element);
                        if (compareResult < 0)
                                current = current.down;
                        else if (compareResult > 0)
                                current = current.right;
                        else
                                return current != bottom;
                }
        }

        /**
         * Insert into the DSL
         * 
         * @param x
         *            the item to insert
         */
        public void insert(AnyType x) {
                SkipNode<AnyType> current = header;
                bottom.element = x;
                while (current != bottom) {
                        while (current.element.compareTo(x) < 0)
                                current = current.right;

                        // If gap size is 3 or at bottom level and
                        // must insert. then promote middle element
                        if (current.down.right.right.element.compareTo(current.element) < 0) {
                                current.right = new SkipNode<AnyType>(current.element,
                                                current.right, current.down.right.right);
                                current.element = current.down.right.element;
                        } else
                                current = current.down;
                }
                if (header.right != tail)
                        header = new SkipNode<AnyType>(infinity, tail, header);
        }

        private static class SkipNode<AnyType> {
                SkipNode(AnyType theElement) {
                        this(theElement, null, null);
                }

                SkipNode(AnyType theElement, SkipNode<AnyType> rt, SkipNode<AnyType> dt) {
                        element = theElement;
                        right = rt;
                        down = dt;
                }

                AnyType element; // The data in the node
                SkipNode<AnyType> right; // Right link
                SkipNode<AnyType> down; // Down link
        }

        private AnyType infinity;
        private SkipNode<AnyType> header;
        private SkipNode<AnyType> bottom = null;
        private SkipNode<AnyType> tail = null;

        /**
         * @param args
         * @throws IOException 
         */
        public static void main(String[] args) throws IOException {
                DSL<Integer> dsl = new DSL<Integer>(Integer.MAX_VALUE);
                int[] a = { 5, 15, 10, 25, 20, 30, 27, 35, 40, 45, 50, 25 };
                // dsl.insert(11);
                // dsl.insert(13);
                // dsl.insert(12);
                for (int i : a) {
                        dsl.insert(i);
                }
                System.in.read();
        }
}