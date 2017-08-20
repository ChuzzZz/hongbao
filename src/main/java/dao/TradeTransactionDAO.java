package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.LuckyMoneyTransaction;
import entity.TradeTransaction;
import entity.User;

public class TradeTransactionDAO {
	/**
	 * 添加充值记录
	 * 
	 * @param account_id
	 * @param amount
	 * @param ts
	 * @param jdbcTemplate
	 * @return 添加充值记录成功返回true,失败返回false
	 */
	public static boolean addTopupTransaction(int account_id, long amount, Timestamp ts, JdbcTemplate jdbcTemplate) {
		String sql = "insert into trade_transaction values(null, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] { account_id, amount, ts });
		} catch (Exception e) {
			System.out.println("add topup transaction failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 添加提现记录
	 * 
	 * @param account_id
	 * @param amount
	 * @param ts
	 * @param jdbcTemplate
	 * @return 添加提现记录成功返回true,失败返回false
	 */
	public static boolean addWithdrawTransaction(int account_id, long amount, Timestamp ts, JdbcTemplate jdbcTemplate) {
		amount = -amount;
		String sql = "insert into trade_transaction values(null, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, new Object[] { account_id, amount, ts });
		} catch (Exception e) {
			System.out.println("add withdraw transaction failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取所有交易记录
	 * 
	 * @param jdbcTemplate
	 * @return List<TradeTransaction>
	 */
	public static List<TradeTransaction> getAllTradeTransactions(JdbcTemplate jdbcTemplate) {
		String sql = "select * from trade_transaction;";
		List<TradeTransaction> transactions = null;
		RowMapper<TradeTransaction> Transaction_mapper = new BeanPropertyRowMapper<TradeTransaction>(
				TradeTransaction.class);
		try {
			transactions = jdbcTemplate.query(sql, Transaction_mapper);
		} catch (Exception e) {
			System.out.println("get all trade transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}

	/**
	 * 获取所有充值记录
	 * @author Chuz
	 * @param jdbcTemplate
	 * @return List<TradeTransaction>
	 */
	public static List<TradeTransaction> getAllTopupTransactions(JdbcTemplate jdbcTemplate) {
		String sql = "select * from trade_transaction where amount >= 0;";
		List<TradeTransaction> transactions = null;
		RowMapper<TradeTransaction> Transaction_mapper = new BeanPropertyRowMapper<TradeTransaction>(
				TradeTransaction.class);
		try {
			transactions = jdbcTemplate.query(sql, Transaction_mapper);
		} catch (Exception e) {
			System.out.println("get all topup transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}

	/**
	 * 获取所有提现记录
	 * 
	 * @param jdbcTemplate
	 * @return List<TradeTransaction>
	 */
	public static List<TradeTransaction> getAllWithdrawTransactions(JdbcTemplate jdbcTemplate) {
		String sql = "select * from trade_transaction where amount < 0;";
		List<TradeTransaction> transactions = null;
		RowMapper<TradeTransaction> Transaction_mapper = new BeanPropertyRowMapper<TradeTransaction>(
				TradeTransaction.class);
		try {
			transactions = jdbcTemplate.query(sql, Transaction_mapper);
		} catch (Exception e) {
			System.out.println("get all withdraw transactions failed!");
			e.printStackTrace();
		}
		return transactions;
	}

	/**
	 * 获取对应account_id的所有交易记录
	 * 
	 * @param account_id
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TradeTransaction> getAllTransactionsById(int account_id, JdbcTemplate jdbcTemplate) {
		String sql = "select * from trade_transaction where account_id = ?";
		List<TradeTransaction> transactions = null;
		RowMapper<TradeTransaction> Transaction_mapper = new BeanPropertyRowMapper<TradeTransaction>(
				TradeTransaction.class);
		try {
			transactions = jdbcTemplate.query(sql, Transaction_mapper);
		} catch (Exception e) {
			System.out.println("get all transactions by id failed!");
			e.printStackTrace();
		}
		return transactions;
	}

	/**
	 * 获取对应itcode交易记录
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TradeTransaction> getAllTransactionsByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		String sql = "select * from account natural join trade_transaction where itcode = ?";
		List<TradeTransaction> transactions = null;
		RowMapper<TradeTransaction> Transaction_mapper = new BeanPropertyRowMapper<TradeTransaction>(
				TradeTransaction.class);
		try {
			transactions = jdbcTemplate.query(sql, Transaction_mapper);
		} catch (Exception e) {
			System.out.println("get all transactions by itcode failed!");
			e.printStackTrace();
		}
		return transactions;
	}

	/**
	 * 获取对应用户的交易记录
	 * 
	 * @param user
	 * @param jdbcTemplate
	 * @return List<Transaction>
	 */
	public static List<TradeTransaction> getAllTransactionsByUser(User user, JdbcTemplate jdbcTemplate) {
		List<TradeTransaction> transactions = getAllTransactionsByItcode(user.getItcode(), jdbcTemplate);
		return transactions;
	}

}
