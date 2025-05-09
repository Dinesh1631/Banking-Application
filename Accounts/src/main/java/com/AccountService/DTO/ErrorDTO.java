package com.AccountService.DTO;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Error", description = "This will have format of error responce we will send to user")
public class ErrorDTO {
	@Schema(description = "This will have apiPath of our error")
	private String apiPath;
	@Schema(description = "This will have httpStatusCode of our error")
	private HttpStatus httpStatusCode;
	@Schema(description = "This will have errorMessage of our error")
	private String errorMessage;
	@Schema(description = "This will have errorTime of our error")
	private LocalDateTime errorTime;

	public ErrorDTO(String apiPath, HttpStatus badRequest, String errorMessage, LocalDateTime errorTime) {
		this.apiPath = apiPath;
		this.httpStatusCode = badRequest;
		this.errorMessage = errorMessage;
		this.errorTime = errorTime;
	}

	public String getApiPath() {
		return apiPath;
	}

	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}

	public HttpStatus getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(HttpStatus httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public LocalDateTime getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(LocalDateTime errorTime) {
		this.errorTime = errorTime;
	}

}
