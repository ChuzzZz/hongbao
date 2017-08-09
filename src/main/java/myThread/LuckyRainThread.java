package myThread;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;

import dao.AccountDAO;
import dao.LuckyMoneyDAO;
import dao.LuckyMoneyTransactionDAO;
import entity.Account;
import entity.LuckyMoney;

public class LuckyRainThread extends Thread {
	boolean flag;
	JdbcTemplate jdbcTemplate;
	int round;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	@Override
	public void run() {
		List<Account> accounts = AccountDAO.getAllAcounts(jdbcTemplate);
		for(Account a : accounts) {
			if(flag) {
				long total = LuckyMoneyDAO.getTotalByRound(round, jdbcTemplate);
				Random r = new Random();
				long amount;
				if(total > 0) {
					if(total > 5000) {
						amount = r.nextInt(5000);
					}else {
						amount = total;
					}
				}else {
					break;
				}
				
				try {
					if(LuckyMoneyDAO.subTotal(round, amount, jdbcTemplate)) {
						Timestamp ts = new Timestamp(System.currentTimeMillis());
						LuckyMoneyTransactionDAO.addTransaction(a.getId(), amount, round, ts, jdbcTemplate);
						AccountDAO.addAccountBalance(a.getId(), amount, jdbcTemplate);
						Thread.sleep(1000);
					}
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
