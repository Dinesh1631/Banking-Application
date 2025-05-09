package com.AccountService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AccountService.DTO.ErrorDTO;
import com.AccountService.DTO.ResponceDTO;
import com.AccountService.DTO.contactInfoDTO;
import com.AccountService.DTO.customersDTO;
import com.AccountService.Services.IAccountService;
import com.AccountService.commonConstants.AccountConstants;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping("/api")
@EnableConfigurationProperties(value = { contactInfoDTO.class })
@Validated
@Tag(name = "Accounts", description = "This controller has CRUD functionality of Account Microservices", externalDocs = @ExternalDocumentation(url = "chat.com"))
public class AccountControllers {
	IAccountService accountService;

	public AccountControllers(IAccountService accountService) {
		this.accountService = accountService;
	}

	@Value("${build.version}")
	private String buildVersion;
	@Autowired
	private Environment env;
	@Autowired
	private contactInfoDTO contactinfodto;

	@PostMapping("/create")
	@Operation(summary = "create account method", description = "This end point is used to create accout in DineshBank")
	@ApiResponse(description = "Http Status created", responseCode = "201")
	public ResponseEntity<ResponceDTO> createCustomer(@Valid @RequestBody customersDTO customerdto) {
		accountService.createUser(customerdto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponceDTO("OK", "user is created along with account"));
	}

	@GetMapping("/fetch")
	@Operation(summary = "fetch account method", description = "This end point is used to fetch accout in DineshBank")
	@ApiResponse(description = "Http Status Found", responseCode = "302")
	public ResponseEntity<customersDTO> fetchCustomer(
			@Pattern(regexp = "^($|[0-9]{10})", message = "Please enter a valid mobileNumber") @RequestParam String mobileNumber) {
		customersDTO customersdto = accountService.fetchCustomer(mobileNumber);
		return ResponseEntity.status(HttpStatus.FOUND).body(customersdto);
	}

	@PutMapping("/update")
	@Operation(summary = "update account method", description = "This end point is used to Update accout in DineshBank")
	@ApiResponses({ @ApiResponse(description = "Http Status Update successfully", responseCode = "200"),
			@ApiResponse(description = "Http Status Internal Server error", responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
			@ApiResponse(description = "Http Status Update Failed", responseCode = "417") })
	public ResponseEntity<ResponceDTO> fetchCustomer(@Valid @RequestBody customersDTO customerdto) {
		boolean isUpdated = accountService.updateCustmor(customerdto);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponceDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponceDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
		}
	}

	@DeleteMapping("/delete")
	@Operation(summary = "delete account method", description = "This end point is used to Delete accout in DineshBank")
	@ApiResponses({ @ApiResponse(description = "Http Status Deleted successfully", responseCode = "200"),
			@ApiResponse(description = "Http Status Internal Server error", responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
			@ApiResponse(description = "Http Status Deleted Failed", responseCode = "417") })

	public ResponseEntity<ResponceDTO> DeleteCustomer(
			@Pattern(regexp = "^($|[0-9]{10})", message = "Please enter a valid mobileNumber") @RequestParam String mobileNumber) {
		boolean isUpdated = accountService.deleteCustomer(mobileNumber);
		if (isUpdated) {
			return ResponseEntity.status(HttpStatus.ACCEPTED)
					.body(new ResponceDTO(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
		} else {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body(new ResponceDTO(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_UPDATE));
		}
	}

	@GetMapping("/build-info")
	@Operation(summary = "fetch buid information", description = "This end point is used to fetch buid information in DineshBank")
	@ApiResponse(description = "Http Status Found", responseCode = "302")
	@Retry(name="BuildInfo",fallbackMethod="BuildInfoFallBack")
	public ResponseEntity<String> getBuildInfo() {
		
		 throw new RuntimeException();
		//return ResponseEntity.status(HttpStatus.FOUND).body(buildVersion);
	}

	public ResponseEntity<String> BuildInfoFallBack(Throwable throwable){
		return ResponseEntity.status(HttpStatus.OK).body("0.9");
	}
	@RateLimiter(name= "getJavaVersion", fallbackMethod = "getJavaVersionFallback")
	@GetMapping("/java-version")
	@Operation(summary = "fetch java Version", description = "This end point is used to fetch java Version in DineshBank")
	@ApiResponse(description = "Http Status Found", responseCode = "302")
	public ResponseEntity<String> getJavaInfo() {
		return ResponseEntity.status(HttpStatus.FOUND).body(env.getProperty("JAVA_HOME"));
	}
	
	 public ResponseEntity<String> getJavaVersionFallback(Throwable throwable) {
	        return ResponseEntity
	                .status(HttpStatus.OK)
	                .body("Java 21");
	    }

	@GetMapping("/contact-info")
	@Operation(summary = "fetch contact-info", description = "This end point is used to fetch contact-info in DineshBank")
	@ApiResponse(description = "Http Status Found", responseCode = "302")
	public ResponseEntity<contactInfoDTO> getContactInfo() {
		return ResponseEntity.status(HttpStatus.OK).body(contactinfodto);
	}

}
