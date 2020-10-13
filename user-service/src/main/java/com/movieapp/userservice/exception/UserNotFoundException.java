package com.movieapp.userservice.exception;


public class UserNotFoundException extends ServiceException{

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public UserNotFoundException(String message) {
		super(message);
	
	}
	

}
