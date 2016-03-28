package system.security;

import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public class AccessControllerTest implements PrivilegedExceptionAction {
	int action = 1;

	public static void main(String[] args) throws PrivilegedActionException {
		AccessControllerTest test = new AccessControllerTest();
		test.doActon();
	}

	private void doActon() throws PrivilegedActionException {
		AccessController.doPrivileged(this);
	}

	@Override
	public Object run() throws Exception {
		return action;
	}

}
