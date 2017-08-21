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
		long lmoney = money;
		List<Account> accounts = AccountDAO.getAllAcounts(jdbcTemplate);
		System.out.println("剩余金额为："+money+"account.size:"+accounts.size());
		for (int i=0;i<accounts.size();i++){
			long nowmoney = 0;
			if (i==accounts.size()-1){nowmoney=lmoney;}
			else if (lmoney==0) break;
			else if (lmoney<money/accounts.size()*2) {nowmoney=lmoney;}
			else {
				Random r = new Random();
				nowmoney=money/accounts.size()*r.nextInt(100)/50;
			}
			lmoney-=nowmoney;
			System.out.println("发出了"+nowmoney+"元");
			
			String sql[] = new String[3];
			sql[0] = LuckyMoneyDAO.subTotal(round, nowmoney, jdbcTemplate);
			sql[1] = LuckyMoneyTransactionDAO.addTransaction(accounts.get(i).getId(), nowmoney, round, jdbcTemplate);
			sql[2] = AccountDAO.addbalance(accounts.get(i).getId(), nowmoney, jdbcTemplate);
			jdbcTemplate.batchUpdate(sql);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}
