package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.TopupTransaction;
import entity.User;

public class TopupTransactionDAO {
	
	private static boolean preTransaction() {return false;} 
	/**
	 * ���ӽ��׼�¼
	 * @param account_id
	 * @param amount
	 * @param ts
	 * @param jdbcTemplate
	 * @return ���ӽ��׼�¼�ɹ�����true,ʧ�ܷ���false
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
	 * get��Ӧaccount_id�Ľ��׼�¼
	 * @param account_id
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TopupTransaction> getTransactionsById(int account_id, JdbcTemplate jdbcTemplate){
//		RowMapper<Transaction> transaction_mapper = new BeanPropertyRowMapper<Transaction>(Transaction.class);
		List<TopupTransaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList("select * from transaction where account_id = ?", TopupTransaction.class, account_id);
		}catch (Exception e) {
			System.out.println("getTransactionsById failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get��Ӧitcode���׼�¼
	 * @param itcode
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TopupTransaction> getTransactionsByItcode(int itcode, JdbcTemplate jdbcTemplate){
//		RowMapper<Transaction> transaction_mapper = new BeanPropertyRowMapper<Transaction>(Transaction.class);
		List<TopupTransaction> transactions = null;
		try {
			transactions = jdbcTemplate.queryForList("select * from account natural join transaction where itcode = ?", TopupTransaction.class, itcode);
		}catch (Exception e) {
			System.out.println("getTransactionsByItcode failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get��Ӧ�û��Ľ��׼�¼
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