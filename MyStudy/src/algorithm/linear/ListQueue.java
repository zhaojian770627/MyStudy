package algorithm.linear;

public class ListQueue {
	String[] context = {};
	int m = 20;
	int f, r;

	public ListQueue() {
		context = new String[m + 1];
		f = r = 1;
	}

	public void EnQueue(String value) throws Exception {
		if ((r + 1) % m == f)
			throw new Exception("OverFlow");

		if (r == m)
			r = 1;
		else
			r++;

		context[r] = value;
	}

	String DeQueue() throws Exception {
		if (f == r)
			throw new Exception("UnderFlow");

		if (f == m)
			f = 1;
		else
			f++;

		return context[f];
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ListQueue queue = new ListQueue();
		for (int i = 0; i < 9; i++) {
			queue.EnQueue(String.valueOf(i));
		}
		queue.DeQueue();
		queue.EnQueue("a");

		System.out.println("R:" + queue.r + " F:" + queue.f);

		int j = 0;
		j++;
	}
}
