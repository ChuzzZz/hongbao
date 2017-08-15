package entity;

import java.sql.Timestamp;

public class AccountTransaction {
	int id;
	int amount;
	Timestamp time;
	String type;
	public AccountTransaction(){}
	public AccountTransaction(int id,int amount,Timestamp time,String type){
		this.id=id;
		this.amount=amount;
		this.time=time;
		this.type=type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	@Override
	public String toString() {
		return "AccountTransaction [id=" + id + ", amount=" + amount + ", time=" + time + ", type=" + type + "]";
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
