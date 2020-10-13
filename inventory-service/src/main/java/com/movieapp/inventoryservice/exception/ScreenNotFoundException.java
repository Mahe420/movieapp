package com.movieapp.inventoryservice.exception;

@SuppressWarnings("serial")
public class ScreenNotFoundException extends ServiceException {



	public ScreenNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public ScreenNotFoundException(String message) {
		super(message);

	}


	

}
