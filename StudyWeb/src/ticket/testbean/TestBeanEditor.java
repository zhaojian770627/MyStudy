package ticket.testbean;

import java.beans.PropertyEditorSupport;
import java.util.StringTokenizer;

public class TestBeanEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TestBean tb = new TestBean();
		StringTokenizer st = new StringTokenizer(text, "_");
		tb.setName(st.nextToken());
		tb.setAge(Integer.parseInt(st.nextToken()));
		setValue(tb);
	}

}
