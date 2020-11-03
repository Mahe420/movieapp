package com.movieapp.inventoryservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.inventoryservice.entity.Cast;
import com.movieapp.inventoryservice.entity.Genre;
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.CastRepository;
import com.movieapp.inventoryservice.repository.GenreRepository;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.service.MovieService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MovieServiceImpl.class })
public class MovieServiceImplTest {

	@Autowired
	MovieService movieService;

	@MockBean
	MovieRepository movieRepository;

	@MockBean
	CastRepository castRepository;

	@MockBean
	GenreRepository genreRepository;

	@Test
	public void addMovieTest() throws JsonProcessingException, Exception {
		Mockito.when(genreRepository.getGenrIdByName(Mockito.anyString())).thenReturn(Optional.of(1));
		Mockito.when(castRepository.getcastIdByName(Mockito.anyString())).thenReturn(Optional.of(1));
		Mockito.when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(getGenre());
		Mockito.when(castRepository.save(Mockito.any(Cast.class))).thenReturn(getCast());
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(getMovie());
		Movie movie = movieService.addMovie(getMovie());
		assertEquals("abc", movie.getTitle());

	}

	@Test(expected = ServiceException.class)
	public void addMovieTestError() throws JsonProcessingException, Exception {
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Movie movie = movieService.addMovie(getMovie());
	}

	@Test
	public void getAllMovieTest() throws Exception {
		List<Movie> movieList = new ArrayList<>();
		movieList.add(getMovie());
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
		List<Movie> movieList2 = movieService.getAllMovies();
		assertEquals(1, movieList2.size());
	}

	@Test(expected = ApplicationException.class)
	public void getAllMovieNotFoundTest() throws Exception {
		List<Movie> movieList = new ArrayList<>();
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
		List<Movie> movieL = movieService.getAllMovies();
	}

	@Test(expected = ApplicationException.class)
	public void getAllMovieTestError() throws Exception {
		Mockito.when(movieRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<Movie> movieList = movieService.getAllMovies();
	}

	@Test
	public void getMovieByIdTest() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getMovie()));
		Movie movie = movieService.getMovieById(1);
		assertEquals("abc", movie.getTitle());
	}

	@Test(expected = ApplicationException.class)
	public void getMovieByIdNotFoundTest() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Movie movie = movieService.getMovieById(1);
	}

	@Test(expected = ApplicationException.class)
	public void getMovieByIdTestError() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		Movie movie = movieService.getMovieById(1);
	}

	@Test
	public void updateMovieTest() throws JsonProcessingException, Exception {
		Mockito.when(genreRepository.getGenrIdByName(Mockito.anyString())).thenReturn(Optional.of(1));
		Mockito.when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(getGenre());
		Mockito.when(castRepository.save(Mockito.any(Cast.class))).thenReturn(getCast());
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(getMovie());

		Movie movie = movieService.updateMovie(getMovie());
		assertEquals("abc", movie.getTitle());

	}

	@Test(expected = ServiceException.class)
	public void updateMovieTestError() throws JsonProcessingException, Exception {
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Movie movie = movieService.updateMovie(getMovie());
	}

	@Test
	public void deleteMovieTest() throws Exception {
		movieService.deleteMovie(1);
		Mockito.verify(movieRepository, Mockito.times(1)).deleteById(Mockito.any());
	}
	
	@Test(expected = ServiceException.class)
	public void deleteMovieTestError() throws JsonProcessingException, Exception {
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(movieRepository).deleteById(Mockito.anyInt());
		movieService.deleteMovie(1);
	}

	public Movie getMovie() {
		Movie movie = new Movie();
		movie.setTitle("abc");
		movie.setDuration(180);
		movie.setGenre(getGenreList());
		movie.setCast(getCastList());
		return movie;
	}

	public String getMovieAsJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getMovie());
	}

	public Genre getGenre() {
		Genre genre = new Genre();
		genre.setName("thriller");
		return genre;
	}

	public Cast getCast() {
		Cast cast = new Cast();
		cast.setName("hero");
		return cast;
	}

	public List<Genre> getGenreList() {
		Genre genre = new Genre();
		genre.setName("thriller");
		List<Genre> genres = new ArrayList<>();
		genres.add(genre);
		return genres;
	}

	public List<Cast> getCastList() {
		Cast cast = new Cast();
		cast.setId(1);
		cast.setName("hero");
		List<Cast> casts = new ArrayList<>();
		casts.add(cast);
		return casts;
	}
}
