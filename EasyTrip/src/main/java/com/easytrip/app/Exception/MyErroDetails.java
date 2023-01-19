package com.easytrip.app.Exception;

import java.time.LocalDateTime;

public class MyErroDetails {

	private LocalDateTime timestamp;
	
	private String message;
	
	private String details;
	
	public MyErroDetails() {
		// TODO Auto-generated constructor stub
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public MyErroDetails(LocalDateTime timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	@Override
	public String toString() {
		return "MyErroDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
	
	
}
