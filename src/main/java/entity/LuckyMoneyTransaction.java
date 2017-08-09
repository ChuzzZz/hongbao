package entity;

import java.sql.Timestamp;

public class LuckyMoneyTransaction {
	int id;
	int account_id;
	long amount;
	int round;
	Timestamp time;
	
	public LuckyMoneyTransaction() {}
	public LuckyMoneyTransaction(int id, int account_id, long amount, int round, Timestamp time) {
		super();
		this.id = id;
		this.account_id = account_id;
		this.amount = amount;
		this.round = round;
		this.time = time;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LuckyMoneyTransaction [id=" + id + ", account_id=" + account_id + ", amount=" + amount + ", round="
				+ round + ", time=" + time + "]";
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

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
