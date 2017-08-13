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
	 * ���û��Ƿ���Ǯ���˻�
	 * @param itcode
	 * @param jdbcTemplate
	 * @return �з���true,û�з���false
	 */
	public static boolean hasAccount(int itcode, JdbcTemplate jdbcTemplate) {
		String sql = "select count(*) from account where itcode = ?";
		int i = -1;
		try {
			i = jdbcTemplate.queryForInt(sql, itcode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(i == 1) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * ע��Ǯ���˻�
	 * @param itcode
	 * @param jdbcTemplate
	 * @return true,init succeed;false,init failed
	 */
	public static boolean initAccount(int itcode, String password, String paycode, JdbcTemplate jdbcTemplate) {
		String sql = "insert into account values(0, ?, ?, ?, 0)";
		try{
			jdbcTemplate.update(sql, new Object[] {itcode, password, paycode});
		}catch (Exception e) {
			System.out.println("account already exists!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * get����Ǯ���˻�
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
	 * ��ȡ��Ӧitcode��Ǯ���˻�id
	 * @param itcode
	 * @param jdbcTemplate
	 * @return �ɹ�����account_id,ʧ�ܷ���-1
	 */
	public static int getAccountIdByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		Account account = getAccountByItcode(itcode, jdbcTemplate);
		if (account != null) {
			return account.getId();
		}else {
			return -1;
		}
	}
	public static Account getAccountById(int id, JdbcTemplate jdbcTemplate) {
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = null;
		try{
			account = jdbcTemplate.queryForObject("select * from account where id = ?", account_mapper, id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	/**
	 * get��Ӧitcode��Ǯ���˻�
	 * @param itcode
	 * @param jdbcTemplate
	 * @return Account
	 */
	public static Account getAccountByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = null;
		try{
			account = jdbcTemplate.queryForObject("select * from account where itcode = ?", account_mapper, itcode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	/**
	 * ��Ӧaccount_id��Ǯ���˻��������amount
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return true,�ɹ�;false,ʧ��
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
	 * ��֤֧������
	 * @param account_id
	 * @param paycode
	 * @param jdbcTemplate
	 * @return ��ȷ����true,���󷵻�false
	 */
	public static boolean verifyPaycode(int account_id, String paycode, JdbcTemplate jdbcTemplate) {
		Account account = getAccountById(account_id, jdbcTemplate);
		if(account.getPaycode().equals(paycode)) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * ��Ǯ���˻���ֵ
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return ��ֵ�ɹ�true,ʧ��false
	 */
	public static boolean topUp(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			TopupTransactionDAO.addTransaction(account_id, amount, ts, jdbcTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
