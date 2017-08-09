package dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.experimental.theories.Theories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Account;

public class AccountDAO {
	/**
	 * 注册钱包账户
	 * @param itcode
	 * @param jdbcTemplate
	 * @return true,init succeed;false,init failed
	 */
	public static boolean initAccount(int itcode, JdbcTemplate jdbcTemplate) {
		String sql = "insert into account values(0, ?, 0)";
		try{
			jdbcTemplate.update(sql, itcode);
		}catch (Exception e) {
			System.out.println("account already exists!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * get所有钱包账户
	 * @param jdbcTemplate
	 * @return List<Account>
	 */
	public static List<Account> getAllAcounts(JdbcTemplate jdbcTemplate){
		String sql = "select * from account;";
		List<Account> accounts = null;
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		try{
			accounts = jdbcTemplate.query(sql,account_mapper);
		}catch (Exception e) {
			System.out.println("getAllAcounts failed");
			e.printStackTrace();
		}
		return accounts;

	}
	/**
	 * get对应itcode的钱包账户
	 * @param itcode
	 * @param jdbcTemplate
	 * @return Account
	 */
	public static Account getAccountByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = jdbcTemplate.queryForObject("select * from account where itcode = ?", account_mapper, itcode);
		return account;
	}
	/**
	 * 对应account_id的钱包账户余额增加amount
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return true,成功;false,失败
	 */
	public static boolean addAccountBalance(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		String sql = "update account set balance = balance + ? where id = ?";
		try{
			jdbcTemplate.update(sql, new Object[] {amount,account_id});
		}catch (Exception e) {
			System.out.println("addAccountBalance failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 充值
	 * @param itcode
	 * @param username
	 * @param amount
	 * @param locale
	 * @param jdbcTemplate
	 * @return true,topup success;false,topup failed
	 */
	public static boolean topUp(int itcode, String username, long amount, Locale locale, JdbcTemplate jdbcTemplate) {
		try {
			Account account = AccountDAO.getAccountByItcode(itcode, jdbcTemplate);
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			
			TopupTransactionDAO.addTransaction(account.getId(), amount, ts, jdbcTemplate);
//			addAccountBalance(account.getId(), amount, jdbcTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// Timestamp ts = new Timestamp(System.currentTimeMillis());
		// System.out.println(ts);
	}
}
