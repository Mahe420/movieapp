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
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.service.MovieService;

@RestController
@CrossOrigin
@RequestMapping(value="/movie/v1")
public class MovieController {

	public static final String MESSAGE = "message";

	public static final int HTTP_STATUS_OK=200;
	@Autowired
	MovieService movieService;

	private static Logger logger = LoggerFactory.getLogger(MovieController.class);

	/**
	 * @author Mahendran Dayalan
	 * @param movie
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ServiceException
	 * 
	 * Post API to add Movies
	 */
	@PostMapping
	public ResponseEntity<APISuccessResponseDTO> addMovie(@RequestBody Movie movie) throws ServiceException {
		logger.info("Entered to insert a movie");
		Movie movieDetails = movieService.addMovie(movie);
		APISuccessResponseDTO response = createResponse(movieDetails, "Movie Saved Successfull");
		logger.info("Successfully added movie");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * Get API to get all movie details
	 */
	@GetMapping
	public ResponseEntity<APISuccessResponseDTO> getAllMovies() throws ApplicationException {
		logger.info("To get all movies");
		List<Movie> movieList = movieService.getAllMovies();
		APISuccessResponseDTO response = createResponse(movieList, "Get all Movie Successfull");
		logger.info("Successfully retrieved all movies");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param movieId
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ApplicationException
	 * 
	 * Get API to retrive movie based on ID
	 */
	@GetMapping("/{movieId}")
	public ResponseEntity<APISuccessResponseDTO> getMovieById(@PathVariable int movieId) throws ApplicationException {
		logger.info("To get movie by id");
		Movie movie = movieService.getMovieById(movieId);
		APISuccessResponseDTO response = createResponse(movie, "Get Movie by Id Successfull");
		logger.info("Got movie by id");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param movie
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ServiceException
	 * 
	 * Put API to update movie details
	 */
	@PutMapping
	public ResponseEntity<APISuccessResponseDTO> updateMovie(@RequestBody Movie movie) throws ServiceException {
		logger.info("Update movie details");
		Movie movieDetails = movieService.updateMovie(movie);
		APISuccessResponseDTO response = createResponse(movieDetails, "Movie details updated Successfully");
		logger.info("Movie details updated");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}

	/**
	 * @author Mahendran Dayalan
	 * @param movieId
	 * @return ResponseEntity<APISuccessResponseDTO>
	 * @throws ServiceException
	 * 
	 * Delete API to delete movie details
	 */
	@DeleteMapping("/{movieId}")
	public ResponseEntity<APISuccessResponseDTO> deleteMovie(@PathVariable int movieId) throws ServiceException {
		logger.info("Delete movie by id");
		movieService.deleteMovie(movieId);
		APISuccessResponseDTO response = createResponse(null,"Movie deleted Successfull");
		logger.info("deleted movie");
		return ResponseEntity.status(HttpStatus.ACCEPTED).header(MESSAGE, String.valueOf(HttpStatus.ACCEPTED))
				.body(response);
	}
	
	/**
	 * @author Mahendran Dayalan
	 * @param object
	 * @param message
	 * @return APISuccessResponseDTO
	 * 
	 * create response using object and message
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
