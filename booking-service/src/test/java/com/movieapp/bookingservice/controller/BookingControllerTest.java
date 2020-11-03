package com.movieapp.bookingservice.controller;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.bookingservice.controller.BookingController;
import com.movieapp.bookingservice.dto.APISuccessResponseDTO;
import com.movieapp.bookingservice.dto.PlayDTO;
import com.movieapp.bookingservice.dto.ScreenDTO;
import com.movieapp.bookingservice.dto.UserDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.repository.BookingRepository;
import com.movieapp.bookingservice.service.BookingService;
import com.movieapp.bookingservice.service.impl.BookingServiceImpl;

@RunWith(SpringRunner.class)
@WebMvcTest({ BookingController.class, BookingServiceImpl.class })
@AutoConfigureMockMvc(addFilters = false)
public class BookingControllerTest {

	@Autowired
	BookingService bookingService;

	@MockBean
	OAuth2RestTemplate restTemplate;

	@MockBean
	KafkaTemplate<String, Booking> kafkaTemplate;

	@MockBean
	BookingRepository bookingRepository;

	@Autowired
	private MockMvc mockMvc;

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
	public void deleteBookingTestError() throws Exception {
		Mockito.when(bookingRepository.findById(Mockito.any())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(delete("/booking/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	public void deleteBookingTestIdNotFound() throws Exception {
		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		MvcResult result = this.mockMvc.perform(delete("/booking/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Booking Found for the Id 1"));
	}

	@Test
	public void deleteBooking() throws Exception {

		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));

		MvcResult result = mockMvc.perform(delete("/booking/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Booking Deleted Successfull"));
	}

	@Test
	public void addBookingTestError() throws Exception {
		Mockito.when(bookingRepository.save(Mockito.any())).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc
				.perform(post("/booking/v1").contentType(MediaType.APPLICATION_JSON).content(bookingJson())).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Booking Not Saved"));
	}

	@Test
	public void addBooking() throws Exception {

		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));

		MvcResult result = mockMvc
				.perform(post("/booking/v1").contentType(MediaType.APPLICATION_JSON).content(bookingJson())).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Booking Saved Successfull"));
	}

	@Test
	public void getBookingIdTestIdNotFound() throws Exception {
		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.empty());
		MvcResult result = this.mockMvc.perform(get("/booking/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Booking Found for the Id 1"));
	}

	@Test
	public void getBookingIdBooking() throws Exception {
		APISuccessResponseDTO api = new APISuccessResponseDTO();
		api.setBody(user());
		api.setStatusCode(200);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://USER-SERVICE/users/v1/1"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));

		Mockito.when(bookingRepository.findById(Mockito.any())).thenReturn(Optional.of(getBooking()));

		MvcResult result = mockMvc.perform(get("/booking/v1/1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Get Booking by Id Successfull"));
	}

	@Test
	public void getAllBookingTestError() throws Exception {
		Mockito.when(bookingRepository.findAll()).thenThrow(Mockito.mock(DataAccessException.class));
		MvcResult result = this.mockMvc.perform(get("/booking/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Failed to connect"));
	}

	@Test
	public void getAllBookingTestIdNotFound() throws Exception {
		Mockito.when(bookingRepository.findAll()).thenReturn(new ArrayList());
		MvcResult result = this.mockMvc.perform(get("/booking/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("No Bookings Found"));
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
		api.setBody(list2);
		api.setStatusCode(200);
		Mockito.when(restTemplate.exchange(ArgumentMatchers.matches("http://INVENTORY-SERVICE/play/v1/"),
				Matchers.eq(HttpMethod.GET), Mockito.any(), ArgumentMatchers.any(Class.class)))
				.thenReturn(new ResponseEntity<APISuccessResponseDTO>(api, HttpStatus.ACCEPTED));
		MvcResult result = this.mockMvc.perform(get("/booking/v1")).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Get all Booking Successfull"));

	}
	

	public Booking getBooking() {
		Booking booking = new Booking();
		booking.setNoOfBookedSeats(2);
		booking.setPlayid(1);
		booking.setUserid(1);
		booking.setTotalPrice(1234);
		return booking;
	}

	public String bookingJson() throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(getBooking());
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
}
