package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.LuckyMoneyTransaction;

public class LuckyMoneyTransactionDAO {
	/**
	 * 添加红包记录
	 * @param account_id
	 * @param amount
	 * @param round
	 * @param ts
	 * @param jdbcTemplate
	 * @return 成功返回true,失败返回false
	 */
	public static boolean addTransaction(int account_id, long amount, int round, JdbcTemplate jdbcTemplate) {
		amount *= 100;
		String sql = "insert into luckymoney_transaction values(0,?,?,?,now());";
		try {
			jdbcTemplate.update(sql,new Object[] {account_id, amount, round});
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("add Transaction failed!");
			return false;
		}
		return true;
	}
	/**
	 * 添加红包记录
	 * @param t
	 * @param jdbcTeamplate
	 * @return true,添加成功;false,添加失败
	 */
	public static boolean addTransaction(LuckyMoneyTransaction t, JdbcTemplate jdbcTemplate) {
		String sql = "insert into luckymoney_transaction values(0,?,?,?,?);";
		try {
			jdbcTemplate.update(sql,new Object[] {t.getAccount_id(),t.getAmount(),t.getRound(),t.getTime()});
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
	public static List<LuckyMoneyTransaction> getAllTransactions(JdbcTemplate jdbcTemplate){
		String sql = "select * from luckymoney_transaction;";
		List<LuckyMoneyTransaction> transactions = null;
		RowMapper<LuckyMoneyTransaction> LuckyMoneyTransaction_mapper = new BeanPropertyRowMapper<LuckyMoneyTransaction>(LuckyMoneyTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, LuckyMoneyTransaction_mapper);
			System.out.println(transactions);
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
