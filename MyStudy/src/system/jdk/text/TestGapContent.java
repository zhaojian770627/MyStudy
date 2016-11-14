package system.jdk.text;

import javax.swing.text.BadLocationException;
import javax.swing.text.GapContent;

public class TestGapContent {

	public static void main(String[] args) throws BadLocationException {
		GapContent gapContent=new GapContent(100);
		
		gapContent.insertString(0, "1234567890");
		gapContent.insertString(10, "ABCDEFG");
		gapContent.remove(3, 2);
		int i=0;
	}

}
