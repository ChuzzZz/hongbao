package dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.User;

public class UserDAO {
	/**
	 * get user by itcode
	 * @param itcode
	 * @param jdbcTemplate
	 * @return User
	 */
	public static User getUserByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user = null;
		try {
			user = jdbcTemplate.queryForObject("select * from user where itcode = ?", user_mapper, itcode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	/**
	 * check if user exists
	 * @param itcode
	 * @param name
	 * @param jdbcTemplate
	 * @return true if user exists,false if user doesn't exist
	 */
	public static boolean userExists(int itcode, String name, JdbcTemplate jdbcTemplate) {
		RowMapper<User> user_mapper = new BeanPropertyRowMapper<User>(User.class);
		User user = null;
		try {
			user = jdbcTemplate.queryForObject("select * from user where itcode = ? and name = ?", user_mapper,
					new Object[] { itcode, name });
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			return false;
		} else {
			return true;
		}
	}
	/**
	 * check if user exists
	 * @param user
	 * @param jdbcTemplate
	 * @return true if user exists,false if user doesn't exist
	 */
	public static boolean userExists(User user, JdbcTemplate jdbcTemplate) {
		if (userExists(user.getItcode(), user.getName(), jdbcTemplate)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * create user
	 * @param itcode
	 * @param name
	 * @param jdbcTemplate
	 * @return true if create user,false if failed
	 * 
	 */
	public static boolean createUser(int itcode, String name, JdbcTemplate jdbcTemplate) {
		String sql = "insert into user values(0,?,?);";
		try {
			int i = jdbcTemplate.update(sql,new Object[] {itcode,name});
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean isLock(int itcode, JdbcTemplate jdbcTemplate) {
		return false;
	}

	public static boolean lockUserByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		return false;
	}

	public static boolean unlockUserByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
