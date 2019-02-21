package com.shop_now.authenticationservice.exception;

import java.util.Date;

public class ExceptionResponse {
	private Date timeStamp;
	private String message;
	private String details;
	
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getdetails() {
		return details;
	}
	public void setdetails(String details) {
		this.details = details;
	}

}
