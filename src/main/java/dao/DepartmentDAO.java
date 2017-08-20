package dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import entity.Department;

public class DepartmentDAO {
	
	/**
	 * ��Ӳ���
	 * @param d
	 * @param jdbcTemplate
	 * @return �ɹ�����true,ʧ�ܷ���false
	 */
	public static boolean addDepartment(String dept_name, JdbcTemplate jdbcTemplate) {
		String sql = "insert into department values(?);";
		try {
			jdbcTemplate.update(sql, dept_name);
		}catch (Exception e) {
			System.out.println("add department failed!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * ��ȡ���в���
	 * @param jdbcTemplate
	 * @return List<Department>
	 */
	public static List<Department> getAllDepartment(JdbcTemplate jdbcTemplate){
		String sql = "select * from department;";
		List<Department> departments = null;
		RowMapper<Department> department_mapper = new BeanPropertyRowMapper<Department>(Department.class);
		try{
			departments = jdbcTemplate.query(sql,department_mapper);
		}catch (Exception e) {
			System.out.println("get all acounts failed");
			e.printStackTrace();
		}
		return departments;
	}
}
