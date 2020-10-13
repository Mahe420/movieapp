package com.movieapp.inventoryservice.exception;

public class ServiceException extends ApplicationException{



	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message) {
		super(message);
	
	}


	

}
