package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Transaction;
import entity.User;

public class TransactionDAO {
	
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
		String sql = "insert into transaction values(null, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] {account_id, amount, ts});
		}catch (Exception e) {
			System.out.println("addTransaction failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * get对应account_id的交易记录
	 * @param account_id
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<Transaction> getTransactionsById(int account_id, JdbcTemplate jdbcTemplate){
//		RowMapper<Transaction> transaction_mapper = new BeanPropertyRowMapper<Transaction>(Transaction.class);
		List<Transaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList("select * from transaction where account_id = ?", Transaction.class, account_id);
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
	public static List<Transaction> getTransactionsByItcode(int itcode, JdbcTemplate jdbcTemplate){
//		RowMapper<Transaction> transaction_mapper = new BeanPropertyRowMapper<Transaction>(Transaction.class);
		List<Transaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList("select * from account natural join transaction where itcode = ?", Transaction.class, itcode);
		}catch (Exception e) {
			System.out.println("getTransactionsByItcode failed!");
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
	public static List<Transaction> getTransactionsByUser(User user, JdbcTemplate jdbcTemplate){
		List<Transaction> transactions = getTransactionsById(user.getId(), jdbcTemplate);
		return transactions;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
