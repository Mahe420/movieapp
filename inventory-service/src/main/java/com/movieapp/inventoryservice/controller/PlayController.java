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
import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.PlayService;

@RestController
@CrossOrigin
@RequestMapping(value="/play/v1")
public class PlayController {

	public static final String MESSAGE = "message";

	public static final int HTTP_STATUS_OK = 200;
	@Autowired
	PlayService playService;

	private static Logger logger = LoggerFactory.getLogger(PlayController.class);

	/**
	 * @author Mahendran Dayalan
	 * @param play
	 * @return
	 * @throws ServiceException
	 * 
	 * Post API to add play
	 */
	@PostMapping
	public ResponseEntity<APISuccessResponseDTO> addPlay(@RequestBody Play play) throws ServiceException {
		logger.info("Add play details");
		Play playDetails = playService.addPlay(play);
		APISuccessResponseDTO response = createResponse(playDetails, "Play Added Successfull");
		logger.info("Play details added");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @return
	 * @throws ApplicationException
	 * 
	 * Get API to retrieve all play details
	 */
	@GetMapping
	public ResponseEntity<APISuccessResponseDTO> getAllPlay() throws ApplicationException {
		logger.info("Get all play details");
		List<Play> playList = playService.getAllPlay();
		APISuccessResponseDTO response = createResponse(playList, "Get all Plays Successfull");
		logger.info("Got all play details");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param playId
	 * @return
	 * @throws ApplicationException
	 * 
	 * Get API to retrieve play based on id
	 */
	@GetMapping("/{playId}")
	public ResponseEntity<APISuccessResponseDTO> getPlayById(@PathVariable int playId) throws ApplicationException {
		logger.info("Get play details by id");
		Play play = playService.getPlayById(playId);
		APISuccessResponseDTO response = createResponse(play, "Get Plays by Id Successfull");
		logger.info("Got play by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param play
	 * @return
	 * @throws ServiceException
	 * 
	 * Put API to update play
	 */
	@PutMapping
	public ResponseEntity<APISuccessResponseDTO> updatePlay(@RequestBody Play play) throws ServiceException {
		logger.info("Update play details");
		Play playDetails = playService.updatePlay(play);
		APISuccessResponseDTO response = createResponse(playDetails, "Play Updated Successfull");
		logger.info("Play details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param playId
	 * @return
	 * @throws ServiceException
	 * 
	 * Delete API to delete play
	 */
	@DeleteMapping("/{playId}")
	public ResponseEntity<APISuccessResponseDTO> deletePlay(@PathVariable int playId) throws ServiceException {
		logger.info("Delete play by id");
		playService.deletePlay(playId);
		APISuccessResponseDTO response = createResponse(null, "Play Deleted Successfull");
		logger.info("play deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param object
	 * @param message
	 * @return
	 * 
	 * create response using object and message
	 */
	private APISuccessResponseDTO createResponse(Object object, String message) {
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(HTTP_STATUS_OK);
		response.setMessage(message);
		response.setBody(object);
		return response;
	}

}
