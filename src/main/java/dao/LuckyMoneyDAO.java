package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import entity.LuckyMoney;

public class LuckyMoneyDAO {
	
	public static boolean initLuckyMoney(LuckyMoney lm, JdbcTemplate jdbcTemplate) {
		String sql = "insert into luckymoney values(?,?);";
		try {
			jdbcTemplate.update(sql, new Object[] {lm.getRound(),lm.getTotal()});
		}catch (Exception e) {
			System.out.println("add lucky money failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static long getTotalByRound(int round, JdbcTemplate jdbcTemplate) {
		String sql = "select total from luckymoney where round = ?;";
		int total = -1;
		try {
			total = jdbcTemplate.queryForInt(sql, round);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public static boolean subTotal(int round, long amount, JdbcTemplate jdbcTemplate) {
		String sql = "update luckymoney set total = total-? where round = ?;";
		try {
			jdbcTemplate.update(sql, new Object[] {amount, round});
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	

}
