package entity;

public class Account {
	int id;
	int itcode;
	String paycode;
	long balance;
	
	public Account() {};

	public Account(int id, int itcode, String paycode, long balance) {
		super();
		this.id = id;
		this.itcode = itcode;
		this.paycode = paycode;
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + ", itcode=" + itcode + ", paycode=" + paycode
				+ ", balance=" + balance + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getItcode() {
		return itcode;
	}

	public void setItcode(int itcode) {
		this.itcode = itcode;
	}

	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

}
