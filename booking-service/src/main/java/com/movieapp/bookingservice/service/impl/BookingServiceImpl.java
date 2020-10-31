package com.movieapp.bookingservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.bookingservice.dto.APISuccessResponseDTO;
import com.movieapp.bookingservice.dto.BookingDTO;
import com.movieapp.bookingservice.dto.PlayDTO;
import com.movieapp.bookingservice.dto.UserDTO;
import com.movieapp.bookingservice.entity.Booking;
import com.movieapp.bookingservice.exception.ApplicationException;
import com.movieapp.bookingservice.exception.ServiceException;
import com.movieapp.bookingservice.repository.BookingRepository;
import com.movieapp.bookingservice.service.BookingService;

@Service
@Transactional
public class BookingServiceImpl implements BookingService {

	public static final String ERROR_CONNECT = "Failed to connect";
	@Value("${kafka.topic.name}")
	private String topicName;

	@Autowired
	OAuth2RestTemplate restTemplate;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	private KafkaTemplate<String, Booking> kafkaTemplate;

	@Autowired
	private ObjectMapper mapper;

	public static final String ERROR_CONNECTION = "Failed to connect ";
	public static final String NO_SEATS = "Seats are not available";

	@Value("${user.url}")
	private String userUrl;

	@Value("${play.url}")
	private String playUrl;

