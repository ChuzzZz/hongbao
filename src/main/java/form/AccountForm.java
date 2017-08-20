package form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AccountForm {
	@NotNull
	int itcode;
	@NotNull
	@Size(min=6,max=16)
	String paycode;
	
//	@NotNull
//	@Min(100)
//	@Max(100000)
//	long amount;
	
	public int getItcode() {
		return itcode;
	}
	public void setItcode(int itcode) {
		this.itcode = itcode;
	}
	public String getPaycode() {
		return paycode;
	}
	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}
	
}
