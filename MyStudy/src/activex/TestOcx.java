package activex;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TestOcx {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(600, 400);
		shell.setLayout(new FillLayout());

		OleControlSite oleControlSite;

		OleFrame oleFrame = new OleFrame(shell, SWT.NONE);
		oleControlSite = new OleControlSite(oleFrame, SWT.NONE, "JavaControl.ZJJavaControl");
		oleControlSite.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
		shell.open();

		final OleAutomation javacontrol = new OleAutomation(oleControlSite);

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		javacontrol.dispose();
		display.dispose();
	}
}
