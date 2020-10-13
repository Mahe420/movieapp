package com.movieapp.inventoryservice.exception;

@SuppressWarnings("serial")
public class PlayNotFoundException extends ServiceException{


	public PlayNotFoundException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public PlayNotFoundException(String message) {
		super(message);
		
	}


}
