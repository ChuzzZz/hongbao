package entity;

public class ShowTips {
	String show_name;
	String department;
	long total_tip;
	
	public ShowTips() {}
	public ShowTips(String show_name, String department, long total_tip) {
		super();
		this.show_name = show_name;
		this.department = department;
		this.total_tip = total_tip;
	}
	@Override
	public String toString() {
		return "ShowTips [show_name=" + show_name + ", department=" + department + ", total_tip=" + total_tip + "]";
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
	public long getTotal_tip() {
		return total_tip;
	}
	public void setTotal_tip(long total_tip) {
		this.total_tip = total_tip;
	}
	
}
