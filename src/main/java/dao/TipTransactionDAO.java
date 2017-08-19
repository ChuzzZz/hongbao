package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.TipTransaction;

public class TipTransactionDAO {
	/**
	 * ��Ӵ��ͼ�¼
	 * @param account_id
	 * @param show_id
	 * @param amount
	 * @param time
	 * @param jdbcTemplate
	 * @return �ɹ�����true,ʧ�ܷ���false
	 */
	public static boolean addTipTransaction(int account_id, int show_id, long amount, Timestamp ts, JdbcTemplate jdbcTemplate) {
		amount *= 100;
		String sql = "insert into tip_transaction values(null, ?, ?, ?, ?);";
		try {
			jdbcTemplate.update(sql, new Object[] {account_id, show_id, amount, ts});
		}catch (Exception e) {
			System.out.println("add tip transaction failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * ��Ӵ��ͼ�¼
	 * @param t
	 * @param jdbcTemplate
	 * @return �ɹ�����true,ʧ�ܷ���false
	 */
	public static boolean addTipTransaction(TipTransaction t, JdbcTemplate jdbcTemplate) {
		return addTipTransaction(t.getAccount_id(), t.getShow_id(), t.getAmount(), t.getTime(), jdbcTemplate);
	}
	/**
	 * ���д��ͼ�¼
	 * @param jdbcTemplate
	 * @return List<TipTransaction>
	 */
	public static List<TipTransaction> getAllTipTransactions(JdbcTemplate jdbcTemplate){
		String sql = "select * from tip_transaction;";
		List<TipTransaction> transactions = null;
		RowMapper<TipTransaction> TipTransaction_mapper = new BeanPropertyRowMapper<TipTransaction>(TipTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, TipTransaction_mapper);
		}catch (Exception e) {
			System.out.println("get all tip transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * ��ȡ��ӦǮ���˻��Ĵ��ͼ�¼
	 * @param account_id
	 * @param jdbcTemplate
	 * @return List<TipTransaction>
	 */
	public static List<TipTransaction> getTipTransactionsByAccountId(int account_id, JdbcTemplate jdbcTemplate){
		String sql = "select * from tip_transaction where account_id = ?;";
		List<TipTransaction> transactions = null;
		RowMapper<TipTransaction> TipTransaction_mapper = new BeanPropertyRowMapper<TipTransaction>(TipTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, TipTransaction_mapper, account_id);
		}catch (Exception e) {
			System.out.println("get account tip transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
	/**
	 * get��Ӧ��Ŀ�Ĵ��ͼ�¼
	 * @param show_id
	 * @param jdbcTemplate
	 * @return List<TipTransaction>
	 */
	public static List<TipTransaction> getTipTransactionsByShowtId(int show_id, JdbcTemplate jdbcTemplate){
		String sql = "select * from tip_transaction where show_id = ?;";
		List<TipTransaction> transactions = null;
		RowMapper<TipTransaction> TipTransaction_mapper = new BeanPropertyRowMapper<TipTransaction>(TipTransaction.class);
		try {
			transactions=jdbcTemplate.query(sql, TipTransaction_mapper, show_id);
		}catch (Exception e) {
			System.out.println("get account tip transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}
}
