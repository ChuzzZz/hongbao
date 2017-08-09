package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import entity.LuckyMoneyTransaction;
import entity.TopupTransaction;

public class LuckyMoneyTransactionDAO {
	public static boolean addTransaction(int account_id, long amount, int round, Timestamp ts, JdbcTemplate jdbcTemplate) {
		String sql = "insert into luckymoney_transaction values(0,?,?,?,?);";
		try {
			jdbcTemplate.update(sql,new Object[] {account_id, amount, round, ts});
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("add Transaction failed!");
			return false;
		}
		return true;
	}
	/**
	 * ��Ӻ����¼
	 * @param t
	 * @param jdbcTeamplate
	 * @return true,��ӳɹ�;false,���ʧ��
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
	 * �õ����к����¼
	 * @param jdbcTeamplate
	 * @return List<LuckMoneyTransaction>
	 */
	public static List<LuckyMoneyTransaction> getAllTransactions(JdbcTemplate jdbcTemplate){
		String sql = "select * from luckymoney_transaction;";
		List<LuckyMoneyTransaction> transactions = null;
		try {
			jdbcTemplate.queryForList(sql, LuckyMoneyTransaction.class);
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
