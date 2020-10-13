package com.movieapp.bookingservice.exception;

@SuppressWarnings("serial")
public class BookingNotFoundException extends ServiceException {

	public BookingNotFoundException(String message, Throwable root) {
		super(message, root);
	
	}

	public BookingNotFoundException(String message) {
		super(message);
	
	}

}
