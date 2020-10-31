package com.movieapp.inventoryservice.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.inventoryservice.entity.Cast;
import com.movieapp.inventoryservice.entity.Genre;
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.repository.CastRepository;
import com.movieapp.inventoryservice.repository.GenreRepository;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.service.MovieService;
import com.movieapp.inventoryservice.service.impl.MovieServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest({ MovieController.class,MovieServiceImpl.class})
@AutoConfigureMockMvc(addFilters=false)
public class MovieControllerTest {

	
	@Autowired
	MovieService movieService;

	
	@MockBean
	MovieRepository movieRepository;

	@MockBean
	CastRepository castRepository;

	@MockBean
	GenreRepository genreRepository;

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void addMovieTest() throws JsonProcessingException, Exception {
		Mockito.when(genreRepository.getGenrIdByName(Mockito.anyString())).thenReturn(Optional.of(1));
		Mockito.when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(getGenre());
		Mockito.when(castRepository.save(Mockito.any(Cast.class))).thenReturn(getCast());
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(getMovie());
		MvcResult result = mockMvc.perform(post("/movie").contentType(MediaType.APPLICATION_JSON)
				.content(getMovieAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("abc"));
	}

	@Test
	public void addMovieTestError() throws JsonProcessingException, Exception {
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(post("/movie").contentType(MediaType.APPLICATION_JSON)
				.content(getMovieAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Movie Not Saved"));
	}

	@Test
	public void getAllMovieTest() throws Exception {
		List<Movie> movieList = new ArrayList<>();
		movieList.add(getMovie());
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
		MvcResult result = this.mockMvc.perform(get("/movie")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("abc"));
	}

	@Test
	public void getAllMovieNotFoundTest() throws Exception {
		List<Movie> movieList = new ArrayList<>();
		Mockito.when(movieRepository.findAll()).thenReturn(movieList);
		MvcResult result = this.mockMvc.perform(get("/movie")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Movies Found"));
	}

	@Test
	public void getAllMovieTestError() throws Exception {
		Mockito.when(movieRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/movie")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	public void getMovieByIdTest() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getMovie()));
		MvcResult result = mockMvc.perform(get("/movie/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("abc"));
	}

	@Test
	public void getMovieByIdNotFoundTest() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		MvcResult result = mockMvc.perform(get("/movie/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Movie Found for the ID 1"));
	}
	@Test
	public void getMovieByIdTestError() throws Exception {
		Mockito.when(movieRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/movie/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	public void updateMovieTest() throws JsonProcessingException, Exception {
		Mockito.when(genreRepository.getGenrIdByName(Mockito.anyString())).thenReturn(Optional.of(1));
		Mockito.when(genreRepository.save(Mockito.any(Genre.class))).thenReturn(getGenre());
		Mockito.when(castRepository.save(Mockito.any(Cast.class))).thenReturn(getCast());
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenReturn(getMovie());
		MvcResult result = mockMvc.perform(put("/movie").contentType(MediaType.APPLICATION_JSON)
				.content(getMovieAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("abc"));
	}

	@Test
	public void updateMovieTestError() throws JsonProcessingException, Exception {
		Mockito.when(movieRepository.save(Mockito.any(Movie.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(put("/movie").contentType(MediaType.APPLICATION_JSON)
				.content(getMovieAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains(""));
	}

	@Test
	public void deleteMovieTest() throws Exception {
		MvcResult result = this.mockMvc.perform(delete("/movie/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Movie deleted Successfull"));
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
		cast.setName("hero");
		List<Cast> casts = new ArrayList<>();
		casts.add(cast);
		return casts;
	}

}
