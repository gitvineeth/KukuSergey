package com.appsdevelopersblog.app.ws.ui.response;

public enum ErrorMessages {

	
	MISSING_REQUIRED_FIELDS("Missing required fields,please check the documentation"),
	INTERNAL_SERVER_ERROR("Internal Server Error"),
	EMPTY_OBJECT("DB returned empty object");
	
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
