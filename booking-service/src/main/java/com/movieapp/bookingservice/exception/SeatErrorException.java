package com.movieapp.bookingservice.exception;

@SuppressWarnings("serial")
public class SeatErrorException extends ServiceException {

	public SeatErrorException(String message, Throwable root) {
		super(message, root);
	
	}

	public SeatErrorException(String message) {
		super(message);
	
	}

}
