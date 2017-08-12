package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.ShowTips;

public class ShowTipsDAO {
	/**
	 * 获取所有节目的总打赏收入
	 * @param jdbcTemplate
	 * @return List<ShowTips>
	 */
	public static List<ShowTips> getAllShowTips(JdbcTemplate jdbcTemplate){
		String sql = "select * from show_totaltip;";
		List<ShowTips> s = null;
		RowMapper<ShowTips> show_mapper = new BeanPropertyRowMapper<ShowTips>(ShowTips.class);
		try {
			s = jdbcTemplate.query(sql, show_mapper);
		}catch (Exception e) {
			System.out.println("get all show tips failed!");
			e.printStackTrace();
		}
		return s;
	}
}
