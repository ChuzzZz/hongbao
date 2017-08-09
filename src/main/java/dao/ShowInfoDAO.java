package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Department;
import entity.LuckyMoneyTransaction;
import entity.ShowInfo;

public class ShowInfoDAO {
	/**
	 * 添加节目
	 * @param order
	 * @param show_name
	 * @param department
	 * @param jdbcTemplate
	 * @return 成功返回true,失败返回false
	 */
	public static boolean addShowInfo(int order, String show_name, String department, JdbcTemplate jdbcTemplate) {
		String sql = "insert into department values(0,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[] {order, show_name, department});
		}catch (Exception e) {
			System.out.println("add showinfo failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 添加节目
	 * @param s
	 * @param jdbcTemplate
	 * @return 成功返回true,失败返回false
	 */
	public static boolean addShowInfo(ShowInfo s, JdbcTemplate jdbcTemplate) {
		return addShowInfo(s.getS_order(), s.getShow_name(), s.getDepartment(), jdbcTemplate);
	}
	/**
	 * get所有节目信息
	 * @param jdbcTemplate
	 * @return List<ShowInfo>
	 */
	public static List<ShowInfo> getAllShowInfo(JdbcTemplate jdbcTemplate){
		String sql = "select * from showinfo;";
		List<ShowInfo> shows = null;
		RowMapper<ShowInfo> show_mapper = new BeanPropertyRowMapper<ShowInfo>(ShowInfo.class);
		try {
			shows = jdbcTemplate.query(sql, show_mapper);
			System.out.println(shows);
		}catch (Exception e) {
			System.out.println("get all showinfo failed!");
			e.printStackTrace();
		}
		return shows;
	}
	
}
