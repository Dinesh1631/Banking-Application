package com.AccountService.Services;

import com.AccountService.DTO.customersDTO;

public interface IAccountService {
	public void createUser(customersDTO customer);
	
	public customersDTO fetchCustomer(String mobileNumber);
	
	public boolean updateCustmor(customersDTO customerdto);
	
	public boolean deleteCustomer(String mobileNumber);
}

