package jvm.garbage;

public class MinorGC {
	private static final int _1MB=1024*1024;
	
	/**
	 * VM����:-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRation=8
	 * -XX:+UseParallelGC
	 */
	public static void testAllocation()
	{
		byte[] allocation1,allocation2,allocation3,allocation4;
		allocation1=new byte[2*_1MB];
		allocation2=new byte[2*_1MB];
		allocation3=new byte[2*_1MB];
		allocation4=new byte[4*_1MB];
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testAllocation();
	}

}
