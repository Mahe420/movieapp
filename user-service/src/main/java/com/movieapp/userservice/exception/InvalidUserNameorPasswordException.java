package com.movieapp.userservice.exception;

public class InvalidUserNameorPasswordException extends ServiceException{

	public InvalidUserNameorPasswordException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public InvalidUserNameorPasswordException(String message) {
		super(message);
		
	}

}
