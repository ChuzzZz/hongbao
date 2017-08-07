package form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountForm {
	@NotNull
	@Size(min=2,max=16)
	String name;
	@NotNull
	@Size(min=2,max=16)
	int itcode;
	@NotNull
	@Min(100)
	@Max(100000)
	int amount;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getItcode() {
		return itcode;
	}

	public void setItcode(int itcode) {
		this.itcode = itcode;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
