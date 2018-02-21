package com.kafeneio.DTO;

import com.kafeneio.enums.AppConstant;

public class MessageDTO {
	private String message;
	private AppConstant messageType;
	private int statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public AppConstant getMessageType() {
		return messageType;
	}
	public void setMessageType(AppConstant messageType) {
		this.messageType = messageType;
	}
	
	
}
