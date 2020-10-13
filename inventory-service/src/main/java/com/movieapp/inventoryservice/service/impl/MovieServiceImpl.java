package com.movieapp.inventoryservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieapp.inventoryservice.entity.Cast;
import com.movieapp.inventoryservice.entity.Genre;
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.exception.MovieNotFoundException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.CastRepository;
import com.movieapp.inventoryservice.repository.GenreRepository;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.service.MovieService;

@Service
@Transactional
public class MovieServiceImpl implements MovieService {

	@Autowired
	MovieRepository movieRepository;
	@Autowired
	CastRepository castRepository;
	@Autowired
	GenreRepository genreRepository;

	public static final String ERROR_CONNECT="Failed to connect";
	@Override
	public Movie addMovie(Movie movie) throws ServiceException {
		try {
			List<Cast> cast = movie.getCast();
			cast.forEach(value->{
				Optional<Integer>castId=castRepository.getcastIdByName(value.getName());
				if(castId.isPresent()) {
					value.setId(castId.get());
				}
			});
			cast.forEach(value -> castRepository.save(value));
			List<Genre> genre = movie.getGenre();
			genre.forEach(value -> {
				Optional<Integer> genreId = genreRepository.getGenrIdByName(value.getName());
				if (genreId.isPresent()) {
					value.setId(genreId.get());
				}
			});
			genre.forEach(value -> genreRepository.save(value));
			return movieRepository.save(movie);
		} catch (DataAccessException e) {
			throw new ServiceException("Movie Not Saved", e.getCause());
		}
	}

	@Override
	public List<Movie> getAllMovies() throws MovieNotFoundException {
		try {
			List<Movie> movieList  = movieRepository.findAll();
			if (movieList.isEmpty()) {
				throw new MovieNotFoundException("No Movies Found");
			}
			return movieList;
		} catch (DataAccessException e) {
			throw new MovieNotFoundException(ERROR_CONNECT, e.getCause());
		}
	}

	@Override
	public Movie updateMovie(Movie movie) throws ServiceException {
		try {
			List<Cast> cast = movie.getCast();
			cast.forEach(value -> castRepository.save(value));
			List<Genre> genre = movie.getGenre();
			movie.getGenre().forEach(value -> {
				Optional<Integer> genreId = genreRepository.getGenrIdByName(value.getName());
				if (genreId.isPresent()) {
					value.setId(genreId.get());
				}
			});
			genre.forEach(value -> genreRepository.save(value));
			return movieRepository.save(movie);
		} catch (DataAccessException e) {
			throw new ServiceException("Movie Not Updated", e.getCause());
		}
	}

	@Override
	public void deleteMovie(int movieId) throws ServiceException {
		try {
		movieRepository.deleteById(movieId);
		}catch(DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());	
		}
	}

	@Override
	public Movie getMovieById(int movieId) throws ServiceException {
		try {
			return movieRepository.findById(movieId)
					.orElseThrow(() -> new MovieNotFoundException("No Movie Found for the ID" + " " + movieId));
		} catch (DataAccessException e) {
			throw new ServiceException(ERROR_CONNECT, e.getCause());
		}
	}

}
