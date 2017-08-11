package entity;

import java.sql.Timestamp;

public class ShowInfo {
	int id;
	int s_order;
	String show_name;
	String performer;
	String department;
	Timestamp time;
	
	public ShowInfo() {}
	public ShowInfo(int id, int s_order, String show_name, String performer, String department, Timestamp time) {
		super();
		this.id = id;
		this.s_order = s_order;
		this.show_name = show_name;
		this.performer = performer;
		this.department = department;
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "ShowInfo [id=" + id + ", s_order=" + s_order + ", show_name=" + show_name + ", performer=" + performer
				+ ", department=" + department + ", start_time=" + time + "]";
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
	public String getPerformer() {
		return performer;
	}
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Timestamp gettime() {
		return time;
	}
	public void settime(Timestamp start_time) {
		this.time = start_time;
	}
	
}