	private static Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);

	@Override
	public Booking addBooking(Booking booking) throws ApplicationException {
		try {
			logger.info("To get play details to check");
			APISuccessResponseDTO inventoryResponse = restTemplate
					.exchange(playUrl + booking.getPlayid(), HttpMethod.GET, null, APISuccessResponseDTO.class)
					.getBody();
			checkStatus(inventoryResponse);
			PlayDTO playDTO = mapper.convertValue(inventoryResponse.getBody(), new TypeReference<PlayDTO>() {
			});
			int changeAvailableSeat = playDTO.getAvailableSeats() - booking.getNoOfBookedSeats();
			if (changeAvailableSeat >= 0) {
				logger.info("Seat are available");
				playDTO.setAvailableSeats(changeAvailableSeat);
				HttpEntity<PlayDTO> updateEntity = new HttpEntity<>(playDTO, null);
				APISuccessResponseDTO updateResponse = restTemplate
						.exchange(playUrl, HttpMethod.PUT, updateEntity, APISuccessResponseDTO.class).getBody();
				checkStatus(updateResponse);
				Booking bookingSaved = bookingRepository.save(booking);

				sendMessage(bookingSaved);

				return bookingSaved;
			} else {
				logger.error(NO_SEATS);
				throw new ApplicationException(NO_SEATS);
			}
		} catch (DataAccessException e) {
			logger.error(ERROR_CONNECTION + " {}", e.getCause());
			throw new ServiceException("Booking Not Saved", e.getCause());
		}
	}

	private void sendMessage(Booking booking) {
		try {
			kafkaTemplate.send(topicName, booking);
		} catch (Exception e) {
			logger.error("Error in Kafka {}", e.getMessage());
		}
	}

	@Override
	public List<BookingDTO> getAllBooking() throws ApplicationException {
		try {
			List<Booking> bookingList = bookingRepository.findAll();
			if (bookingList.isEmpty()) {
				logger.info("Empty booking details present in db");
				throw new ApplicationException("No Bookings Found");
			}

			APISuccessResponseDTO userResponse = restTemplate
					.exchange(userUrl, HttpMethod.GET, null, APISuccessResponseDTO.class).getBody();
			checkStatus(userResponse);
			APISuccessResponseDTO inventoryResponse = restTemplate
					.exchange(playUrl, HttpMethod.GET, null, APISuccessResponseDTO.class).getBody();
			checkStatus(inventoryResponse);
			List<UserDTO> userDTOList = mapper.convertValue(userResponse.getBody(), new TypeReference<List<UserDTO>>() {
			});
			List<PlayDTO> playDTOList = mapper.convertValue(inventoryResponse.getBody(),
					new TypeReference<List<PlayDTO>>() {
					});

			return bookingList.stream().map(booking -> {
				UserDTO userDTO = new UserDTO();
				PlayDTO playDTO = new PlayDTO();
				try {
					userDTO = userDTOList.stream().filter(user -> user.getId() == booking.getUserid()).findAny()
							.orElseThrow(() -> new ServiceException("User id not present"));
					playDTO = playDTOList.stream().filter(play -> play.getId() == booking.getPlayid()).findAny()
							.orElseThrow(() -> new ServiceException("Error in play id"));
				} catch (ServiceException ex) {
					logger.error("Error in convertion in streams");
					throw new RuntimeException("Error in id of user or play");
				}
				return new BookingDTO(booking.getId(), booking.getTotalPrice(), booking.getNoOfBookedSeats(), userDTO,
						playDTO);
			}).collect(Collectors.toList());

		} catch (DataAccessException e) {
			logger.error(ERROR_CONNECTION + "{}", e.getCause());
			throw new ServiceException(ERROR_CONNECTION, e.getCause());
		}
	}

	@Override
	public BookingDTO getBookingById(int bookingId) throws ApplicationException {
		try {
			Booking booking = bookingRepository.findById(bookingId)
					.orElseThrow(() -> new ServiceException("No Booking Found for the Id" + " " + bookingId));
			APISuccessResponseDTO userResponse = restTemplate
					.exchange(userUrl + booking.getUserid(), HttpMethod.GET, null, APISuccessResponseDTO.class)
					.getBody();
			checkStatus(userResponse);
			APISuccessResponseDTO inventoryResponse = restTemplate
					.exchange(playUrl + booking.getPlayid(), HttpMethod.GET, null, APISuccessResponseDTO.class)
					.getBody();
			checkStatus(inventoryResponse);
			UserDTO userDTO = mapper.convertValue(userResponse.getBody(), new TypeReference<UserDTO>() {
			});
			PlayDTO playDTO = mapper.convertValue(inventoryResponse.getBody(), new TypeReference<PlayDTO>() {
			});

			return new BookingDTO(booking.getId(), booking.getTotalPrice(), booking.getNoOfBookedSeats(), userDTO,
					playDTO);

		} catch (DataAccessException e) {
			logger.error(ERROR_CONNECTION + " {}", e.getCause());
			throw new ServiceException(ERROR_CONNECTION, e.getCause());
		}
	}

	@Override
	public void deleteBooking(int bookingId) throws ApplicationException {

		try {
			Booking booking = bookingRepository.findById(bookingId)
					.orElseThrow(() -> new ApplicationException("No Booking Found for the Id " + bookingId));
			APISuccessResponseDTO inventoryResponse = restTemplate
					.exchange(playUrl + booking.getPlayid(), HttpMethod.GET, null, APISuccessResponseDTO.class)
					.getBody();
			checkStatus(inventoryResponse);

			PlayDTO playDTO = mapper.convertValue(inventoryResponse.getBody(), new TypeReference<PlayDTO>() {
			});

			int changeAvailableSeat = playDTO.getAvailableSeats() + booking.getNoOfBookedSeats();
			if (changeAvailableSeat <= playDTO.getScreen().getTotalSeats()) {
				logger.info("Seat are available");
				playDTO.setAvailableSeats(changeAvailableSeat);
				HttpEntity<PlayDTO> updateEntity = new HttpEntity<>(playDTO, null);
				APISuccessResponseDTO updateResponse = restTemplate
						.exchange(playUrl, HttpMethod.PUT, updateEntity, APISuccessResponseDTO.class).getBody();
				checkStatus(updateResponse);
				bookingRepository.deleteById(bookingId);
			} else {
				logger.info(NO_SEATS);
				throw new ApplicationException(NO_SEATS);
			}
		} catch (DataAccessException e) {
			logger.error(ERROR_CONNECTION + " {}", e.getCause());
			throw new ServiceException(ERROR_CONNECTION, e.getCause());
		}

	}

	public static void checkStatus(APISuccessResponseDTO response) throws ApplicationException {
		logger.info("Response from other api {}", response.toString());
		if (response.getStatusCode() != 200) {
			logger.error("Error in calling other service");
			throw new ApplicationException("Error in calling service message:" + response.getBody());
		}
	}

}
