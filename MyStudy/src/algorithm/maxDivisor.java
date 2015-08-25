package algorithm;

public class maxDivisor {

	/**
	 * 辗转相除法求最大公因数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int m, n;
		m = 75;
		n = 15;
		if (m < n) {
			int t = m;
			m = n;
			n = t;
		}
		
		int r;
		// n除m
		r = m % n;
		while (r != 0) {
			m = n;
			n = r;
			r = m % n;
		}
		System.out.println("最大公因数:" + n);
	}

}
