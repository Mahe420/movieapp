package com.movieapp.inventoryservice.exception;

public class MovieNotFoundException extends ServiceException {


	public MovieNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public MovieNotFoundException(String message) {
		super(message);
		
	}


	

}
