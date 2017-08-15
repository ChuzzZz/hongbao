package dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Account;
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
	public static boolean addShowInfo(String show_name,String performer, String department, Timestamp time, JdbcTemplate jdbcTemplate) {
		String sql = "insert into showinfo values(0,?,?,?,?)";
		try {
			jdbcTemplate.update(sql, new Object[] { show_name, performer, department, time});
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
		return addShowInfo( s.getShow_name(), s.getPerformer(), s.getDepartment(), s.getStart_time(), jdbcTemplate);
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
		}catch (Exception e) {
			System.out.println("get all showinfo failed!");
			e.printStackTrace();
		}
		return shows;
	}

	public static List<ShowInfo> getAllShowInfoByOrder(JdbcTemplate jdbcTemplate){
		String sql = "select * from showinfo order by start_time;";
		List<ShowInfo> shows = null;
		RowMapper<ShowInfo> show_mapper = new BeanPropertyRowMapper<ShowInfo>(ShowInfo.class);
		try {
			shows = jdbcTemplate.query(sql, show_mapper);
		}catch (Exception e) {
			System.out.println("get all showinfo by order failed!");
			e.printStackTrace();
		}
		return shows;
	}
	
	
	public static ShowInfo getShowInfoByid(int id, JdbcTemplate jdbcTemplate) {
		RowMapper<ShowInfo> ShowInfo_mapper = new BeanPropertyRowMapper<ShowInfo>(ShowInfo.class);
		ShowInfo showinfo = jdbcTemplate.queryForObject("select * from showinfo where id = ?", ShowInfo_mapper, id);
		return showinfo;
	}
	
	public static boolean swapShowTime(int idA, int idB, JdbcTemplate jdbcTemplate) {
		Timestamp timeA = getShowInfoByid(idA,jdbcTemplate).getStart_time();
		Timestamp timeB = getShowInfoByid(idB,jdbcTemplate).getStart_time();
		String sql = "update showinfo set start_time = ? where id = ?";
		try {
			jdbcTemplate.update(sql, new Object[] {timeB, idA});
			jdbcTemplate.update(sql, new Object[] {timeA, idB});
		}catch (Exception e) {
			System.out.println("add showinfo failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static Boolean AllClear(JdbcTemplate jdbcTemplate) {
		String sql = "delete from showinfo";
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			System.out.println("delete from showinfo failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
