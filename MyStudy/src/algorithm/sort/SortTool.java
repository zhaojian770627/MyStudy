package algorithm.sort;

import algorithm.sort.base.IWaitNext;

public class SortTool {
	/**
	 * ≤Â»Î≈≈–Ú
	 * 
	 * @param <AnyType>
	 * @param a
	 * @param wn
	 * @throws InterruptedException
	 */
	public static <AnyType extends Comparable<? super AnyType>> void insertionSort(
			AnyType[] a, IWaitNext wn) throws InterruptedException {
		int j;
		for (int p = 1; p < a.length; p++) {

			wn.waitNext();

			AnyType tmp = a[p];
			for (j = p; j > 0 && tmp.compareTo(a[j - 1]) < 0; j--)
				a[j] = a[j - 1];
			a[j] = tmp;

			wn.notify(1, 1);

			wn.continueNext();
		}
	}

	/**
	 * œ£∂˚≈≈–Ú
	 * 
	 * @param <AnyType>
	 * @param a
	 * @param wn
	 * @throws InterruptedException
	 */
	public static <AnyType extends Comparable<? super AnyType>> void shellsort(
			AnyType[] a, IWaitNext wn) throws InterruptedException {
		int j;

		for (int gap = a.length / 2; gap > 0; gap /= 2)
			for (int i = gap; i < a.length; i++) {

				wn.waitNext();

				AnyType tmp = a[i];
				for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap)
					a[j] = a[j - gap];
				a[j] = tmp;

				wn.notify(1, 1);

				wn.continueNext();
			}
	}

	/**
	 * ∂—≈≈–Ú
	 * 
	 * @param <AnyType>
	 * @param a
	 * @throws InterruptedException
	 */
	public static <AnyType extends Comparable<? super AnyType>> void heapsort(
			AnyType[] a, IWaitNext wn) throws InterruptedException {
		wn.waitNext();
		wn.notify(1, 1);
		wn.continueNext();

		for (int i = a.length / 2; i >= 0; i--)
			// Ω®∂—
			percDown(a, i, a.length);

		wn.waitNext();
		wn.notify(1, 1);
		wn.continueNext();

		for (int i = a.length - 1; i > 0; i--) {
			wn.waitNext();

			swapReferences(a, 0, i);
			percDown(a, 0, i);

			wn.notify(1, 1);
			wn.continueNext();
		}
	}

	private static <AnyType extends Comparable<? super AnyType>> void percDown(
			AnyType[] a, int i, int n) {
		int child;
		AnyType tmp;

		for (tmp = a[i]; leftChild(i) < n; i = child) {
			child = leftChild(i);
			if (child != n - 1 && a[child].compareTo(a[child + 1]) < 0)
				child++;
			if (tmp.compareTo(a[child]) < 0)
				a[i] = a[child];
			else
				break;
		}
		a[i] = tmp;
	}

	private static int leftChild(int i) {
		return 2 * i + 1;
	}

	public static <AnyType> void swapReferences(AnyType[] a, int index1,
			int index2) {
		AnyType tmp = a[index1];
		a[index1] = a[index2];
		a[index2] = tmp;
	}

	/**
	 * πÈ≤¢≈≈–Ú
	 * 
	 * @param <AnyType>
	 * @param a
	 * @throws InterruptedException
	 */
	public static <AnyType extends Comparable<? super AnyType>> void mergeSort(
			AnyType[] a, IWaitNext wn) throws InterruptedException {
		AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
		mergeSort(a, tmpArray, 0, a.length - 1, wn);
	}

	private static <AnyType extends Comparable<? super AnyType>> void mergeSort(
			AnyType[] a, AnyType[] tmpArray, int left, int right, IWaitNext wn)
			throws InterruptedException {
		if (left < right) {
			int center = (left + right) / 2;
			mergeSort(a, tmpArray, left, center, wn);
			mergeSort(a, tmpArray, center + 1, right, wn);

			wn.waitNext();

			merge(a, tmpArray, left, center + 1, right);

			wn.notify(1, 1);
			wn.continueNext();
		}
	}

	private static <AnyType extends Comparable<? super AnyType>> void merge(
			AnyType[] a, AnyType[] tmpArray, int leftPos, int rightPos,
			int rightEnd) {
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		while (leftPos <= leftEnd && rightPos <= rightEnd)
			if (a[leftPos].compareTo(a[rightPos]) <= 0)
				tmpArray[tmpPos++] = a[leftPos++];
			else
				tmpArray[tmpPos++] = a[rightPos++];

		while (leftPos <= leftEnd)
			tmpArray[tmpPos++] = a[leftPos++];

		while (rightPos <= rightEnd)
			tmpArray[tmpPos++] = a[rightPos++];

		for (int i = 0; i < numElements; i++, rightEnd--)
			a[rightEnd] = tmpArray[rightEnd];
	}
	
	
	private static final int CUTOFF = 3;

    /**
     * øÏÀŸ≈≈–Ú
     * 
     * @param <AnyType>
     * @param a
     */
    public static <AnyType extends Comparable<? super AnyType>> void quicksort(
                    AnyType[] a) {
            quicksort(a, 0, a.length - 1);
    }

    /**
     * Return median of left, center, and right. Order these and hide the pivot.
     */
    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(
                    AnyType[] a, int left, int right) {
            int center = (left + right) / 2;
            if (a[center].compareTo(a[left]) < 0)
                    swapReferences(a, left, center);
            if (a[right].compareTo(a[left]) < 0)
                    swapReferences(a, left, right);
            if (a[right].compareTo(a[center]) < 0)
                    swapReferences(a, center, right);

            // Place pivot at position right -1
            swapReferences(a, center, right - 1);
            return a[right - 1];
    }

    private static <AnyType extends Comparable<? super AnyType>> void quicksort(
                    AnyType[] a, int left, int right) {
            if (left + CUTOFF <= right) {
                    AnyType pivot = median3(a, left, right);

                    // Begin partitioning
                    int i = left, j = right - 1;
                    for (;;) {
                            while (a[++i].compareTo(pivot) < 0) {
                            }
                            while (a[--i].compareTo(pivot) > 0) {
                            }

                            if (i < j)
                                    swapReferences(a, i, j);
                            else
                                    break;
                    }

                    // Restore pivot
                    swapReferences(a, left, i - 1);

                    // Sort small elements
                    quicksort(a, left, i - 1);

                    // Sort large elements
                    quicksort(a, i + 1, right);
            } else
                    // Do an insertion sort on the subarray
                    insertionSort(a, left, right);
    }

    private static <AnyType extends Comparable<? super AnyType>> void insertionSort(
                    AnyType[] a, int left, int right) {
            for (int p = left + 1; p <= right; p++) {
                    AnyType tmp = a[p];
                    int j;
                    for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                            a[j] = a[j - 1];
                    a[j] = tmp;
            }
    }
}
