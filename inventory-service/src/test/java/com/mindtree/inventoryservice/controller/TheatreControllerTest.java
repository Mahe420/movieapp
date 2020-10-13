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
import com.movieapp.inventoryservice.entity.Address;
import com.movieapp.inventoryservice.entity.Location;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.repository.AddressRepository;
import com.movieapp.inventoryservice.repository.LocationRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.TheatreService;
import com.movieapp.inventoryservice.service.impl.TheatreServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest({ TheatreController.class, TheatreServiceImpl.class })
@AutoConfigureMockMvc(addFilters=false)
public class TheatreControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	TheatreService theatreService;

	@MockBean
	TheatreRepository theatreRepository;

	@MockBean
	AddressRepository addressRepository;
	
	@MockBean
	LocationRepository locationRepository;
	
	@Test
	public void addTheatreTest() throws Exception
	{
		Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(getAddress());
		Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(getLocation());
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(getTheatre());
		MvcResult result = mockMvc.perform(post("/theatre").contentType(MediaType.APPLICATION_JSON).content(getTheatreAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("sky walk"));
	}

	@Test
	public void addTheatreTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(post("/theatre").contentType(MediaType.APPLICATION_JSON).content(getTheatreAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Theatre Not Saved"));
	}
	
	@Test
	public void getAllTheatreTest() throws Exception
	{
		List<Theatre> theatreList = new ArrayList<>();
		theatreList.add(getTheatre());
		Mockito.when(theatreRepository.findAll()).thenReturn(theatreList);
		MvcResult result = this.mockMvc.perform(get("/theatre")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("sky walk"));
	}
	
	@Test
	public void getAllTheatreNotFoundTest() throws Exception
	{
		List<Theatre> theatreList = new ArrayList<>();
		Mockito.when(theatreRepository.findAll()).thenReturn(theatreList);
		MvcResult result = this.mockMvc.perform(get("/theatre")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Theatre Found"));
	}
	
	@Test
	public void getAllTheatreTestError() throws Exception
	{
		Mockito.when(theatreRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/theatre")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void getTheatreByIdTest() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getTheatre()));
		MvcResult result = mockMvc.perform(get("/theatre/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("sky walk"));
	}
	
	@Test
	public void getTheatreByIdNotFoundTest() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		MvcResult result = mockMvc.perform(get("/theatre/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Theatre Found for the ID 1"));
	}
	
	@Test
	public void getTheatreByIdTestError() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(get("/theatre/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}
	
	@Test
	public void updateTheatreTest() throws Exception
	{
		Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(getAddress());
		Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(getLocation());
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(getTheatre());
		MvcResult result = mockMvc.perform(put("/theatre").contentType(MediaType.APPLICATION_JSON).content(getTheatreAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("sky walk"));
	}
	
	@Test
	public void updateTheatreTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = mockMvc.perform(put("/theatre").contentType(MediaType.APPLICATION_JSON).content(getTheatreAsJson()).characterEncoding("utf-8")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Theatre Not Updated"));
	}
	
	@Test
	public void deleteTheatreTest() throws Exception
	{
		MvcResult result = this.mockMvc.perform(delete("/theatre/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Theatre Deleted Successfull"));
	}
	public Address getAddress() {
		Address address = new Address();
		address.setCity("chennai");
		return address;
	}
	
	public Location getLocation() {
		Location location = new Location();
		location.setLatitude("180.11.34");
		return location;
	}
	
	public Theatre getTheatre()
	{
		Theatre theatre=new Theatre();
		theatre.setName("sky walk");
		theatre.setAddress(getAddress());
		theatre.setLocation(getLocation());
	     return theatre;
	}
    
	public String getTheatreAsJson() throws JsonProcessingException{
			return new ObjectMapper().writeValueAsString(getTheatre());			
	}
}

