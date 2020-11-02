package com.movieapp.notificationservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.notificationservice.dto.APISuccessResponseDTO;
import com.movieapp.notificationservice.dto.Booking;
import com.movieapp.notificationservice.dto.PlayDTO;
import com.movieapp.notificationservice.dto.UserDTO;

@Service
public class KafkaConsumer {

	@Value("${user.url}")
	private String userUrl;

	@Value("${play.url}")
	private String playUrl;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private OAuth2RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

	/**
	 * @author Mahendran Dayalan
	 * @param booking
	 * @throws Exception
	 * 
	 * Consume the message sent from other services
	 */
	@KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.consumer.group.id")
	public void receiveKafkaMessage(Booking booking) throws Exception {

		logger.info("Booking Details" + booking.toString());

		APISuccessResponseDTO userResponse = restTemplate
				.exchange(userUrl + booking.getUserid(), HttpMethod.GET, null, APISuccessResponseDTO.class).getBody();
		checkStatus(userResponse);
		APISuccessResponseDTO inventoryResponse = restTemplate
				.exchange(playUrl + booking.getPlayid(), HttpMethod.GET, null, APISuccessResponseDTO.class).getBody();
		checkStatus(inventoryResponse);
		UserDTO userDTO = mapper.convertValue(userResponse.getBody(), new TypeReference<UserDTO>() {
		});
		PlayDTO playDTO = mapper.convertValue(inventoryResponse.getBody(), new TypeReference<PlayDTO>() {
		});
		logger.info("User Details" + userDTO.toString());

		logger.info("PlayDTO" + playDTO.toString());
	}

	public static void checkStatus(APISuccessResponseDTO response) throws Exception {
		if (response.getStatusCode() != 200) {
			throw new Exception("Error in calling service message:" + response.getBody());
		}
	}
}
