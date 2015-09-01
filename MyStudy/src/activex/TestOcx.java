package activex;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.ole.win32.OLE;
import org.eclipse.swt.ole.win32.OleAutomation;
import org.eclipse.swt.ole.win32.OleControlSite;
import org.eclipse.swt.ole.win32.OleEvent;
import org.eclipse.swt.ole.win32.OleFrame;
import org.eclipse.swt.ole.win32.OleListener;
import org.eclipse.swt.ole.win32.Variant;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class TestOcx {

	public static void main(String[] args) {
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(600, 400);
		shell.setLayout(new FillLayout());

		OleControlSite oleControlSite;

		OleFrame oleFrame = new OleFrame(shell, SWT.NONE);

		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		MenuItem fileItem1 = new MenuItem(bar, SWT.CASCADE);
		fileItem1.setText(" Ù–‘");

		MenuItem fileItem2 = new MenuItem(bar, SWT.CASCADE);
		fileItem2.setText("∑Ω∑®");

		oleControlSite = new OleControlSite(oleFrame, SWT.NONE, "JavaControl.ZJJavaControl");

		oleControlSite.addEventListener(0x00000001, new OleListener() {

			@Override
			public void handleEvent(OleEvent oe) {
				JOptionPane.showMessageDialog(null, oe.type);
			}
		});

		oleControlSite.doVerb(OLE.OLEIVERB_INPLACEACTIVATE);
		final OleAutomation javacontrol = new OleAutomation(oleControlSite);

		fileItem1.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent se) {

				Variant v = javacontrol.getProperty(0x68030001);
				JOptionPane.showMessageDialog(null, v.getString());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent se) {

			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		javacontrol.dispose();
		oleControlSite.dispose();
		display.dispose();
	}
}
