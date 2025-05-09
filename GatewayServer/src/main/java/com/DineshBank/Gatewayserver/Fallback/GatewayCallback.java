package com.DineshBank.Gatewayserver.Fallback;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class GatewayCallback {
	@GetMapping("/supportInfo")
	public Mono<String> contactSupport(){
		return Mono.just("There is unexpected issue ,please contact customer support");	
	}

}
