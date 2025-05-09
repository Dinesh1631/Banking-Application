package com.AccountService.mappers;

import org.springframework.stereotype.Component;

import com.AccountService.DTO.accountsDTO;
import com.AccountService.Entity.Accounts;
@Component
public class accountMapper {
	
	public Accounts mapToAccount(Accounts accounts , accountsDTO accountdto) {
		accounts.setAccount_number(accountdto.getAccountNumber());
		accounts.setAccount_type(accountdto.getAccountType());
		accounts.setBranch_address(accountdto.getBranchAddress());
		return accounts;
	}
	
	public accountsDTO mapToAccountDTO(Accounts accounts , accountsDTO accountdto) {
		accountdto.setAccountNumber(accounts.getAccount_number());
		accountdto.setAccountType(accounts.getAccount_type());
		accountdto.setBranchAddress(accounts.getBranch_address());
		return accountdto;
	}

}
