package com.movieapp.bookingservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.bookingservice.dto.APISuccessResponseDTO;
import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ApplicationException;
import com.movieapp.bookingservice.exception.ServiceException;
import com.movieapp.bookingservice.service.BookingService;



@RestController
@CrossOrigin
public class BookingController {
	
	public static final String MESSAGE="message";
	@Autowired
	BookingService bookingService;
	
	private static Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	@PostMapping("/booking")
	public ResponseEntity<APISuccessResponseDTO> addBooking(@RequestBody Booking booking) throws ServiceException 
	{
		logger.info("Entered to add booking");
		Booking bookingDetails=bookingService.addBooking(booking);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Booking Saved Successfull");
		response.setBody(bookingDetails);
		logger.info("Successfully added booking");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/booking")
	public ResponseEntity<APISuccessResponseDTO> getAllBooking(HttpServletRequest req) throws ApplicationException {
		
		logger.info("Entered to Get all the booking details");
		List<BookingDTO> bookingDTOList = bookingService.getAllBooking();
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get all Booking Successfull");
		response.setBody(bookingDTOList);
		logger.info("Successfully retrieved booking details");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/booking/{bookingId}")
	public ResponseEntity<APISuccessResponseDTO> getBookingById(@PathVariable int bookingId) throws ServiceException  {
		
		logger.info("Entered to get booking by id");
		BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get Booking by Id Successfull");
		response.setBody(bookingDTO);
		logger.info("Successfully found booking by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}


	@DeleteMapping("/booking/{bookingId}")
	public ResponseEntity<APISuccessResponseDTO> deleteBooking(@PathVariable int bookingId) throws ServiceException {
		logger.info("Entered to delete booking ");
		bookingService.deleteBooking(bookingId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Booking Deleted Successfull");
		response.setBody(null);
		logger.info("Successfully deleted booking");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}
	

}
