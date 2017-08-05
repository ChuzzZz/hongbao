package entity;

public class User {
	int id;
	int itcode;
	String name;
	
	public User() {};
	
	public User(int id, int itcode, String name) {
		super();
		this.id = id;
		this.itcode = itcode;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getItcode() {
		return itcode;
	}

	public void setItcode(int itcode) {
		this.itcode = itcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ("ID:"+ id +"  ITcode:" + itcode + "  name:" + name );
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
