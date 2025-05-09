package com.AccountService.Services.FeignClients.FeignCallbacks;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.AccountService.DTO.CardsDto;
import com.AccountService.Services.FeignClients.cardsFeignClient;
@Component
public class cardsCallback implements cardsFeignClient {

	@Override
	public ResponseEntity<CardsDto> fetchCardDetails(String mobileNumber,
			String trace_id) {
		return null;
	}

}
