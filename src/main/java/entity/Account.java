package entity;

public class Account {
	int id;
	int itcode;
	int balance;
	
	public Account() {};
	
	public Account(int id, int itcode, int balance) {
		this.id = id;
		this.itcode = itcode;
		this.balance = balance;
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


	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
