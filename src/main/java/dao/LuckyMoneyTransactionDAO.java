package dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import entity.LuckyMoneyTransaction;
import entity.TopupTransaction;

public class LuckyMoneyTransactionDAO {
	/**
	 * 添加红包记录
	 * @param t
	 * @param jdbcTeamplate
	 * @return true,添加成功;false,添加失败
	 */
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
	/**
	 * 得到所有红包记录
	 * @param jdbcTeamplate
	 * @return List<LuckMoneyTransaction>
	 */
	public static List<LuckyMoneyTransaction> getAllTransactions(JdbcTemplate jdbcTeamplate){
		String sql = "select * from luckymoney_transaction;";
		List<LuckyMoneyTransaction> transactions = null;
		try {
			jdbcTeamplate.queryForList(sql, LuckyMoneyTransaction.class);
		}catch (Exception e) {
			System.out.println("get all lucky money transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
