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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.bookingservice.dto.APISuccessResponseDTO;
import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ApplicationException;
import com.movieapp.bookingservice.service.BookingService;




@RestController
@CrossOrigin
@RequestMapping(value="/booking/v1")
public class BookingController {
	
	public static final String MESSAGE="message";
	public static final int HTTP_STATUS_OK=200;
	@Autowired
	BookingService bookingService;
	
	private static Logger logger = LoggerFactory.getLogger(BookingController.class);
	
	/**
	 * @author Mahendran Dayalan
	 * @param booking
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * 
	 * Post API to add Booking
	 */
	@PostMapping
	public ResponseEntity<APISuccessResponseDTO> addBooking(@RequestBody Booking booking) throws ApplicationException 
	{
		logger.info("Entered to add booking");
		Booking bookingDetails=bookingService.addBooking(booking);
		
		APISuccessResponseDTO response = createResponse(bookingDetails, "Booking Saved Successfull");
		
		logger.info("Successfully added booking");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	
	/**
	 * @author Mahendran Dayalan
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * Get API to get all booking details
	 */
	@GetMapping
	public ResponseEntity<APISuccessResponseDTO> getAllBooking() throws ApplicationException {
		
		logger.info("Entered to Get all the booking details");
		List<BookingDTO> bookingDTOList = bookingService.getAllBooking();
		APISuccessResponseDTO response = createResponse(bookingDTOList, "Get all Booking Successfull");
		logger.info("Successfully retrieved booking details");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param bookingId
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * Get API to get booking details by id
	 */
	@GetMapping("/{bookingId}")
	public ResponseEntity<APISuccessResponseDTO> getBookingById(@PathVariable int bookingId) throws ApplicationException  {
		
		logger.info("Entered to get booking by id");
		BookingDTO bookingDTO = bookingService.getBookingById(bookingId);
		
		APISuccessResponseDTO response = createResponse(bookingDTO, "Get Booking by Id Successfull");
		
		logger.info("Successfully found booking by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}


	/**
	 * @author Mahendran Dayalan
	 * @param bookingId
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * Delete API to delete booking details
	 */
	@DeleteMapping("/{bookingId}")
	public ResponseEntity<APISuccessResponseDTO> deleteBooking(@PathVariable int bookingId) throws ApplicationException {
		logger.info("Entered to delete booking ");
		bookingService.deleteBooking(bookingId);
		
		APISuccessResponseDTO response = createResponse(null, "Booking Deleted Successfull");
		
		logger.info("Successfully deleted booking");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}
	
	/**
	 * @author Mahendran Dayalan
	 * @param object
	 * @param message
	 * @return APISuccessResponseDTO
	 * 
	 * To create Response using Object and message
	 */
	private APISuccessResponseDTO createResponse(Object object,String message) {
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(HTTP_STATUS_OK);
		response.setMessage(message);
		response.setBody(object);
		return response;
	}
	

}
