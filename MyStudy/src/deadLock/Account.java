package deadLock;

public class Account {

	private int balance=1000;
	public Integer getBalance() {
		return balance;
	}

	public void debit(Integer amount) {
		balance-=amount;
	}

	public void credit(Integer amount) {		
		balance+=amount;
	}

}
