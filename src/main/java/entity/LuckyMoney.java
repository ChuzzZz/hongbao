package entity;

public class LuckyMoney {
	int round;
	long total;
	
	public LuckyMoney() {}
	public LuckyMoney(int round, long total) {
		super();
		this.round = round;
		this.total = total;
	}

	public int getRound() {
		return round;
	}
	
	public void setRound(int round) {
		this.round = round;
	}
	
	public long getTotal() {
		return total;
	}
	
	public void setTotal(long total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return ("µÚ" + round + "ÂÖ" + "  totoal:" + total);
	}
	
}
