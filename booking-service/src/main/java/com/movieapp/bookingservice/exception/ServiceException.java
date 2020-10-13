package com.movieapp.bookingservice.exception;


public class ServiceException extends ApplicationException{



	/**
	 * 
	 */
	private static final long serialVersionUID = -3291330898703511740L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ServiceException(String message) {
		super(message);
	
	}


	

}
