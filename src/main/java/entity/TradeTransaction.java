package entity;

import java.sql.Timestamp;

public class TradeTransaction {
	int id;
	int account_id;
	long amount;
	Timestamp time;

	public TradeTransaction() {};
	public TradeTransaction(int id, int account_id, long amount, Timestamp time) {
		this.id = id;
		this.account_id = account_id;
		this.amount = amount;
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TopupTransaction [id=" + id + ", account_id=" + account_id + ", amount=" + amount + ", time=" + time
				+ "]";
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
	
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}
	
}
