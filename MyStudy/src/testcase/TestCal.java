package testcase;

import junit.framework.TestCase;

public class TestCal extends TestCase {
	public void testAdd() {
		Calcuator calcuator = new Calcuator();
		double result = calcuator.add(1, 2);
		assertEquals(3, result, 0);
	}

	public void testXml() throws Exception
	{
		String ss = new SSCTaskPushMQWfGadgetHelper().generateXml("aaaa", "bbbb", "ccc", "billid");
		
		new SSCTaskPushMQWfGadgetHelper().executeOperate(ss);;
		int i=0;
		i++;
	}
}
