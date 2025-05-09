package com.AccountService.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="Responce",description="This will have format of responce we will send to user")
public class ResponceDTO {
	@Schema(description="Status code of our responce" ,example = "200")
	private String statusCode;
	@Schema(description="Status Message of our responce" ,example = "Internal Error")
	private String statusMessage;

	public ResponceDTO(String statusCode, String statusMessage) {
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}

	public ResponceDTO() {}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	

}
