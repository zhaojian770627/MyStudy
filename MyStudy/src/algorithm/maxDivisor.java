package algorithm;

public class maxDivisor {

	/**
	 * շת��������������
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
		// n��m
		r = m % n;
		while (r != 0) {
			m = n;
			n = r;
			r = m % n;
		}
		System.out.println("�������:" + n);
	}

}
