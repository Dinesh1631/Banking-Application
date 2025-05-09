package com.AccountService.Services;

import com.AccountService.DTO.customerDetailsDto;

public interface IcustomService {
  
	public customerDetailsDto fetchAllDetailsOfCustomers(String mobileNumber, String trace_id);
		
}
