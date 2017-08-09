package entity;

public class User {
	int id;
	int itcode;
	String name;
	int onsite;
	
	public User() {};

	public User(int id, int itcode, String name, int onsite) {
		super();
		this.id = id;
		this.itcode = itcode;
		this.name = name;
		this.onsite = onsite;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", itcode=" + itcode + ", name=" + name + ", onsite=" + onsite + "]";
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

	public int getOnsite() {
		return onsite;
	}

	public void setOnsite(int onsite) {
		this.onsite = onsite;
	}

}
