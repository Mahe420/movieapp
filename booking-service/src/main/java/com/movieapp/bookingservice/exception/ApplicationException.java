package com.movieapp.bookingservice.exception;


public class ApplicationException extends Exception{



	/**
	 * 
	 */
	private static final long serialVersionUID = 8307158020575528736L;

	public ApplicationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ApplicationException(String message) {
		super(message);
		
	}


	

}
