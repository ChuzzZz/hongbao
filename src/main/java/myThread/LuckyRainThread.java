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
		long money=LuckyMoneyDAO.getTotalByRound(round, jdbcTemplate);
		List<Account> accounts = AccountDAO.getAllAcounts(jdbcTemplate);
		for (int i=0;i<accounts.size();i++){
			long lmoney;
			if (i==accounts.size()-1){lmoney=money;}
			else {
				Random r = new Random();
				lmoney=money/accounts.size()*r.nextInt(100)/50;
			}
			money-=lmoney;
			LuckyMoneyTransactionDAO.addTransaction(accounts.get(i).getId(), lmoney, round, jdbcTemplate);
			AccountDAO.addAccountBalance(accounts.get(i).getId(), lmoney, jdbcTemplate);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
