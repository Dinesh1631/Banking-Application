package com.AccountService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(name="customerDetails",description="This will hold the details of customer, account,cards,loans")
public class customerDetailsDto {
	  @NotNull(message = "Name filed should not be empty")
	    @Size(min=5 ,max =30 ,message="Name should of length between 5 and 30 characters")
	    @Schema(description="This will hold the Name of customer" ,example="Dinesh")
		private String name;
	    
	    @NotNull(message = "Email filed should not be empty")
	    @Email(message="This is not a valid email format")
	    @Schema(description="This will hold the Email of customer ",example="Dinesh@gmail.com")
		private String email;
	    
	    @NotNull(message = "mobileNumber filed should not be empty")
	    @Pattern(regexp = "^($|[0-9]{10})",message="Please enter a valid mobileNumber")
	    @Schema(description="This will hold the MobileNumber of customer ",example="1234567890")
		private String mobileNumber;
	    
	    @Schema(description="This will hold the details of account")
		private accountsDTO accountsdto;
	    
	    @Schema(description="This will hold the details of loans")
		private LoansDto loansdto;
	    
	    @Schema(description="This will hold the details of cards")
		private CardsDto cardsdto;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public accountsDTO getAccountsdto() {
			return accountsdto;
		}

		public void setAccountsdto(accountsDTO accountsdto) {
			this.accountsdto = accountsdto;
		}

		public LoansDto getLoansdto() {
			return loansdto;
		}

		public void setLoansdto(LoansDto loansdto) {
			this.loansdto = loansdto;
		}

		public CardsDto getCardsdto() {
			return cardsdto;
		}

		public void setCardsdto(CardsDto cardsdto) {
			this.cardsdto = cardsdto;
		}
}
