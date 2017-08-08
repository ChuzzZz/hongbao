package myThread;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.AccountDAO;
import entity.Account;

public class LuckyRainThread extends Thread {
	boolean flag = false;
	JdbcTemplate jdbcTemplate;
	int round=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		List<Account> accounts = AccountDAO.getAllAcounts(jdbcTemplate);
		
	}

}
