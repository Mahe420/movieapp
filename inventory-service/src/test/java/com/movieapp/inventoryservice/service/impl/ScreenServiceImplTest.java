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
import com.movieapp.inventoryservice.entity.Screen;
import com.movieapp.inventoryservice.entity.Theatre;
import com.movieapp.inventoryservice.exception.ApplicationException;
import com.movieapp.inventoryservice.exception.ServiceException;
import com.movieapp.inventoryservice.repository.ScreenRepository;
import com.movieapp.inventoryservice.repository.TheatreRepository;
import com.movieapp.inventoryservice.service.ScreenService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ScreenServiceImpl.class })
public class ScreenServiceImplTest {

	
	@Autowired
	ScreenService screenService;
	
	@MockBean
	ScreenRepository screenRepository;

	@MockBean
	TheatreRepository theatreRepository;
	
	@Test
	public void addScreenTest() throws Exception {
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenReturn(getScreen());
		Screen screen = screenService.addScreen(getScreen());
		assertEquals(1, screen.getId());

	}

	@Test(expected = ServiceException.class)
	public void addScreenTestError() throws JsonProcessingException, Exception {
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Screen screen = screenService.addScreen(getScreen());
	}

	@Test
	public void getAllscreenTest() throws Exception {
		List<Screen> screenList = new ArrayList<>();
		screenList.add(getScreen());
		Mockito.when(screenRepository.findAll()).thenReturn(screenList);
		List<Screen> screenList1 = screenService.getAllScreen();
		assertEquals(1, screenList1.size());
	}

	@Test(expected = ApplicationException.class)
	public void getAllscreenNotFoundTest() throws Exception {
		List<Screen> screenList = new ArrayList<>();
		Mockito.when(screenRepository.findAll()).thenReturn(screenList);
		List<Screen> screenList1 = screenService.getAllScreen();
	}

	@Test(expected = ApplicationException.class)
	public void getAllscreenTestError() throws Exception {
		Mockito.when(screenRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<Screen> screenList1 = screenService.getAllScreen();
	}

	@Test
	public void getscreenByIdTest() throws Exception {
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenReturn(Optional.of(getScreen()));
		Screen screen = screenService.getScreenById(1);
		assertEquals(1, screen.getId());
	}

	@Test(expected = ApplicationException.class)
	public void getscreenByIdNotFoundTest() throws Exception {
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenReturn(Optional.empty());
		Screen screen = screenService.getScreenById(1);
	}

	@Test(expected = ApplicationException.class)
	public void getscreenByIdTestError() throws Exception {
		Mockito.when(screenRepository.findById(Mockito.anyInt())).thenThrow(Mockito.mock(DataAccessException.class));
		Screen screen = screenService.getScreenById(1);
	}

	@Test
	public void updatescreenTest() throws Exception {
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenReturn(getScreen());
		Screen screen = screenService.updateScreen(getScreen());
		assertEquals(1, screen.getId());
	}

	@Test(expected = ServiceException.class)
	public void updatescreenTestError() throws JsonProcessingException, Exception {
		Mockito.when(screenRepository.save(Mockito.any(Screen.class))).thenThrow(Mockito.mock(DataAccessException.class));
		Screen screen = screenService.updateScreen(getScreen());
	}

	@Test
	public void deletescreenTest() throws Exception {
		screenService.deleteScreen(1);
		Mockito.verify(screenRepository, Mockito.times(1)).deleteById(Mockito.any());
	}

	@Test(expected = ServiceException.class)
	public void deletescreenTestError() throws Exception {
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(screenRepository).deleteById(Mockito.anyInt());
		screenService.deleteScreen(1);
	}
	
	
	public Screen getScreen()
	{
		Screen screen=new Screen();
		screen.setId(1);
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
