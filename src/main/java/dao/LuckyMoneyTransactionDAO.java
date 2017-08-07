package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import entity.LuckyMoneyTransaction;

public class LuckyMoneyTransactionDAO {

	public static boolean addTransaction(LuckyMoneyTransaction t, JdbcTemplate jdbcTeamplate) {
		String sql = "insert into luckymoney_transaction values(0,?,?,?,?);";
		try {
			jdbcTeamplate.update(sql,new Object[] {t.getAccount_id(),t.getAmount(),t.getRound(),t.getTime()});
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("add Transaction failed!");
			return false;
		}
		return true;
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
