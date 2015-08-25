package deadLock;

import java.util.Random;

import javax.naming.InsufficientResourcesException;

/**
 * 开始一个循环，它在典型条件下制定死锁
 * 
 * @author zhaojianc
 * 
 */
public class DemonstrateDeadlock {
	private static final int NUM_THREADS = 20;
	private static final int NUM_ACCOUNTS = 5;
	private static final int NUM_ITERATIONS = 1000000;
	private static final Object tieLock = new Object();

	public static void main(String[] args) {
		final Random rnd = new Random();
		final Account[] accounts = new Account[NUM_ACCOUNTS];

		for (int i = 0; i < accounts.length; i++)
			accounts[i] = new Account();

		class TransferThread extends Thread {
			@Override
			public void run() {
				for (int i = 0; i < NUM_ITERATIONS; i++) {
					int fromAcct = rnd.nextInt(NUM_ACCOUNTS);
					int toAct = rnd.nextInt(NUM_ACCOUNTS);

					Integer amount = rnd.nextInt(1000);
					try {
						transferMoney(accounts[fromAcct], accounts[toAct],
								amount);
					} catch (InsufficientResourcesException e) {
						e.printStackTrace();
					}
				}
			}
		}

		for (int i = 0; i < NUM_THREADS; i++)
			new TransferThread().start();
	}

	/*
	 * // 动态加锁顺序产生的死锁 public static void transferMoney(Account fromAccount,
	 * Account toAccount, Integer amount) throws InsufficientResourcesException
	 * { synchronized (fromAccount) { synchronized (toAccount) { if
	 * (fromAccount.getBalance().compareTo(amount) < 0) throw new
	 * InsufficientResourcesException(); else { fromAccount.debit(amount);
	 * toAccount.credit(amount); } } } }
	 */

	/*
	 * 制定锁的顺序来避免死锁
	 */
	public static void transferMoney(final Account fromAccount,
			final Account toAccount, final Integer amount)
			throws InsufficientResourcesException {
		class Helper {
			public void transfer() throws InsufficientResourcesException {
				if (fromAccount.getBalance().compareTo(amount) < 0)
					throw new InsufficientResourcesException();
				else {
					fromAccount.debit(amount);
					toAccount.credit(amount);
				}
			}
		}

		int fromHash = System.identityHashCode(fromAccount);
		int toHash = System.identityHashCode(toAccount);

		if (fromHash < toHash) {
			synchronized (fromAccount) {
				synchronized (toAccount) {
					new Helper().transfer();
				}
			}
		}else if(fromHash>toHash){
			synchronized (toAccount) {
				synchronized (fromAccount) {
					new Helper().transfer();
				}
			}
		}else{
			synchronized (tieLock) {
				synchronized (fromAccount) {
					synchronized (toAccount) {
						new Helper().transfer();
					}
				}
			}
		}
	}
}
