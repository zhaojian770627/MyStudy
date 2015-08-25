package FinalObject;

public class FinalMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreeStooges ts=new ThreeStooges();
		System.out.println(ts.isStooge("abc"));
		ts.addName("abc");
		System.out.println(ts.isStooge("abc"));
		ts.changeFinal();
	}

}
