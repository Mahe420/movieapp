package com.movieapp.bookingservice.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.bookingservice.dto.APISuccessResponseDTO;
import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.dto.PlayDTO;
import com.movieapp.bookingservice.dto.ScreenDTO;
import com.movieapp.bookingservice.dto.UserDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ApplicationException;
import com.movieapp.bookingservice.repository.BookingRepository;
import com.movieapp.bookingservice.service.BookingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {BookingServiceImpl.class,ObjectMapper.class})
public class BookingServiceImplTest {
	
	@Autowired
	BookingService bookingService;

	@MockBean
	OAuth2RestTemplate restTemplate;

	@MockBean
	KafkaTemplate<String, Booking> kafkaTemplate;
	
	@Autowired
	 ObjectMapper mapper;

	@MockBean
	BookingRepository bookingRepository;

	@Before
	public void setup() {
		APISuccessResponseDTO api = new APISuccessResponseDTO();
		api.setBody(play());
		api.setStatusCode(200);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://INVENTORY-SERVICE/play/v1/1"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));
		Mockito.when(restTemplate.exchange(Mockito.anyString(), Matchers.eq(HttpMethod.PUT), Mockito.any(),
				ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));
	}

	@Test
	public void addBooking() throws Exception {

		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));
		Mockito.when(bookingRepository.save(Mockito.any())).thenReturn(getBooking());
		Booking booking = bookingService.addBooking(getBooking());
		assertEquals(1,booking.getId());
	}
	
	@Test(expected=ApplicationException.class)
	public void addBookingTestError() throws Exception {
		Mockito.when(bookingRepository.save(Mockito.any())).thenThrow(Mockito.mock(DataAccessException.class));
		Booking booking=bookingService.addBooking(getBooking());
	}
	@Test
	public void getAllBooking() throws Exception {
		List list = new ArrayList();
		list.add(getBooking());
		Mockito.when(bookingRepository.findAll()).thenReturn(list);
		APISuccessResponseDTO api = new APISuccessResponseDTO();
		List list1 = new ArrayList();
		list1.add(user());
		api.setBody(list1);
		api.setStatusCode(200);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://USER-SERVICE/users/v1/"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));
		List list2 = new ArrayList();
		list2.add(play());
		APISuccessResponseDTO api2 = new APISuccessResponseDTO();
		api2.setBody(list2);
		api2.setStatusCode(200);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://INVENTORY-SERVICE/play/v1/"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api2, HttpStatus.ACCEPTED));
		
		List<BookingDTO> bookingList=bookingService.getAllBooking();
		assertEquals(1,bookingList.size());
	}
	
	@Test(expected=ApplicationException.class)
	public void getAllBookingException() throws Exception{
		Mockito.when(bookingRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		List<BookingDTO> bookingList=bookingService.getAllBooking();
	}
	
	
	@Test
	public void getBookingByIdTest() throws Exception{
		APISuccessResponseDTO api = new APISuccessResponseDTO();
		api.setBody(user());
		api.setStatusCode(200);
		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://USER-SERVICE/users/v1/1"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));

		BookingDTO booking=bookingService.getBookingById(1);
		assertEquals(1,booking.getId());
	}
	
	@Test(expected=ApplicationException.class)
	public void getBookingByIdTestError() throws Exception{
		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		BookingDTO booking=bookingService.getBookingById(1);
	}
	
	@Test
	public void deleteBooking() throws Exception {
		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));
		bookingService.deleteBooking(1);
		Mockito.verify(bookingRepository,Mockito.times(1)).deleteById(Mockito.any());
	}
	
	@Test(expected=ApplicationException.class)
	public void deleteBookingException() throws Exception {
		Mockito.doThrow(Mockito.mock(DataAccessException.class)).when(bookingRepository).deleteById(Mockito.anyInt());
		bookingService.deleteBooking(1);
	}
	
	
	
	
	
	
	public Booking getBooking() {
		Booking booking = new Booking();
		booking.setId(1);
		booking.setNoOfBookedSeats(2);
		booking.setPlayid(1);
		booking.setUserid(1);
		booking.setTotalPrice(1234);
		return booking;
	}


	public PlayDTO play() {
		ScreenDTO screen = new ScreenDTO();
		screen.setTotalSeats(200);
		PlayDTO play = new PlayDTO();
		play.setAvailableSeats(90);
		play.setId(1);
		play.setDate("1234");
		play.setEndTiming("10:00 PM");
		play.setStartTiming("8:00 AM");
		play.setId(1);
		play.setScreen(screen);
		return play;
	}

	public UserDTO user() {
		UserDTO user = new UserDTO();
		user.setId(1);
		user.setUserName("sample123");
		user.setPassword("1234");
		return user;
	}
	public String bookingJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getBooking());
	}
}
