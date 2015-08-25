package socket.compute;

import javax.swing.JOptionPane;

public class TestCompute implements ICompute {

	@Override
	public void run() {
		JOptionPane.showMessageDialog(null, "abcd");
	}

}
