package algorithm.linear;

public class ChildStack {
	String[] context = null;

	int base;
	int top;
	int capacity;
	int limits;

	public ChildStack(String[] parent, int base, int capacity) {
		this.context = parent;
		this.base = base;
		this.capacity = capacity;
		top = base;
		limits = base + capacity;
	}

	void push(String value) throws Exception {
		if ((top + 1) > limits)
			throw new Exception("OverFlow");
		top++;
		context[top] = value;
	}

	String pop() throws Exception {
		if (top == base)
			throw new Exception("UnderFlow");
		return context[top--];
	}

	public String[] getContext() {
		return context;
	}

	public void setContext(String[] context) {
		this.context = context;
	}

	public int getBase() {
		return base;
	}

	public void setBase(int base) {
		this.base = base;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getLimits() {
		return limits;
	}

	public void setLimits(int limits) {
		this.limits = limits;
	}

}
