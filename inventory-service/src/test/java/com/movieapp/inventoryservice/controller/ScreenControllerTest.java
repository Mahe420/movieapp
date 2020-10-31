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
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.ScreenService;
import com.movieapp.inventoryservice.service.impl.ScreenServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest({ ScreenController.class, ScreenServiceImpl.class })
@AutoConfigureMockMvc(addFilters=false)
public class ScreenControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ScreenService screenService;

	@MockBean
	ScreenRepository screenRepository;

	@MockBean
	TheatreRepository theatreRepository;
	
	@Test
	public void addScreenTest() throws Exception
	{
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenReturn(getScreen());
		MvcResult result = mockMvc.perform(post("/screen").contentType(MediaType.APPLICATION_JSON).content(getScreenAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("screen-1"));
	}
	
	@Test
	public void addScreenTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(post("/screen").contentType(MediaType.APPLICATION_JSON).content(getScreenAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Screen Not Saved"));
	}
	
	@Test
	public void getAllScreenTest() throws Exception
	{
		List<Screen> screenList = new ArrayList<>();
		screenList.add(getScreen());
		Mockito.when(screenRepository.findAll()).thenReturn(screenList);
		MvcResult result = this.mockMvc.perform(get("/screen")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("screen-1"));
	}
	
	@Test
	public void getAllScreenNotFoundTest() throws Exception
	{
		List<Screen> screenList = new ArrayList<>();
		Mockito.when(screenRepository.findAll()).thenReturn(screenList);
		MvcResult result = this.mockMvc.perform(get("/screen")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Screen Not Found"));
	}
	
	@Test
	public void getAllScreenTestError() throws Exception
	{
		Mockito.when(screenRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/screen")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void getScreenByIdTest() throws Exception
	{
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getScreen()));
		MvcResult result = mockMvc.perform(get("/screen/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("screen-1"));
	}
    
	@Test
	public void getScreenByIdNotFoundTest() throws Exception
	{
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		MvcResult result = mockMvc.perform(get("/screen/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Screen Not Found for the ID 1"));
	}
	
	@Test
	public void getScreenByIdTestError() throws Exception
	{
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(get("/screen/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void updateScreenTest() throws Exception
	{
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenReturn(getScreen());
		MvcResult result = mockMvc.perform(put("/screen").contentType(MediaType.APPLICATION_JSON).content(getScreenAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("screen-1"));
	}
	
	@Test
	public void updateScrreenTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(put("/screen").contentType(MediaType.APPLICATION_JSON).content(getScreenAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Screen Not Updated"));
	}
	
	@Test
	public void deleteScreenTest() throws Exception
	{
		MvcResult result = this.mockMvc.perform(delete("/screen/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Screen deleted Successfull"));
	}
	
	public Screen getScreen()
	{
		Screen screen=new Screen();
		screen.setTheatre(getTheatre());
		screen.setName("screen-1");
		return screen;
	}
	
	public Theatre getTheatre()
	{
		Theatre theatre=new Theatre();
		theatre.setId(1);
		return theatre;
	}
	
	public String getScreenAsJson() throws JsonProcessingException{
			return new ObjectMapper().writeValueAsString(getScreen());	
	}
}
