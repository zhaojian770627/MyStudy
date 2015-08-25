package algorithm.sort.base;

public interface IWaitNext {
	void waitNext() throws InterruptedException;

	void continueNext();

	void notify(int i, int j);
}
