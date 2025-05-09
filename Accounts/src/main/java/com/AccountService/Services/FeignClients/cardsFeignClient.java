package com.AccountService.Services.FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.AccountService.DTO.CardsDto;
import com.AccountService.Services.FeignClients.Configuration.FeignClientConfig;
import com.AccountService.Services.FeignClients.FeignCallbacks.cardsCallback;

@FeignClient(name= "CARDS" ,fallback= cardsCallback.class,configuration=FeignClientConfig.class )
public interface cardsFeignClient {
	  @GetMapping(value="api/fetch" ,consumes="application/json")
	    public ResponseEntity<CardsDto> fetchCardDetails(@RequestParam String mobileNumber,@RequestHeader("Dineshbank-correlation-id") String trace_id); 
}
