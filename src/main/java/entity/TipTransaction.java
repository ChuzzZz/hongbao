package entity;

import java.sql.Timestamp;

public class TipTransaction {
	int id;
	int account_id;
	int show_id;
	long amount;
	Timestamp time;
	
	public TipTransaction() {}
	public TipTransaction(int id, int account_id, int show_id, long amount, Timestamp time) {
		super();
		this.id = id;
		this.account_id = account_id;
		this.show_id = show_id;
		this.amount = amount;
		this.time = time;
	}

	@Override
	public String toString() {
		return "TipTransaction [id=" + id + ", account_id=" + account_id + ", show_id=" + show_id + ", amount=" + amount
				+ ", time=" + time + "]";
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
	public int getShow_id() {
		return show_id;
	}
	public void setShow_id(int show_id) {
		this.show_id = show_id;
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
