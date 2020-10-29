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
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.exception.ScreenNotFoundException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.ScreenService;

@RestController
@CrossOrigin
public class ScreenController {

	public static final String MESSAGE = "message";

	public static final int HTTP_STATUS_OK=200;
	@Autowired
	ScreenService screenService;

	private static Logger logger = LoggerFactory.getLogger(ScreenController.class);
	
	@PostMapping("/screen")
	public ResponseEntity<APISuccessResponseDTO> addScreen(@RequestBody Screen screen) throws ServiceException {
		logger.info("Add screen");
		Screen screenDetails = screenService.addScreen(screen);
		
		APISuccessResponseDTO response = createResponse(screenDetails, "Screen Added Successfull");
		
		logger.info("Screen added");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/screen")
	public ResponseEntity<APISuccessResponseDTO> getAllScreen() throws ScreenNotFoundException {
		logger.info("Get all screens");
		List<Screen> screenList = screenService.getAllScreen();
		APISuccessResponseDTO response = createResponse(screenList, "Get all Screens Successfull");
		logger.info("Retrieved all screens");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/screen/{screenId}")
	public ResponseEntity<APISuccessResponseDTO> getScreenById(@PathVariable int screenId)
			throws ScreenNotFoundException {
		logger.info("Get screen by id");
		Screen screen = screenService.getScreenById(screenId);
		APISuccessResponseDTO response = createResponse(screen, "Get Screens by ID Successfull");
		logger.info("Got screen by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@PutMapping("/screen")
	public ResponseEntity<APISuccessResponseDTO> updateScreen(@RequestBody Screen screen) throws ServiceException {
		logger.info("Update screen details");
		Screen screenDetails = screenService.updateScreen(screen);
		APISuccessResponseDTO response = createResponse(screenDetails, "Update Screens Successfull");
		logger.info("Screen detaisl updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@DeleteMapping("/screen/{screenId}")
	public ResponseEntity<APISuccessResponseDTO> deleteScreen(@PathVariable int screenId) throws ServiceException {
		logger.info("delete screen by id");
		screenService.deleteScreen(screenId);
		APISuccessResponseDTO response = createResponse(null,"Delete screen Successfull");
		logger.info("screen by id deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
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
