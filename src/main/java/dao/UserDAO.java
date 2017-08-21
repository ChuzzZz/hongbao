package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Account;
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
	 * 根据Itcode获取用户姓名
	 * @param itcode
	 * @param jdbcTemplate
	 * @return User.name
	 */
	public static String getUserNameByItcode(int itcode, JdbcTemplate jdbcTemplate) {
		User u = getUserByItcode(itcode, jdbcTemplate);
		return u.getName();
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
	public static boolean createUser(int itcode, String name, int onsite, JdbcTemplate jdbcTemplate) {
		User user = new User(0, itcode, name, onsite);
		return createUser(user, jdbcTemplate);
	}
	
	public static boolean createUser(User user, JdbcTemplate jdbcTemplate) {
		String sql = "insert into user values(0,?,?,?);";
		try {
			jdbcTemplate.update(sql,new Object[] {user.getItcode(),user.getName(),user.getOnsite()});
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

	public static List<User> getAllUsers(JdbcTemplate jdbcTemplate) {
		String sql = "select * from user;";
		List<User> user = null;
		RowMapper<User> User_mapper = new BeanPropertyRowMapper<User>(User.class);
		try {
			user = jdbcTemplate.query(sql, User_mapper);
		} catch (Exception e) {
			System.out.println("getAllAcounts failed");
			e.printStackTrace();
		}
		return user;

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static boolean DeleteAllUsers(JdbcTemplate jdbcTemplate) {
		String sql = "delete from user;";
		try {
			jdbcTemplate.update(sql);
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
