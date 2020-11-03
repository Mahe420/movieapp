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
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.movieapp.inventoryservice.entity.Address;
import com.movieapp.inventoryservice.entity.Location;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.AddressRepository;
import com.movieapp.inventoryservice.repository.LocationRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.TheatreService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TheatreServiceImpl.class })
public class TheatreServiceImplTest {

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
		Theatre theatre=theatreService.addTheatre(getTheatre());
		assertEquals(1, theatre.getId());
	}

	@Test(expected=ServiceException.class)
	public void addTheatreTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Theatre theatre=theatreService.addTheatre(getTheatre());
	}
	
	@Test
	public void getAllTheatreTest() throws Exception
	{
		List<Theatre> theatreList = new ArrayList<>();
		theatreList.add(getTheatre());
		Mockito.when(theatreRepository.findAll()).thenReturn(theatreList);
		List<Theatre> theatreList1=theatreService.getAlltheatre();
		assertEquals(1,theatreList1.size());
	}
	
	@Test(expected=ApplicationException.class)
	public void getAllTheatreNotFoundTest() throws Exception
	{
		List<Theatre> theatreList = new ArrayList<>();
		Mockito.when(theatreRepository.findAll()).thenReturn(theatreList);
		List<Theatre> theatreList1=theatreService.getAlltheatre();
		
	}
	
	@Test(expected=ApplicationException.class)
	public void getAllTheatreTestError() throws Exception
	{
		Mockito.when(theatreRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<Theatre> theatreList1=theatreService.getAlltheatre();
	}
	
	@Test
	public void getTheatreByIdTest() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getTheatre()));
		Theatre theatre=theatreService.getTheatreById(1);
		assertEquals(1, theatre.getId());	
	}
	
	@Test(expected=ApplicationException.class)
	public void getTheatreByIdNotFoundTest() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Theatre theatre=theatreService.getTheatreById(1);
	
	}
	
	@Test(expected=ApplicationException.class)
	public void getTheatreByIdTestError() throws Exception
	{
		Mockito.when(theatreRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		Theatre theatre=theatreService.getTheatreById(1);
		
	}
	
	@Test
	public void updateTheatreTest() throws Exception
	{
		Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(getAddress());
		Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(getLocation());
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenReturn(getTheatre());
		Theatre theatre=theatreService.updateTheatre(getTheatre());
		assertEquals(1, theatre.getId());
	}
	
	@Test(expected=ServiceException.class)
	public void updateTheatreTestError() throws JsonProcessingException, Exception
	{
		Mockito.when(theatreRepository.save(Mockito.any(Theatre.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Theatre theatre=theatreService.updateTheatre(getTheatre());
	}
	
	@Test
	public void deleteTheatreTest() throws Exception
	{
		theatreService.deleteTheatre(1);
		Mockito.verify(theatreRepository,Mockito.times(1)).deleteById(Mockito.any());
	}
	
	@Test(expected=ServiceException.class)
	public void deleteTheatreTestError() throws Exception
	{
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(theatreRepository).deleteById(Mockito.anyInt());
		theatreService.deleteTheatre(1);
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
		theatre.setId(1);
		theatre.setName("sky walk");
		theatre.setAddress(getAddress());
		theatre.setLocation(getLocation());
	     return theatre;
	}
}
