package com.AccountService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
@Schema(name="account",description="This will hold the details of account")
public class accountsDTO {
	@NotNull(message = "accountNumber filed should not be empty")
	@Pattern(regexp = "^($|[0-9]{10})", message = "Please enter a valid mobileNumber")
	 @Schema(description="This will hold the accountNumber of customer" ,example="1234567890")
	private long accountNumber;
	
	@NotNull(message = "accountType filed should not be empty")
	 @Schema(description="This will hold the accountType of customer" ,example="Savings")
	private String accountType;
	
	@NotNull(message = "branchAddress filed should not be empty")
	 @Schema(description="This will hold the branchAddress of customer" ,example="NewYork123")
	private String branchAddress;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

}
