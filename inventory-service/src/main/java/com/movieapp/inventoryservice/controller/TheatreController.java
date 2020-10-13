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
import org.springframework.web.bind.annotation.RestController;

import com.movieapp.inventoryservice.dto.APISuccessResponseDTO;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.exception.TheatreNotFoundException;
import com.movieapp.inventoryservice.service.TheatreService;

@RestController
@CrossOrigin
public class TheatreController {
	
	public static final String MESSAGE="message";
	@Autowired
	TheatreService theatreService;
	
	private static Logger logger = LoggerFactory.getLogger(TheatreController.class);
	
	@PostMapping("/theatre")
	public ResponseEntity<APISuccessResponseDTO> addTheatre(@RequestBody Theatre theatre ) throws ServiceException
	{
		logger.info("Add theatre");
		Theatre theatreDetails=theatreService.addTheatre(theatre);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Theatre Added Successfull");
		response.setBody(theatreDetails);
		logger.info("Theatre added");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);	
	}
	
	@GetMapping("/theatre")
	public ResponseEntity<APISuccessResponseDTO> getAllTheatre() throws TheatreNotFoundException {
		logger.info("Get all theatre values");
		List<Theatre> theatreList =theatreService.getAlltheatre();
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get all Theatre Successfull");
		response.setBody(theatreList);
		logger.info("All theatre values retrieved");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
	
	@GetMapping("/theatre/{theatreId}")
	public ResponseEntity<APISuccessResponseDTO> getTheatreById(@PathVariable int theatreId) throws TheatreNotFoundException {
		logger.info("Get theatre by id");
		Theatre theatre=theatreService.getTheatreById(theatreId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get Theatre by Id Successfull");
		response.setBody(theatre);
		logger.info("theatre by id retrieved");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
     
	@PutMapping("/theatre")
	public ResponseEntity<APISuccessResponseDTO> updateTheatre(@RequestBody Theatre theatre) throws ServiceException
	{
		logger.info("Update theatre details");
		Theatre theatreDetails=theatreService.updateTheatre(theatre);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Theatre Updated Successfull");
		response.setBody(theatreDetails);
		logger.info("Theatre details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);	
	}

	@DeleteMapping("/theatre/{theatreId}")
	public ResponseEntity<APISuccessResponseDTO> deleteTheatre(@PathVariable int theatreId) throws ServiceException
	{
		logger.info("Delete theatre by id");
		theatreService.deleteTheatre(theatreId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Theatre Deleted Successfull");
		response.setBody(null);
		logger.info("theatre by id deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED)).body(response);
	}
}
