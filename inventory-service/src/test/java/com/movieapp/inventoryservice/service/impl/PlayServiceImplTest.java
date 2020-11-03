package com.movieapp.inventoryservice.service.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.repository.PlayRepository;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.service.PlayService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { PlayServiceImpl.class })
public class PlayServiceImplTest {

	@Autowired
	PlayService playService;

	@MockBean
	PlayRepository playRepository;

	@MockBean
	ScreenRepository screenRepository;

	@MockBean
	MovieRepository movieRepository;

	@Test
	public void addPlayTest() throws Exception {
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenReturn(getPlay());
		Play play = playService.addPlay(getPlay());
		assertEquals(1, play.getId());

	}

	@Test(expected = ServiceException.class)
	public void addPlayTestError() throws JsonProcessingException, Exception {
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Play play = playService.addPlay(getPlay());
	}

	@Test
	public void getAllPlayTest() throws Exception {
		List<Play> playList = new ArrayList<>();
		playList.add(getPlay());
		Mockito.when(playRepository.findAll()).thenReturn(playList);
		List<Play> playList1 = playService.getAllPlay();
		assertEquals(1, playList1.size());
	}

	@Test(expected = ApplicationException.class)
	public void getAllPlayNotFoundTest() throws Exception {
		List<Play> playList = new ArrayList<>();
		Mockito.when(playRepository.findAll()).thenReturn(playList);
		List<Play> playList1 = playService.getAllPlay();
	}

	@Test(expected = ApplicationException.class)
	public void getAllPlayTestError() throws Exception {
		Mockito.when(playRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<Play> playList1 = playService.getAllPlay();
	}

	@Test
	public void getPlayByIdTest() throws Exception {
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getPlay()));
		Play play = playService.getPlayById(1);
		assertEquals(1, play.getId());
	}

	@Test(expected = ApplicationException.class)
	public void getPlayByIdNotFoundTest() throws Exception {
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Play play = playService.getPlayById(1);
	}

	@Test(expected = ApplicationException.class)
	public void getPlayByIdTestError() throws Exception {
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		Play play = playService.getPlayById(1);
	}

	@Test
	public void updatePlayTest() throws Exception {
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenReturn(getPlay());
		Play play = playService.updatePlay(getPlay());
		assertEquals(1, play.getId());
	}

	@Test(expected = ServiceException.class)
	public void updatePlayTestError() throws JsonProcessingException, Exception {
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Play play = playService.updatePlay(getPlay());
	}

	@Test
	public void deletePlayTest() throws Exception {
		playService.deletePlay(1);
		Mockito.verify(playRepository, Mockito.times(1)).deleteById(Mockito.any());
	}

	@Test(expected = ServiceException.class)
	public void deletePlayTestError() throws Exception {
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(playRepository).deleteById(Mockito.anyInt());
		playService.deletePlay(1);
	}

	public Play getPlay() {
		Play play = new Play();
		play.setId(1);
		play.setScreen(getScreen());
		play.setMovie(getMovie());
		play.setStartTiming("05:00 PM");
		;
		return play;
	}

	public Screen getScreen() {
		Screen screen = new Screen();
		screen.setId(1);
		return screen;
	}

	public Movie getMovie() {
		Movie movie = new Movie();
		movie.setId(1);
		return movie;
	}
}
