package system.jvm.garbage;

/**
 * 演示长期存活的对象将进入老年代
 * 
 * @author zhaojian
 * 
 */
public class TenuringThreshold {
	private static final int _1MB = 1024 * 1024;

	/**
	 * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8
	 * -XX:MaxTenuringThreshold=1
	 */
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3,allocation4;
		allocation1 = new byte[_1MB / 4];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[5 * _1MB];
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		testTenuringThreshold();
	}

}
