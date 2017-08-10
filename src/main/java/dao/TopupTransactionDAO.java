package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.LuckyMoneyTransaction;
import entity.TopupTransaction;
import entity.User;

public class TopupTransactionDAO {
	
	private static boolean preTransaction() {return false;} 
	/**
	 * 添加交易记录
	 * @param account_id
	 * @param amount
	 * @param ts
	 * @param jdbcTemplate
	 * @return 添加交易记录成功返回true,失败返回false
	 */
	public static boolean addTransaction(int account_id, long amount, Timestamp ts, JdbcTemplate jdbcTemplate) {
		String sql = "insert into topup_transaction values(null, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] {account_id, amount, ts});
		}catch (Exception e) {
			System.out.println("add topup transaction failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static List<TopupTransaction> getAllTransactions(JdbcTemplate jdbcTemplate){
		String sql = "select * from topup_transaction;";
		List<TopupTransaction> transactions = null;
		RowMapper<TopupTransaction> TopupTransaction_mapper = new BeanPropertyRowMapper<TopupTransaction>(TopupTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, TopupTransaction_mapper);
		}catch (Exception e) {
			System.out.println("get all topup transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get对应account_id的交易记录
	 * @param account_id
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TopupTransaction> getTransactionsById(int account_id, JdbcTemplate jdbcTemplate){
		String sql = "select * from topup_transaction where account_id = ?";
		List<TopupTransaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList(sql, TopupTransaction.class, account_id);
		}catch (Exception e) {
			System.out.println("getTransactionsById failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get对应itcode交易记录
	 * @param itcode
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TopupTransaction> getTransactionsByItcode(int itcode, JdbcTemplate jdbcTemplate){
//		RowMapper<Transaction> transaction_mapper = new BeanPropertyRowMapper<Transaction>(Transaction.class);
		String sql = "select * from account natural join topup_transaction where itcode = ?";
		List<TopupTransaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList(sql, TopupTransaction.class, itcode);
		}catch (Exception e) {
			System.out.println("get topupTransactionsByItcode failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get对应用户的交易记录
	 * @param user
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TopupTransaction> getTransactionsByUser(User user, JdbcTemplate jdbcTemplate){
		List<TopupTransaction> transactions = getTransactionsById(user.getId(), jdbcTemplate);
		return transactions;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
