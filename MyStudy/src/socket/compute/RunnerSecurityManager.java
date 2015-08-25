package socket.compute;

public class RunnerSecurityManager extends SecurityManager {
	private boolean silent = true;
	private boolean checkDelete = true;
	private boolean checkExit = true;

	RunnerSecurityManager() {
		System.out.println("RunnerSecurityManager started");
	}

	@Override
	public void checkExit(int status) {
		if (checkExit)
			throw new SecurityException("Sorry,checkExit " + status);
		else if (!silent)
			System.out.println("RunnerSecurityManager STATUS=" + status
					+ ":checkExit");
	}

	@Override
	public void checkDelete(String file) {
		if (checkDelete)
			throw new SecurityException("Sorry,not allowed to delete" + file);
		else if (!silent)
			System.out.println("RunnerSecurityManager FILE=" + file
					+ ":checkDelete");
	}
}
