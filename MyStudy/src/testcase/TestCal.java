package testcase;

import junit.framework.TestCase;

public class TestCal extends TestCase {
	public void testAdd(){ 
        Calcuator calcuator=new Calcuator(); 
        double result=calcuator.add(1,2); 
        assertEquals(3,result,0); 
    } 
}
