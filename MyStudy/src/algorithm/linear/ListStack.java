package algorithm.linear;

public class ListStack {
	String[] context = {};
	int n = 10;
	int top;

	public ListStack() {
		context = new String[n + 1];
		top = 0;
	}

	void push(String value) throws Exception {
		if ((top + 1) > n)
			throw new Exception("OverFlow");
		top++;
		context[top] = value;
	}

	String pop() throws Exception {
		if (top == 0)
			throw new Exception("UnderFlow");
		return context[top--];
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		ListStack stack = new ListStack();
		for (int i = 0; i < 10; i++) {
			stack.push(String.valueOf(i));
		}

		for (int i = 0; i < 11; i++) {
			System.out.println(stack.pop());
		}
	}

}
