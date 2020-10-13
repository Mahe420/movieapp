package com.movieapp.inventoryservice.exception;

public class TheatreNotFoundException extends ServiceException{



	public TheatreNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public TheatreNotFoundException(String message) {
		super(message);
		
	}


	

}
