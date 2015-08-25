package C1;

public class TreeMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tree t = new Tree(null, 50, null);
		Tree nt = t.insert(25, t);
		nt=t.insert(90, nt);
		nt=t.insert(40, nt);
		boolean f=t.search(40, nt);
		System.out.println(f);
		int i=0;
	}

}
