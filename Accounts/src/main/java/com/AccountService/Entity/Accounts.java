package com.AccountService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity{
	@Id
	private Long accountNumber;
	
	@Column(name="customer_id")
	private Long customerid;
	
	@Column(name="account_type")
	private String account_type;
	
	@Column(name="branch_address")
	private String branch_address;

	public Long getAccount_number() {
		return accountNumber;
	}

	public void setAccount_number(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Long getCustomer_id() {
		return customerid;
	}

	public void setCustomer_id(Long customerid) {
		this.customerid = customerid;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getBranch_address() {
		return branch_address;
	}

	public void setBranch_address(String branch_address) {
		this.branch_address = branch_address;
	}
	
}
