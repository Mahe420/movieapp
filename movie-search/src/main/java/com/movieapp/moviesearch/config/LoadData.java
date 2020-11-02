package com.movieapp.moviesearch.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.movieapp.moviesearch.dto.APISuccessResponseDTO;
import com.movieapp.moviesearch.exception.ApplicationException;
import com.movieapp.moviesearch.model.PlayData;
import com.movieapp.moviesearch.repository.PlayDataRepository;

@Component
public class LoadData {

	private Logger logger = LoggerFactory.getLogger(LoadData.class);

	@Value("${play.url}")
	String playUrl;
	
	@Autowired
	private ElasticsearchOperations operations;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private PlayDataRepository playDataRepository;

	/**
	 * @author Mahendran Dayalan
	 * @throws ApplicationException
	 * 
	 * Load data from database into elastic search
	 */
	@PostConstruct
	@Transactional
	public void loadAll() throws ApplicationException {

		operations.putMapping(PlayData.class);
		
		logger.info("Entered into Loading Data services");

		APISuccessResponseDTO response = restTemplate
				.exchange(playUrl, HttpMethod.GET, null, APISuccessResponseDTO.class)
				.getBody();
		
			if(response.getStatusCode()!=200) {
				throw new ApplicationException("Error in calling service message:"+response.getBody());
			}
			List<PlayData> play = objectMapper.convertValue(response.getBody(), new TypeReference<List<PlayData>>() {
		});

		playDataRepository.saveAll(play);
		
     	logger.info("Data is loaded");

	
	}

}
