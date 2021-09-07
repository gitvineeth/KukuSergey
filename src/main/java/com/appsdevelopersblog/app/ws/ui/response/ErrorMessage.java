package com.appsdevelopersblog.app.ws.ui.response;

import java.util.Date;

public class ErrorMessage {

	private Date timestamp;
	private String errorMessage;
	public ErrorMessage(Date timestamp, String errorMessage) {
		super();
		this.timestamp = timestamp;
		this.errorMessage = errorMessage;
	}
	public ErrorMessage() {
		super();
	}
	
	
	
	
	
}
