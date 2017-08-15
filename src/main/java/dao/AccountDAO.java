package dao;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.experimental.theories.Theories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Account;
import entity.AccountTransaction;

public class AccountDAO {

	/**
	 * 提现前判断余额是否足够提现
	 * 
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return 能提现返回true,不能返回false
	 */
	private static boolean preTransaction(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		String sql = "select balance from account where id = ?;";
		long balance = 0;
		try {
			balance = jdbcTemplate.queryForLong(sql, account_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (balance >= amount) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 该用户是否有钱包账户
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return 有返回true,没有返回false
	 */
	public static boolean hasAccount(int itcode, JdbcTemplate jdbcTemplate) {
		String sql = "select count(*) from account where itcode = ?";
		int i = -1;
		try {
			i = jdbcTemplate.queryForInt(sql, itcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (i == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 注册钱包账户
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return true,init succeed;false,init failed
	 */
	public static boolean initAccount(int itcode, String paycode, JdbcTemplate jdbcTemplate) {
		String sql = "insert into account values(null, ?, ?, 0)";
		try {
			jdbcTemplate.update(sql, new Object[] { itcode, paycode });
		} catch (Exception e) {
			System.out.println("account already exists!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 获取所有钱包账户
	 * 
	 * @param jdbcTemplate
	 * @return List<Account>
	 */
	public static List<Account> getAllAcounts(JdbcTemplate jdbcTemplate) {
		String sql = "select * from account;";
		List<Account> accounts = null;
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		try {
			accounts = jdbcTemplate.query(sql, account_mapper);
		} catch (Exception e) {
			System.out.println("getAllAcounts failed");
			e.printStackTrace();
		}
		return accounts;

	}

	/**
	 * 获取对应itcode的钱包账户id
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return 成功返回account_id,失败返回-1
	 */
	public static int getAccountIdByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		Account account = getAccountByItcode(itcode, jdbcTemplate);
		if (account != null) {
			return account.getId();
		} else {
			return -1;
		}
	}

	/**
	 * 获取对应account_id的钱包账户
	 * 
	 * @param id
	 * @param jdbcTemplate
	 * @return
	 */
	public static Account getAccountById(int id, JdbcTemplate jdbcTemplate) {
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = null;
		try {
			account = jdbcTemplate.queryForObject("select * from account where id = ?", account_mapper, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	/**
	 * 获取对应itcode的钱包账户
	 * 
	 * @param itcode
	 * @param jdbcTemplate
	 * @return Account
	 */
	public static Account getAccountByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
		Account account = null;
		try {
			account = jdbcTemplate.queryForObject("select * from account where itcode = ?", account_mapper, itcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}

	/**
	 * 对应account_id的钱包账户余额增加amount
	 * 
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return true,成功;false,失败
	 */
	public static boolean addAccountBalance(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		String sql = "update account set balance = balance + ? where id = ?";
		amount *= 100;
		try {
			jdbcTemplate.update(sql, new Object[] { amount, account_id });
		} catch (Exception e) {
			System.out.println("addAccountBalance failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 验证支付密码
	 * 
	 * @param account_id
	 * @param paycode
	 * @param jdbcTemplate
	 * @return 正确返回true,错误返回false
	 */
	public static boolean verifyPaycode(int account_id, String paycode, JdbcTemplate jdbcTemplate) {
		Account account = getAccountById(account_id, jdbcTemplate);
		if (account.getPaycode().equals(paycode)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 钱包账户充值
	 * 
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return 充值成功true,失败false
	 */
	public static boolean topUp(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			amount *= 100;
			TradeTransactionDAO.addTopupTransaction(account_id, amount, ts, jdbcTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 钱包账户提现
	 * 
	 * @param account_id
	 * @param amount
	 * @param jdbcTemplate
	 * @return 提现成功true,失败false
	 */
	public static boolean withdraw(int account_id, long amount, JdbcTemplate jdbcTemplate) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			amount *= 100;
			if (preTransaction(account_id, amount, jdbcTemplate)) {
				TradeTransactionDAO.addWithdrawTransaction(account_id, amount, ts, jdbcTemplate);
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean tip(int account_id, int show_id, long amount, JdbcTemplate jdbcTemplate) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			amount *= 100;
			if (preTransaction(account_id, amount, jdbcTemplate)) {
				TipTransactionDAO.addTipTransaction(account_id, show_id, amount, ts, jdbcTemplate);
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static List<AccountTransaction> GetAccountTransactions(String od,int account_id, JdbcTemplate jdbcTemplate,HttpServletResponse response,HttpServletRequest request) {
			String order = "";
			Cookie[] cookies = request.getCookies();
			if (cookies == null) {
				order="";
			}
			for (Cookie c : cookies) {
				if (c.getName().equals("order")) {
					if (c.getValue().equals("desc")){order="";}
					else {order="desc";}
					break;
				}
			}
			
		Cookie cookie = new Cookie("order", order);
//		cookie.setMaxAge(30 * 60);
//		cookie.setPath("/");
        response.addCookie(cookie);
       
        
		String sql = "select * from ";
		sql+="((SELECT id, amount, time, '节目打赏支出' AS type ";
		sql+="FROM tip_transaction AS t) ";
		sql+="UNION ALL ";
		sql+="(SELECT id, amount, time, '账户充值' AS type ";
		sql+="FROM topup_transaction AS a) ";
		sql+="UNION ALL ";
		sql+="(SELECT id, amount, time, '获得红包' AS type " ;
		sql+="FROM luckymoney_transaction AS a)) AS m ";
		sql+="ORDER BY "+od+" "+order;
		List<AccountTransaction> t = null;
		RowMapper<AccountTransaction> AccountTransaction_mapper = new BeanPropertyRowMapper<AccountTransaction>(AccountTransaction.class);
		try {
			t = jdbcTemplate.query(sql, AccountTransaction_mapper);
		} catch (Exception e) {
			System.out.println("getAccountTransaction failed");
			e.printStackTrace();
		}
		return t;
	}
	
}
