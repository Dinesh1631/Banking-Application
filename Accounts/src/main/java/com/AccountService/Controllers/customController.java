package com.AccountService.Controllers;

import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AccountService.DTO.contactInfoDTO;
import com.AccountService.DTO.customerDetailsDto;
import com.AccountService.Services.IcustomService;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api")
@EnableConfigurationProperties(value = { contactInfoDTO.class })
@Validated
@Tag(name = "Accounts", description = "This controller has details of custom controllers", externalDocs = @ExternalDocumentation(url = "chat.com"))
public class customController {
	
	IcustomService customservice;
	
	private static final org.slf4j.Logger logger =  LoggerFactory.getLogger(customController.class);

	public customController(IcustomService customservice) {
		this.customservice = customservice;
	}
	
	@GetMapping("/fetchCustomerDetails")
	@Operation(summary = "fetch account method", description = "This end point is used to fetch accout in DineshBank")
	@ApiResponse(description = "Http Status Found", responseCode = "302")
	public ResponseEntity<customerDetailsDto> fetchCustomer(
			@RequestHeader("Dineshbank-correlation-id") String trace_id,
			@Pattern(regexp = "^($|[0-9]{10})", message = "Please enter a valid mobileNumber") @RequestParam String mobileNumber
			) {
		logger.debug("AccountController trace_id founnd : {} " , trace_id);
		customerDetailsDto customerdetails = customservice.fetchAllDetailsOfCustomers(mobileNumber,trace_id);
		return ResponseEntity.status(HttpStatus.FOUND).body(customerdetails);
	}

}
