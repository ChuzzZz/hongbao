package dao;

import java.util.Random;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;


import entity.RedPackage;

public class RedPackageDAO {
	
	
	public static boolean initRedPackage (RedPackage lm, JdbcTemplate jdbcTemplate) {
		String sql = "insert into redpackage values(?,?);";
		try {
			jdbcTemplate.update(sql, new Object[] {lm.getRound(),lm.getTotal()});
		}catch (Exception e) {
			System.out.println("add red package failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static long getTotalByRound(int round, JdbcTemplate jdbcTemplate) {
		String sql = "select total from redpackage where round = ?;";
		int total = -1;
		try {
			total = jdbcTemplate.queryForInt(sql, round);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public static int getActiveRound(JdbcTemplate jdbcTemplate) {
		String sql = "select round from redpackage where is_on = 1;";
		int round = -1;
		try {
			round = jdbcTemplate.queryForInt(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return round;
	}
	
	public static String subTotal(int round, long amount, JdbcTemplate jdbcTemplate) {
		String sql = "update redpackage set total = total - " + amount + " where round = " + round + " ;";
		return sql;
	}
	
	
	public static boolean ActicateRound(int round, JdbcTemplate jdbcTemplate) {
		String sql1 = "update redpackage set is_on = 0";
		String sql = "update redpackage set is_on = 1 where round = ?;";
		try {
			jdbcTemplate.update(sql1);
			jdbcTemplate.update(sql, new Object[] {round});
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
	public static synchronized int GiveMoney(int id, JdbcTemplate jdbcTemplate) 
	{ 
		int account_id = AccountDAO.getAccountByItcode(id, jdbcTemplate).getId();
		System.out.println("现在开始分发红包");
		int round = getActiveRound(jdbcTemplate);
		System.out.println("获得的轮次为："+round);
		if (!RedPackageTransactionDAO.CheckAccount(account_id, round, jdbcTemplate)) {System.out.println("领过了红包，退出");return -1;}
		long money=getTotalByRound(round,jdbcTemplate);
		System.out.println("红包剩余金额："+money);
		Random r = new Random();
		if (getTotalByRound(round, jdbcTemplate)<=money/(long)(AccountDAO.getAllAcounts(jdbcTemplate).size()*0.6)*2){
			money=getTotalByRound(round, jdbcTemplate);
			System.out.println("因为剩余金额较少，全部分配出去，得到："+money);
		}
		else {
			money=money/(long)(AccountDAO.getAllAcounts(jdbcTemplate).size()*0.6)*r.nextInt(100)/50;
			System.out.println("抢到金额："+money);
		}
		String[] sql = new String [3];
		sql[0] = RedPackageDAO.subTotal(round, money, jdbcTemplate);
		sql[1] = RedPackageTransactionDAO.addTransaction(account_id, money, round, jdbcTemplate);
		sql[2] = AccountDAO.addbalance(account_id, money, jdbcTemplate);
		jdbcTemplate.batchUpdate(sql);
		return (int) money;
	}

	public static Boolean StopRound(int round, JdbcTemplate jdbcTemplate) {
		// TODO Auto-generated method stub
		String sql = "update redpackage set is_on = 0 where round = ?;";
		try {
			jdbcTemplate.update(sql, new Object[] {round});
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	
	public static int CheckStatus(int round,JdbcTemplate jdbcTemplate) {
		String sql = "select is_on from redpackage where round = "+round;
		int is_on = -1;
		try {
			is_on = jdbcTemplate.queryForInt(sql);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return is_on;
	}
	
	
}
