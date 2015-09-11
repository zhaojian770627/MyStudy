package ticket.testbean;

import java.beans.PropertyEditorManager;
import java.beans.PropertyVetoException;

import ticket.framework.interface21.beans.BeanWrapper;
import ticket.framework.interface21.beans.BeanWrapperImpl;
import ticket.framework.interface21.beans.BeansException;

public class TestBeanEditorMain {

	public static void main(String[] args) throws BeansException, PropertyVetoException {
		PropertyEditorManager.registerEditor(ITestBean.class, TestBeanEditor.class);
		TestBean rod = new TestBean();
		BeanWrapper bw = new BeanWrapperImpl(rod);
		bw.setPropertyValue("spouse", "Kerry_34");
	}

}
