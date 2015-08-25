package algorithm;

public class MaxSub {
	/**
	 * Cubic maximum contiguous subsequence sub algorithm
	 * 
	 * @param a
	 * @return
	 */
	public static int maxSubSum1(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++)
			for (int j = i; j < a.length; j++) {
				int thisSum = 0;

				for (int k = i; k <= j; k++)
					thisSum += a[k];
				if (thisSum > maxSum)
					maxSum = thisSum;
			}
		return maxSum;
	}

	public static int maxSubSum2(int[] a) {
		int maxSum = 0;
		for (int i = 0; i < a.length; i++) {
			int thisSum = 0;
			for (int j = i; j < a.length; j++) {
				thisSum += a[j];
				if (thisSum > maxSum)
					maxSum = thisSum;
			}
		}
		return maxSum;
	}

	public static int maxSubSum3(int[] a, int left, int right) {
		if (left == right) // Base case
			if (a[left] > 0)
				return a[left];
			else
				return 0;

		int center = (left + right) / 2;
		int maxLeftSum = maxSubSum3(a, left, center);
		int maxRightSum = maxSubSum3(a, center + 1, right);

		int maxLeftBorderSum = 0, leftBorderSum = 0;
		for (int i = center; i >= left; i--) {
			leftBorderSum += a[i];
			if (leftBorderSum > maxLeftBorderSum)
				maxLeftBorderSum = leftBorderSum;
		}

		int maxRightBorderSum = 0, rightBorderSum = 0;
		for (int i = center + 1; i <= right; i++) {
			rightBorderSum += a[i];
			if (rightBorderSum > maxRightBorderSum)
				maxRightBorderSum = rightBorderSum;
		}

		return Math.max(Math.max(maxLeftSum, maxRightSum), maxLeftBorderSum
				+ maxRightBorderSum);
	}

	public static int maxSubSum4(int[] a) {
		int maxSum = 0, thisSum = 0;

		for (int j = 0; j < a.length; j++) {
			thisSum += a[j];
			if (thisSum > maxSum)
				maxSum = thisSum;
			else if (thisSum < 0)
				thisSum = 0;
		}
		return maxSum;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = { -2, 11, -4, 13, -5, -2 };
		int sum = maxSubSum4(a);
		// int sum=maxSubSum3(a,0,a.length-1);
		System.out.println(sum);
	}

}
