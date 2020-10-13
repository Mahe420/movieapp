package com.movieapp.bookingservice.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.movieapp.bookingservice.dto.APIErrorResponseDTO;
import com.movieapp.bookingservice.exception.ApplicationException;

@RestControllerAdvice
public class ErrorHandler {
	@ExceptionHandler(value= {ApplicationException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleException(ApplicationException e)
	{
		APIErrorResponseDTO apiError = new APIErrorResponseDTO(HttpStatus.NOT_FOUND,
				404, e.getMessage());
		return new ResponseEntity<> (apiError, HttpStatus.OK);
	}
	@ExceptionHandler(value= {Exception.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleException(Exception e)
	{
		APIErrorResponseDTO apiError = new APIErrorResponseDTO(HttpStatus.NOT_FOUND,
				404, e.getMessage(),e.getCause());
		return new ResponseEntity<> (apiError, HttpStatus.OK);
	}

}
