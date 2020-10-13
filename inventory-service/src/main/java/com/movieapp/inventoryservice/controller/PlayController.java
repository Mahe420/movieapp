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
import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.exception.PlayNotFoundException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.PlayService;

@RestController
@CrossOrigin
public class PlayController {

	public static final String MESSAGE = "message";
	@Autowired
	PlayService playService;

	private static Logger logger = LoggerFactory.getLogger(PlayController.class);

	@PostMapping("/play")
	public ResponseEntity<APISuccessResponseDTO> addPlay(@RequestBody Play play) throws ServiceException {
		logger.info("Add play details");
		Play playDetails = playService.addPlay(play);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Play Added Successfull");
		response.setBody(playDetails);
		logger.info("Play details added");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/play")
	public ResponseEntity<APISuccessResponseDTO> getAllPlay() throws PlayNotFoundException {
		logger.info("Get all play details");
		List<Play> playList = playService.getAllPlay();
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get all Plays Successfull");
		response.setBody(playList);
		logger.info("Got all play details");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/play/{playId}")
	public ResponseEntity<APISuccessResponseDTO> getPlayById(@PathVariable int playId) throws PlayNotFoundException {
		logger.info("Get play details by id");
		Play play = playService.getPlayById(playId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get Plays by Id Successfull");
		response.setBody(play);
		logger.info("Got play by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@PutMapping("/play")
	public ResponseEntity<APISuccessResponseDTO> updatePlay(@RequestBody Play play) throws ServiceException {
		logger.info("Update play details");
		Play playDetails = playService.updatePlay(play);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Play Updated Successfull");
		response.setBody(playDetails);
		logger.info("Play details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@DeleteMapping("/play/{playId}")
	public ResponseEntity<APISuccessResponseDTO> deletePlay(@PathVariable int playId) throws ServiceException {
		logger.info("Delete play by id");
		playService.deletePlay(playId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Play Deleted Successfull");
		response.setBody(null);
		logger.info("play deleted");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

}
