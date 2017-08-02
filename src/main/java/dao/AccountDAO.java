package dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Account;

public class AccountDAO {
	public static int topUp(String itcode, String username, String amount, Locale locale, JdbcTemplate jdbcTemplate) {
		try {
			
			RowMapper<Account> account_mapper = new BeanPropertyRowMapper<Account>(Account.class);
			Account account = jdbcTemplate.queryForObject("select * from account where itcode = ?", account_mapper, itcode);
			
			Date date = new Date();
			DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
			String formattedDate = dateFormat.format(date);
			
			int i = jdbcTemplate.update("insert into transaction values(null, ?, ?, ?)", new Object[] {account.getId(), amount, formattedDate});
			if(i > 0) {
				int j = jdbcTemplate.update("update account set balance = balance + ? where id = ?", new Object[] {amount, account.getId()});
				if(j > 0) {
					return 1;
				}
			}
		}catch(Exception e) {
			return -1;
		}
		
		return 0;
		
	}
}
