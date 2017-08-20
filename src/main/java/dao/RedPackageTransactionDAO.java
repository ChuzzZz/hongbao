package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.RedPackageTransaction;


public class RedPackageTransactionDAO {
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
		String sql = "insert into redpackage_transaction values(0,?,?,?,now());";
		try {
			jdbcTemplate.update(sql, new Object[] {account_id, amount, round});
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
	public static boolean addTransaction(RedPackageTransaction t, JdbcTemplate jdbcTemplate) {
		String sql = "insert into redpackage_transaction values(0,?,?,?,?);";
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
	public static List<RedPackageTransaction> getAllTransactions(JdbcTemplate jdbcTemplate){
		String sql = "select * from redpackage_transaction;";
		List<RedPackageTransaction> transactions = null;
		RowMapper<RedPackageTransaction> RedPackageTransaction_mapper = new BeanPropertyRowMapper<RedPackageTransaction>(RedPackageTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, RedPackageTransaction_mapper);
			System.out.println(transactions);
		}catch (Exception e) {
			System.out.println("get all red package transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	
	public static boolean CheckAccount(int id,int round, JdbcTemplate jdbcTemplate) {
		String sql = "select * from redpackage_transaction where account_id = " +id+ " and round = "+"round";
		List<RedPackageTransaction> transactions = null;
		RowMapper<RedPackageTransaction> RedPackageTransaction_mapper = new BeanPropertyRowMapper<RedPackageTransaction>(RedPackageTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, RedPackageTransaction_mapper);
			System.out.println(transactions);
		}catch (Exception e) {
			System.out.println("get all red package transactions failed!");
			e.printStackTrace();
		}
		if (transactions.size()==0) return true;
		else return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
