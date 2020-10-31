package com.movieapp.inventoryservice.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.inventoryservice.dto.APISuccessResponseDTO;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.TheatreService;

@RestController
@CrossOrigin
@RequestMapping(value="/theatre/v1")
public class TheatreController {
	
	public static final String MESSAGE="message";

	public static final int HTTP_STATUS_OK=200;
	@Autowired
	TheatreService theatreService;
	
	private static Logger logger = LoggerFactory.getLogger(TheatreController.class);
	
	@PostMapping
	public ResponseEntity<APISuccessResponseDTO> addTheatre(@RequestBody Theatre theatre ) throws ServiceException
	{
		logger.info("Add theatre");
		Theatre theatreDetails=theatreService.addTheatre(theatre);
		APISuccessResponseDTO response = createResponse(theatreDetails, "Theatre Added Successfull");
		logger.info("Theatre added");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);	
	}
	
	@GetMapping
	public ResponseEntity<APISuccessResponseDTO> getAllTheatre() throws ApplicationException {
		logger.info("Get all theatre values");
		List<Theatre> theatreList =theatreService.getAlltheatre();
		APISuccessResponseDTO response = createResponse(theatreList, "Get all Theatre Successfull");
		logger.info("All theatre values retrieved");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
	
	@GetMapping("/{theatreId}")
	public ResponseEntity<APISuccessResponseDTO> getTheatreById(@PathVariable int theatreId) throws ApplicationException {
		logger.info("Get theatre by id");
		Theatre theatre=theatreService.getTheatreById(theatreId);
		APISuccessResponseDTO response = createResponse(theatre, "Get Theatre by Id Successfull");
		logger.info("theatre by id retrieved");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
     
	@PutMapping
	public ResponseEntity<APISuccessResponseDTO> updateTheatre(@RequestBody Theatre theatre) throws ServiceException
	{
		logger.info("Update theatre details");
		Theatre theatreDetails=theatreService.updateTheatre(theatre);
		APISuccessResponseDTO response = createResponse(theatreDetails, "Theatre Updated Successfull");
		logger.info("Theatre details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);	
	}

	@DeleteMapping("/{theatreId}")
	public ResponseEntity<APISuccessResponseDTO> deleteTheatre(@PathVariable int theatreId) throws ServiceException
	{
		logger.info("Delete theatre by id");
		theatreService.deleteTheatre(theatreId);
		
		APISuccessResponseDTO response = createResponse(null, "Theatre Deleted Successfull");
		
		logger.info("theatre by id deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
	
	private APISuccessResponseDTO createResponse(Object object,String message) {
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(HTTP_STATUS_OK);
		response.setMessage(message);
		response.setBody(object);
		return response;
	}
}
