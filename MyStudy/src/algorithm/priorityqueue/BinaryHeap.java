package algorithm.priorityqueue;

public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
	private static final int DEFAULT_CAPACITY = 10;
	private int currentSize; // Number of elements in heap
	private AnyType[] array; // The heap array

	public BinaryHeap() {
		this(DEFAULT_CAPACITY);
	}

	public BinaryHeap(int capacity) {
		currentSize = 0;
		array = (AnyType[]) new Comparable[capacity + 1];
	}

	public BinaryHeap(AnyType[] items) {
		currentSize = items.length;
		array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (AnyType item : items)
			array[i++] = item;
		buildHeap();
	}

	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	public AnyType findMin() {
		if (isEmpty())
			return null;
		return array[1];
	}

	public AnyType deleteMin() {
		if (isEmpty())
			return null;

		AnyType minItem = findMin();
		array[1] = array[currentSize--];
		percolateDown(1);
		return minItem;
	}

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole
	 *            the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		int child;
		AnyType tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize
					&& array[child + 1].compareTo(array[child]) < 0)
				child++;
			if (array[child].compareTo(tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	private void enlargeArray(int newSize) {
		AnyType[] old = array;
		array = (AnyType[]) new Comparable[newSize];
		for (int i = 0; i < old.length; i++)
			array[i] = old[i];
	}

	public void insert(AnyType x) {
		if (currentSize == array.length - 1)
			enlargeArray(array.length * 2 + 1);

		int hole = ++currentSize;
		for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;
	}

	public int getCurrentSize() {
		return currentSize;
	}

	public void setCurrentSize(int currentSize) {
		this.currentSize = currentSize;
	}

	public AnyType get(int i) {
		return array[i];
	}
}
