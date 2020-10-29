package com.movieapp.moviesearch.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;


public class APISuccessResponseDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((httpStatus == null) ? 0 : httpStatus.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + statusCode;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		APISuccessResponseDTO other = (APISuccessResponseDTO) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (httpStatus != other.httpStatus)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusCode != other.statusCode)
			return false;
		return true;
	}
	
	
}
