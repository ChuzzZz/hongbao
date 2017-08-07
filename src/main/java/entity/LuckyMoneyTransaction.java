package entity;

import java.sql.Timestamp;

public class LuckyMoneyTransaction {
	int id;
	int account_id;
	long amount;
	int round;
	Timestamp time;
	
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

	@Override
	public String toString() {
		String str;
		str = "½»Ò×¼ÇÂ¼:" + id +"  "+ round +"  "+ time +"  "+ account_id +"  "+ amount ;
		return str;
	}

}
