package entity;

public class Transaction {
	int id;
	int account_id;
	int amount;
	String time;

	public Transaction() {};
	
	public Transaction(int id, int account_id, int amount, String time) {
		this.id = id;
		this.account_id = account_id;
		this.amount = amount;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccount_id() {
		return account_id;
	}
	
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
