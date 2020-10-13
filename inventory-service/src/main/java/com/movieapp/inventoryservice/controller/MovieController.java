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
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.exception.MovieNotFoundException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.MovieService;

@RestController
@CrossOrigin
public class MovieController {

	public static final String MESSAGE = "message";

	@Autowired
	MovieService movieService;

	private static Logger logger = LoggerFactory.getLogger(MovieController.class);

	@PostMapping("/movie")
	public ResponseEntity<APISuccessResponseDTO> addMovie(@RequestBody Movie movie) throws ServiceException {
		logger.info("Entered to insert a movie");
		Movie movieDetails = movieService.addMovie(movie);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Movie Saved Successfull");
		response.setBody(movieDetails);
		logger.info("Successfully added movie");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/movie")
	public ResponseEntity<APISuccessResponseDTO> getAllMovies() throws MovieNotFoundException {
		logger.info("To get all movies");
		List<Movie> movieList = movieService.getAllMovies();
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get all Movie Successfull");
		response.setBody(movieList);
		logger.info("Successfully retrieved all movies");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@GetMapping("/movie/{movieId}")
	public ResponseEntity<APISuccessResponseDTO> getMovieById(@PathVariable int movieId) throws ServiceException {
		logger.info("To get movie by id");
		Movie movie = movieService.getMovieById(movieId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Get Movie by Id Successfull");
		response.setBody(movie);
		logger.info("Got movie by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@PutMapping("/movie")
	public ResponseEntity<APISuccessResponseDTO> updateMovie(@RequestBody Movie movie) throws ServiceException {
		logger.info("Update movie details");
		Movie movieDetails = movieService.updateMovie(movie);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Movie Updated Successfull");
		response.setBody(movieDetails);
		logger.info("Movie details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	@DeleteMapping("/movie/{movieId}")
	public ResponseEntity<APISuccessResponseDTO> deleteMovie(@PathVariable int movieId) throws ServiceException {
		logger.info("Delete movie by id");
		movieService.deleteMovie(movieId);
		APISuccessResponseDTO response = new APISuccessResponseDTO();
		response.setHttpStatus(HttpStatus.ACCEPTED);
		response.setStatusCode(200);
		response.setMessage("Movie Deleted Successfull");
		response.setBody(null);
		logger.info("deleted movie");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}
}
