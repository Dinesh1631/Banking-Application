package com.AccountService.Services.FeignClients.FeignCallbacks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.AccountService.DTO.LoansDto;
import com.AccountService.Services.FeignClients.LoansFeignClient;
@Component
public class loansCallback implements LoansFeignClient {

	@Override
	public ResponseEntity<LoansDto> fetchCardDetails(String mobileNumber,
			String trace_id) {
		return null;
	}

}
