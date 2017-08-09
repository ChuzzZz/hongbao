package entity;

import java.sql.Timestamp;

public class ShowInfo {
	int id;
	int s_order;
	String show_name;
	String department;
	Timestamp start_time;
	
	public ShowInfo() {}
	public ShowInfo(int id, int s_order, String show_name, String department, Timestamp start_time) {
		super();
		this.id = id;
		this.s_order = s_order;
		this.show_name = show_name;
		this.department = department;
		this.start_time = start_time;
	}

	@Override
	public String toString() {
		return "ShowInfo [id=" + id + ", s_order=" + s_order + ", show_name=" + show_name + ", department=" + department
				+ ", start_time=" + start_time + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getS_order() {
		return s_order;
	}
	public void setS_order(int s_order) {
		this.s_order = s_order;
	}
	public String getShow_name() {
		return show_name;
	}
	public void setShow_name(String show_name) {
		this.show_name = show_name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Timestamp getStart_time() {
		return start_time;
	}
	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}
	
}
