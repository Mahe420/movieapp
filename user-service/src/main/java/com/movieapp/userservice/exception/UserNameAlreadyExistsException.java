package com.movieapp.userservice.exception;

public class UserNameAlreadyExistsException extends ServiceException{

	public UserNameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNameAlreadyExistsException(String message) {
		super(message);
	
	}
	

}
