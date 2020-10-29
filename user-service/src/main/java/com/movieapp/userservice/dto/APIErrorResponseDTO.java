package com.movieapp.userservice.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;


public class APIErrorResponseDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;
	private int statusCode;
	private String message;
	private Throwable cause;
	public APIErrorResponseDTO() {
		super();
	}
	
	public APIErrorResponseDTO(HttpStatus httpStatus, int statusCode, String message) {
		super();
		this.httpStatus = httpStatus;
		this.statusCode = statusCode;
		this.message = message;
	}

	public APIErrorResponseDTO(HttpStatus httpStatus, int statusCode, String message, Throwable cause) {
		super();
		this.httpStatus = httpStatus;
		this.statusCode = statusCode;
		this.message = message;
		this.cause = cause;
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

	public Throwable getCause() {
		return cause;
	}

	public void setCause(Throwable cause) {
		this.cause = cause;
	}

	
	
}
