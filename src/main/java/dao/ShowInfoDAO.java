package dao;

import org.springframework.jdbc.core.JdbcTemplate;

import entity.Department;

public class ShowInfoDAO {
	
	public static boolean addShow(Department d, JdbcTemplate jdbcTemplate) {
		String sql = "insert into department values(0,?)";
		try {
			jdbcTemplate.update(sql, d.getDept_name());
		}catch (Exception e) {
			System.out.println("add department failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
