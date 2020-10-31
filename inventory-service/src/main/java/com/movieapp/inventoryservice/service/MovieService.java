package com.movieapp.inventoryservice.service;

import java.util.List;

import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;

public interface MovieService {

	List<Movie> getAllMovies() throws ApplicationException;

	Movie addMovie(Movie movie) throws ServiceException;

	Movie updateMovie(Movie movie) throws ServiceException;

	void deleteMovie(int movieId) throws ServiceException;

	Movie getMovieById(int movieId) throws ApplicationException;

}
