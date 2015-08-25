package algorithm.hashmap;

public class QuadraticProbHashTable<AnyType> {
	private static final int DEFAULT_TABLE_SIZE = 101;
	private HashEntry<AnyType>[] array; // The array of elements
	private int theSize; // The number of occupied cells
	private int occupied; // The number of occupied cells

	public QuadraticProbHashTable() {
		this(DEFAULT_TABLE_SIZE);
	}

	public QuadraticProbHashTable(int size) {
		allocateArray(size);
		makeEmpty();
	}

	private void allocateArray(int arraySize) {
		array = new HashEntry[nextPrime(arraySize)];
	}

	public void makeEmpty() {
		theSize = 0;
		for (int i = 0; i < array.length; i++)
			array[i] = null;
	}

	public boolean contains(AnyType x) {
		int currentPos = findPos(x);
		return isActive(currentPos);
	}

	private int findPos(AnyType x) {
		int offset = 1;
		int currentPos = myhash(x);
		while (array[currentPos] != null
				&& !array[currentPos].element.equals(x)) {
			currentPos += offset;
			offset += 2;
			if (currentPos >= array.length)
				currentPos -= array.length;
		}
		return currentPos;
	}

	private boolean isActive(int currentPos) {
		return array[currentPos] != null && array[currentPos].isActive;
	}

	private int myhash(AnyType x) {
		int hashVal = x.hashCode();

		hashVal %= array.length;
		if (hashVal < 0)
			hashVal += array.length;

		return hashVal;
	}

	public boolean insert(AnyType x) {
		int currentPos = findPos(x);
		if (isActive(currentPos))
			return false;
		array[currentPos] = new HashEntry<AnyType>(x, true);
		theSize++;

		if (++occupied > array.length / 2)
			rehash();
		
		return true;
	}

	private void rehash() {
		HashEntry<AnyType>[] oldArray = array;

		// Create a new double-sized, empty table
		allocateArray(2 * oldArray.length);
		occupied = 0;
		theSize = 0;

		// Copy table over
		for (HashEntry<AnyType> entry : oldArray)
			if (entry != null && entry.isActive)
				insert(entry.element);

	}

	public boolean remove(AnyType x) {
		int currentPos = findPos(x);
		if (isActive(currentPos)) {
			array[currentPos].isActive = false;
			theSize--;
			return true;
		}else
			return false;
	}

    /**
     * Get current size.
     * @return the size.
     */
    public int size( )
    {
        return theSize;
    }
    
    /**
     * Get length of internal table.
     * @return the size.
     */
    public int capacity( )
    {
        return array.length;
    }
    
	private static class HashEntry<AnyType> {
		public AnyType element; // the element
		public boolean isActive; // false if marked deleted

		public HashEntry(AnyType e) {
			this(e, true);
		}

		public HashEntry(AnyType e, boolean i) {
			element = e;
			isActive = i;
		}
	}

	private static int nextPrime(int n) {
		if (n % 2 == 0)
			n++;

		for (; !isPrime(n); n += 2)
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime. Not an efficient algorithm.
	 * 
	 * @param n
	 *            the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime(int n) {
		if (n == 2 || n == 3)
			return true;

		if (n == 1 || n % 2 == 0)
			return false;

		for (int i = 3; i * i <= n; i += 2)
			if (n % i == 0)
				return false;

		return true;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		QuadraticProbHashTable<Integer> H=new QuadraticProbHashTable<Integer>();
		long startTime = System.currentTimeMillis();
		
		System.out.println("³ß´ç:" + H.capacity());
		
		for(int i=0;i<50;i++)
		{
			H.insert(i);
		}
		
		if(H.contains(1))
			System.out.println("1°üº¬");
		
		System.out.println("³ß´ç:" + H.capacity());
		
		
		H.insert(102);
		H.insert(103);
		H.insert(104);
		System.out.println("³ß´ç:" + H.capacity());
		long endTime = System.currentTimeMillis();

		System.out.println("Elapsed time: " + (endTime - startTime));
	}

}
