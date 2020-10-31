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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.inventoryservice.entity.Movie;
import com.movieapp.inventoryservice.entity.Play;
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.repository.MovieRepository;
import com.movieapp.inventoryservice.repository.PlayRepository;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.service.PlayService;
import com.movieapp.inventoryservice.service.impl.PlayServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest({ PlayController.class, PlayServiceImpl.class })
@AutoConfigureMockMvc(addFilters=false)
public class PlayControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	PlayService playService;
	
	@MockBean
	PlayRepository playRepository;
	
	@MockBean
	ScreenRepository screenRepository;
	
	@MockBean
	MovieRepository movieRepository;
	
	@Test
	public void addPlayTest() throws Exception
	{
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenReturn(getPlay());
		MvcResult result = mockMvc.perform(post("/play/v1").contentType(MediaType.APPLICATION_JSON).content(getPlayAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("05:00 PM"));
	}
	
	@Test
	public void addPlayTestError() throws JsonProcessingException, Exception 
	{
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(post("/play/v1").contentType(MediaType.APPLICATION_JSON).content(getPlayAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Play Not Saved"));
	}
	
	@Test
	public void getAllPlayTest() throws Exception
	{
		List<Play> playList = new ArrayList<>();
		playList.add(getPlay());
		Mockito.when(playRepository.findAll()).thenReturn(playList);
		MvcResult result = this.mockMvc.perform(get("/play/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("05:00 PM"));
	}
	
	@Test
	public void getAllPlayNotFoundTest() throws Exception
	{
		List<Play> playList = new ArrayList<>();
		Mockito.when(playRepository.findAll()).thenReturn(playList);
		MvcResult result = this.mockMvc.perform(get("/play/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Play Found"));
	}
	
	@Test
	public void getAllPlayTestError() throws Exception
	{
		Mockito.when(playRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/play/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void getPlayByIdTest() throws Exception
	{
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getPlay()));
		MvcResult result = mockMvc.perform(get("/play/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("05:00 PM"));
	}
	
	@Test
	public void getPlayByIdNotFoundTest() throws Exception
	{
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		MvcResult result = mockMvc.perform(get("/play/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Play Found for he ID 1"));
	}
    
	@Test
	public void getPlayByIdTestError() throws Exception
	{
		Mockito.when(playRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(get("/play/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void updatePlayTest() throws Exception
	{
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenReturn(getPlay());
		MvcResult result = mockMvc.perform(put("/play/v1").contentType(MediaType.APPLICATION_JSON).content(getPlayAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("05:00 PM"));
	}
	
	@Test
	public void updatePlayTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(playRepository.save(Mockito.any(Play.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(put("/play/v1").contentType(MediaType.APPLICATION_JSON).content(getPlayAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Play Not Updated"));
	}
	
	@Test
	public void deletePlayTest() throws Exception
	{
		MvcResult result = this.mockMvc.perform(delete("/play/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Play Deleted Successfull"));
	}
	
	public Play getPlay()
	{
		Play play=new Play();
		play.setScreen(getScreen());
		play.setMovie(getMovie());
		play.setStartTiming("05:00 PM");;
		return play;
	}
	
	public Screen getScreen()
	{
		Screen screen=new Screen();
		screen.setId(1);
		return screen;
	}
	
	public Movie getMovie()
	{
		Movie movie=new Movie();
		movie.setId(1);
		return movie;
	}
	
	
	public String getPlayAsJson() throws JsonProcessingException{
			return new ObjectMapper().writeValueAsString(getPlay());
	}

}
