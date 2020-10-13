package com.movieapp.inventoryservice.dto;

import org.springframework.http.HttpStatus;


public class APISuccessResponseDTO {
	private HttpStatus httpStatus;
	private int statusCode;
	private String message;
	private Object body;
	public APISuccessResponseDTO() {
		super();
	}
	public APISuccessResponseDTO(HttpStatus httpStatus, int statusCode, String message, Object body) {
		super();
		this.httpStatus = httpStatus;
		this.statusCode = statusCode;
		this.message = message;
		this.body = body;
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getBody() {
		return body;
	}
	public void setBody(Object body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "APISuccessResponse [httpStatus=" + httpStatus + ", statusCode=" + statusCode + ", message=" + message
				+ ", body=" + body + "]";
	}
	
	
}
