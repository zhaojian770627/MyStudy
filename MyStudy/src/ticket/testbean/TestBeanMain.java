package ticket.testbean;

import java.beans.PropertyVetoException;

import ticket.interface21.beans.BeanWrapper;
import ticket.interface21.beans.BeanWrapperImpl;
import ticket.interface21.beans.BeansException;

public class TestBeanMain {

	public static void main(String[] args) throws BeansException, PropertyVetoException {
		TestBean rod = new TestBean();
		BeanWrapper bw = new BeanWrapperImpl(rod);
		bw.setPropertyValue("age", new Integer(32));
		bw.setPropertyValue("name", "rod");
		bw.setPropertyValue("spouse", new TestBean());
		bw.setPropertyValue("spouse.name", "kerry");
		bw.setPropertyValue("spouse.spouse", rod);

		Integer age = (Integer) bw.getPropertyValue("age");
		String name = (String) bw.getPropertyValue("name");
		System.out.println(age);
		System.out.println(name);
	}

}
