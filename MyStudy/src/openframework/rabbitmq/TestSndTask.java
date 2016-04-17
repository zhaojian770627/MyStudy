package openframework.rabbitmq;

import org.junit.Test;

import junit.framework.TestCase;

public class TestSndTask extends TestCase {

	@Test
	public void testAAA() throws Exception {
		for (int i = 0; i < 20; i++) {
			TaskMQUtil.getInstance().sendMQMsg("M" + i);
		}
	}

}
